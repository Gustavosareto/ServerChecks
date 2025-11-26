package com.serverchecks.listeners;

import com.serverchecks.ServerChecks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Listener para gerenciar interações com cheques
 */
public class ChequeInteractListener implements Listener {
    
    private final ServerChecks plugin;
    
    public ChequeInteractListener(ServerChecks plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Detecta quando um jogador clica com botão direito segurando um item
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Verificar se é clique com botão direito
        if (event.getAction() != Action.RIGHT_CLICK_AIR && 
            event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        
        // Verificar se tem item na mão
        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }
        
        // Verificar se é um cheque válido
        if (!plugin.getChequeManager().isValidCheque(item)) {
            return;
        }
        
        // Verificar permissão
        if (!event.getPlayer().hasPermission("serverchecks.resgatar")) {
            event.getPlayer().sendMessage(plugin.getMessage("no-permission"));
            event.setCancelled(true);
            return;
        }
        
        // Resgatar o cheque
        boolean success = plugin.getChequeManager().redeemCheque(event.getPlayer(), item);
        
        if (success) {
            // Cancelar evento para não usar o item
            event.setCancelled(true);
        }
    }
}
