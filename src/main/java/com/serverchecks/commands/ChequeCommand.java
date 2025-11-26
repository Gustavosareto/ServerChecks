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
        // Verificar se é jogador
        if (!(sender instanceof Player)) {
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
