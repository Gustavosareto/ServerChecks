package com.serverchecks.commands;

import com.serverchecks.ServerChecks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handler para os comandos /cheque
 */
public class ChequeCommand implements CommandExecutor {
    
    private final ServerChecks plugin;
    
    // Sistema de confirmação para valores altos
    private final Map<UUID, ConfirmationData> confirmations = new HashMap<>();
    
    public ChequeCommand(ServerChecks plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Console pode usar apenas /cheque info
        if (!(sender instanceof Player)) {
            // Console só pode ver informações
            if (args.length > 0 && (args[0].equalsIgnoreCase("info") || 
                                     args[0].equalsIgnoreCase("informacao") || 
                                     args[0].equalsIgnoreCase("economia"))) {
                handleInfoCommandConsole(sender);
                return true;
            }
            
            sender.sendMessage(plugin.getMessage("player-only"));
            return true;
        }
        
        Player player = (Player) sender;
        
        // Verificar permissão básica
        if (!player.hasPermission("serverchecks.use")) {
            player.sendMessage(plugin.getMessage("no-permission"));
            return true;
        }
        
        // Verificar argumentos
        if (args.length == 0) {
            player.sendMessage(plugin.getMessage("usage-main"));
            return true;
        }
        
        String subCommand = args[0].toLowerCase();
        
        switch (subCommand) {
            case "criar":
            case "create":
                handleCreateCommand(player, args);
                break;
                
            case "auto":
            case "automatico":
            case "media":
                handleAutoCommand(player, args);
                break;
                
            case "info":
            case "informacao":
            case "economia":
                handleInfoCommand(player);
                break;
                
            default:
                player.sendMessage(plugin.getMessage("usage-main"));
                break;
        }
        
        return true;
    }
    
    /**
     * Manipula o comando /cheque criar
     */
    private void handleCreateCommand(Player player, String[] args) {
        // Verificar permissão
        if (!player.hasPermission("serverchecks.criar")) {
            player.sendMessage(plugin.getMessage("no-permission"));
            return;
        }
        
        // Verificar argumentos
        if (args.length < 2) {
            player.sendMessage(plugin.getMessage("usage-criar"));
            return;
        }
        
        // Parsear valor
        double value;
        try {
            // Tentar converter usando o parser compacto (suporta 1k, 200k, 1M, etc)
            value = plugin.parseCompactValue(args[1]);
            
            if (value < 0 || Double.isNaN(value) || Double.isInfinite(value)) {
                player.sendMessage(plugin.getMessage("invalid-amount"));
                return;
            }
        } catch (Exception e) {
            player.sendMessage(plugin.getMessage("invalid-amount"));
            return;
        }
        
        // Obter mensagem opcional
        String message = null;
        if (args.length > 2) {
            StringBuilder messageBuilder = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                messageBuilder.append(args[i]).append(" ");
            }
            message = messageBuilder.toString().trim();
        }
        
        // Verificar se precisa de confirmação
        UUID playerId = player.getUniqueId();
        ConfirmationData confirmation = confirmations.get(playerId);
        
        if (plugin.getEconomyAnalyzer().shouldWarn(value)) {
            // Verificar se já confirmou
            if (confirmation != null && confirmation.isValid() && 
                confirmation.getValue() == value) {
                // Confirmado, remover da lista
                confirmations.remove(playerId);
            } else {
                // Precisa confirmar
                double percentage = plugin.getEconomyAnalyzer().getPercentageAboveAverage(value);
                String warning = plugin.getMessage("economy-warning")
                        .replace("%percentage%", String.format("%.0f", percentage))
                        .replace("%average%", plugin.formatMoney(plugin.getEconomyAnalyzer().getAverage()));
                
                player.sendMessage(warning);
                player.sendMessage(plugin.getMessage("economy-warning-confirm"));
                
                // Armazenar confirmação
                confirmations.put(playerId, new ConfirmationData(value, message));
                
                // Remover após 10 segundos
                plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {
                        confirmations.remove(playerId);
                    }
                }, 200L); // 10 segundos = 200 ticks
                
                return;
            }
        }
        
        // Verificar inventário
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(plugin.getMessage("inventory-full"));
            return;
        }
        
        // Criar cheque
        ItemStack cheque = plugin.getChequeManager().createCheque(player, value, message);
        
        if (cheque != null) {
            player.getInventory().addItem(cheque);
            player.sendMessage(plugin.getMessage("cheque-created")
                    .replace("%value%", plugin.formatMoney(value)));
        }
    }
    
    /**
     * Manipula o comando /cheque auto
     * Cria cheque baseado na média econômica
     */
    private void handleAutoCommand(Player player, String[] args) {
        // Verificar permissão
        if (!player.hasPermission("serverchecks.criar")) {
            player.sendMessage(plugin.getMessage("no-permission"));
            return;
        }
        
        // Verificar inventário
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(plugin.getMessage("inventory-full"));
            return;
        }
        
        // Obter média da economia
        double average = plugin.getEconomyAnalyzer().getAverage();
        
        if (average <= 0) {
            player.sendMessage(plugin.getMessage("economy-not-loaded"));
            return;
        }
        
        // Determinar multiplicador baseado nos argumentos
        double multiplier = 1.0; // Padrão: 1x da média
        String mode = "media"; // Modo padrão
        
        if (args.length > 1) {
            String option = args[1].toLowerCase();
            
            // Opções predefinidas
            switch (option) {
                case "baixo":
                case "low":
                case "pequeno":
                    multiplier = 0.5; // 50% da média
                    mode = "baixo";
                    break;
                    
                case "medio":
                case "medium":
                case "media":
                case "normal":
                    multiplier = 1.0; // 100% da média
                    mode = "medio";
                    break;
                    
                case "alto":
                case "high":
                case "grande":
                    multiplier = 2.0; // 200% da média
                    mode = "alto";
                    break;
                    
                case "muito":
                case "very":
                case "extremo":
                    multiplier = 5.0; // 500% da média
                    mode = "extremo";
                    break;
                    
                default:
                    // Tentar interpretar como multiplicador customizado
                    try {
                        multiplier = Double.parseDouble(option.replace("x", ""));
                        if (multiplier <= 0) {
                            player.sendMessage(plugin.getMessage("invalid-multiplier"));
                            return;
                        }
                        mode = multiplier + "x";
                    } catch (Exception e) {
                        player.sendMessage(plugin.getMessage("usage-auto"));
                        return;
                    }
                    break;
            }
        }
        
        // Calcular valor final
        double value = average * multiplier;
        
        // Arredondar para número mais limpo
        value = Math.round(value);
        
        // Verificar limites
        double maxValue = plugin.getConfig().getDouble("cheque.maximum-value", -1);
        if (maxValue > 0 && value > maxValue) {
            value = maxValue;
        }
        
        double minValue = plugin.getConfig().getDouble("cheque.minimum-value", 1.0);
        if (value < minValue) {
            value = minValue;
        }
        
        // Criar mensagem personalizada indicando que é baseado na economia
        String autoMessage = "Baseado na economia (" + mode + ")";
        
        // Criar cheque
        ItemStack cheque = plugin.getChequeManager().createCheque(player, value, autoMessage);
        
        if (cheque != null) {
            player.getInventory().addItem(cheque);
            player.sendMessage(plugin.getMessage("cheque-auto-created")
                    .replace("%value%", plugin.formatMoney(value))
                    .replace("%mode%", mode)
                    .replace("%average%", plugin.formatMoney(average)));
        }
    }
    
    /**
     * Manipula o comando /cheque info
     */
    private void handleInfoCommand(Player player) {
        // Verificar permissão
        if (!player.hasPermission("serverchecks.info")) {
            player.sendMessage(plugin.getMessage("no-permission"));
            return;
        }
        
        // Obter dados
        double average = plugin.getEconomyAnalyzer().getAverage();
        double total = plugin.getEconomyAnalyzer().getTotal();
        int players = plugin.getEconomyAnalyzer().getPlayersAnalyzed();
        
        // Enviar mensagens
        player.sendMessage(plugin.getMessageRaw("economy-info-header"));
        player.sendMessage(plugin.getMessageRaw("economy-info-average")
                .replace("%average%", plugin.formatMoney(average)));
        player.sendMessage(plugin.getMessageRaw("economy-info-total")
                .replace("%total%", plugin.formatMoney(total)));
        player.sendMessage(plugin.getMessageRaw("economy-info-players")
                .replace("%players%", String.valueOf(players)));
        player.sendMessage(plugin.getMessageRaw("economy-info-footer"));
    }
    
    /**
     * Manipula o comando /cheque info para console
     */
    private void handleInfoCommandConsole(CommandSender sender) {
        // Obter dados
        double average = plugin.getEconomyAnalyzer().getAverage();
        double total = plugin.getEconomyAnalyzer().getTotal();
        int players = plugin.getEconomyAnalyzer().getPlayersAnalyzed();
        
        // Enviar mensagens
        sender.sendMessage(plugin.getMessageRaw("economy-info-header"));
        sender.sendMessage(plugin.getMessageRaw("economy-info-average")
                .replace("%average%", plugin.formatMoney(average)));
        sender.sendMessage(plugin.getMessageRaw("economy-info-total")
                .replace("%total%", plugin.formatMoney(total)));
        sender.sendMessage(plugin.getMessageRaw("economy-info-players")
                .replace("%players%", String.valueOf(players)));
        sender.sendMessage(plugin.getMessageRaw("economy-info-footer"));
    }
    
    /**
     * Classe interna para armazenar dados de confirmação
     */
    private static class ConfirmationData {
        private final double value;
        private final String message;
        private final long timestamp;
        
        public ConfirmationData(double value, String message) {
            this.value = value;
            this.message = message;
            this.timestamp = System.currentTimeMillis();
        }
        
        public double getValue() {
            return value;
        }
        
        public String getMessage() {
            return message;
        }
        
        public boolean isValid() {
            // Válido por 10 segundos
            return (System.currentTimeMillis() - timestamp) < 10000;
        }
    }
}
