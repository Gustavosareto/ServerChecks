package com.serverchecks.managers;

import com.serverchecks.ServerChecks;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Gerenciador responsável pela criação e validação de cheques
 */
public class ChequeManager {
    
    private final ServerChecks plugin;
    private final String SIGNATURE_KEY = "§0§0§1§2§3§4§5§6§7§8§9CHECK";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    public ChequeManager(ServerChecks plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Cria um novo cheque para o jogador
     * 
     * @param player Jogador que está criando o cheque
     * @param value Valor do cheque
     * @param message Mensagem opcional (pode ser null)
     * @return ItemStack do cheque criado ou null se houver erro
     */
    public ItemStack createCheque(Player player, double value, String message) {
        // Validações
        double minValue = plugin.getConfig().getDouble("cheque.minimum-value", 1.0);
        double maxValue = plugin.getConfig().getDouble("cheque.maximum-value", -1);
        
        if (value < minValue) {
            String msg = plugin.getMessage("below-minimum")
                    .replace("%minimum%", plugin.formatMoney(minValue));
            player.sendMessage(msg);
            return null;
        }
        
        if (maxValue > 0 && value > maxValue) {
            String msg = plugin.getMessage("above-maximum")
                    .replace("%maximum%", plugin.formatMoney(maxValue));
            player.sendMessage(msg);
            return null;
        }
        
        // Verificar saldo
        double balance = plugin.getEconomy().getBalance(player);
        double fee = value * (plugin.getConfig().getDouble("cheque.creation-fee", 0) / 100.0);
        double totalRequired = value + fee;
        
        if (balance < totalRequired) {
            String msg = plugin.getMessage("insufficient-funds")
                    .replace("%required%", plugin.formatMoney(totalRequired));
            player.sendMessage(msg);
            return null;
        }
        
        // Debitar valor
        plugin.getEconomy().withdrawPlayer(player, totalRequired);
        
        // Criar item
        Material material = Material.getMaterial(
                plugin.getConfig().getString("cheque.material", "PAPER")
        );
        if (material == null) {
            material = Material.PAPER;
        }
        
        ItemStack cheque = new ItemStack(material, 1);
        ItemMeta meta = cheque.getItemMeta();
        
        // Configurar nome
        String displayName = plugin.colorize(
                plugin.getConfig().getString("cheque-format.display-name", "&6&lCheque de %value%")
        ).replace("%value%", plugin.formatMoney(value));
        meta.setDisplayName(displayName);
        
        // Configurar lore
        List<String> lore = new ArrayList<>();
        List<String> loreTemplate = plugin.getConfig().getStringList("cheque-format.lore");
        String currentDate = dateFormat.format(new Date());
        
        for (String line : loreTemplate) {
            // Processar linha de mensagem
            if (line.contains("%message%")) {
                if (message != null && !message.isEmpty()) {
                    String messageFormat = plugin.getConfig().getString("cheque-format.message-format", 
                            "&e&oMensagem: &f&o%message%");
                    line = messageFormat.replace("%message%", message);
                } else {
                    continue; // Pular linha se não houver mensagem
                }
            }
            
            // Substituir placeholders
            line = plugin.colorize(line)
                    .replace("%value%", plugin.formatMoney(value))
                    .replace("%player%", player.getName())
                    .replace("%date%", currentDate);
            
            lore.add(line);
        }
        
        // Adicionar assinatura invisível (anti-falsificação)
        lore.add(SIGNATURE_KEY);
        
        // Adicionar valor codificado (para resgate)
        lore.add(encodeValue(value));
        
        meta.setLore(lore);
        cheque.setItemMeta(meta);
        
        return cheque;
    }
    
    /**
     * Valida se um item é um cheque válido do plugin
     * 
     * @param item Item a ser validado
     * @return true se for um cheque válido
     */
    public boolean isValidCheque(ItemStack item) {
        if (item == null || !item.hasItemMeta()) {
            return false;
        }
        
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) {
            return false;
        }
        
        List<String> lore = meta.getLore();
        
        // Verificar assinatura
        return lore.contains(SIGNATURE_KEY);
    }
    
    /**
     * Obtém o valor de um cheque
     * 
     * @param item Cheque
     * @return Valor do cheque ou 0 se inválido
     */
    public double getChequeValue(ItemStack item) {
        if (!isValidCheque(item)) {
            return 0;
        }
        
        List<String> lore = item.getItemMeta().getLore();
        
        // Procurar linha com valor codificado
        for (String line : lore) {
            if (line.startsWith("§0§0VALUE:")) {
                return decodeValue(line);
            }
        }
        
        return 0;
    }
    
    /**
     * Resgata um cheque, adicionando o valor ao saldo do jogador
     * 
     * @param player Jogador que está resgatando
     * @param cheque Cheque a ser resgatado
     * @return true se resgatado com sucesso
     */
    public boolean redeemCheque(Player player, ItemStack cheque) {
        if (!isValidCheque(cheque)) {
            player.sendMessage(plugin.getMessage("invalid-cheque"));
            return false;
        }
        
        double value = getChequeValue(cheque);
        
        if (value <= 0) {
            player.sendMessage(plugin.getMessage("invalid-cheque"));
            return false;
        }
        
        // Adicionar valor ao jogador
        plugin.getEconomy().depositPlayer(player, value);
        
        // Remover cheque do inventário
        if (cheque.getAmount() > 1) {
            cheque.setAmount(cheque.getAmount() - 1);
        } else {
            player.getInventory().setItemInHand(null);
        }
        
        // Mensagem de sucesso
        String msg = plugin.getMessage("cheque-redeemed")
                .replace("%value%", plugin.formatMoney(value));
        player.sendMessage(msg);
        
        return true;
    }
    
    /**
     * Codifica o valor do cheque em uma string invisível
     * 
     * @param value Valor a ser codificado
     * @return String codificada
     */
    private String encodeValue(double value) {
        return "§0§0VALUE:" + value;
    }
    
    /**
     * Decodifica o valor de uma string codificada
     * 
     * @param encoded String codificada
     * @return Valor decodificado
     */
    private double decodeValue(String encoded) {
        try {
            String valueStr = encoded.replace("§0§0VALUE:", "");
            return Double.parseDouble(valueStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
