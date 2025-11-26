package com.serverchecks;

import com.serverchecks.commands.ChequeCommand;
import com.serverchecks.economy.EconomyAnalyzer;
import com.serverchecks.listeners.ChequeInteractListener;
import com.serverchecks.managers.ChequeManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Classe principal do plugin ServerChecks
 * Sistema de cheques com análise econômica para Minecraft 1.8.8
 */
public class ServerChecks extends JavaPlugin {
    
    private static ServerChecks instance;
    private Economy economy;
    private ChequeManager chequeManager;
    private EconomyAnalyzer economyAnalyzer;
    
    @Override
    public void onEnable() {
        instance = this;
        
        // Salvar configuração padrão se não existir
        saveDefaultConfig();
        
        // Configurar integração com Vault
        if (!setupEconomy()) {
            getLogger().severe("Vault não encontrado! Desabilitando plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        
        getLogger().info("Vault detectado! Sistema de economia carregado.");
        
        // Inicializar gerenciadores
        chequeManager = new ChequeManager(this);
        economyAnalyzer = new EconomyAnalyzer(this);
        
        // Registrar comandos
        getCommand("cheque").setExecutor(new ChequeCommand(this));
        
        // Registrar listeners
        getServer().getPluginManager().registerEvents(new ChequeInteractListener(this), this);
        
        // Iniciar análise econômica se habilitada
        if (getConfig().getBoolean("economy-scan.enabled", true)) {
            int intervalMinutes = getConfig().getInt("economy-scan.interval-minutes", 5);
            economyAnalyzer.startScan(intervalMinutes);
            getLogger().info("Análise econômica iniciada! Intervalo: " + intervalMinutes + " minutos.");
        }
        
        getLogger().info("ServerChecks v" + getDescription().getVersion() + " habilitado com sucesso!");
    }
    
    @Override
    public void onDisable() {
        // Parar análise econômica
        if (economyAnalyzer != null) {
            economyAnalyzer.stopScan();
        }
        
        // Cancelar todas as tasks
        getServer().getScheduler().cancelTasks(this);
        
        getLogger().info("ServerChecks desabilitado!");
    }
    
    /**
     * Configura a integração com o Vault
     * @return true se configurado com sucesso
     */
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager()
                .getRegistration(Economy.class);
        
        if (rsp == null) {
            return false;
        }
        
        economy = rsp.getProvider();
        return economy != null;
    }
    
    /**
     * Obtém a instância do plugin
     * @return Instância do ServerChecks
     */
    public static ServerChecks getInstance() {
        return instance;
    }
    
    /**
     * Obtém o provedor de economia do Vault
     * @return Economy provider
     */
    public Economy getEconomy() {
        return economy;
    }
    
    /**
     * Obtém o gerenciador de cheques
     * @return ChequeManager
     */
    public ChequeManager getChequeManager() {
        return chequeManager;
    }
    
    /**
     * Obtém o analisador de economia
     * @return EconomyAnalyzer
     */
    public EconomyAnalyzer getEconomyAnalyzer() {
        return economyAnalyzer;
    }
    
    /**
     * Formata uma mensagem com o prefixo do plugin
     * @param message Mensagem a ser formatada
     * @return Mensagem formatada
     */
    public String formatMessage(String message) {
        String prefix = getConfig().getString("messages.prefix", "&8[&6ServerChecks&8]&r");
        return colorize(prefix + " " + message);
    }
    
    /**
     * Obtém uma mensagem da configuração
     * @param path Caminho da mensagem
     * @return Mensagem formatada
     */
    public String getMessage(String path) {
        return formatMessage(getConfig().getString("messages." + path, "&cMensagem não encontrada: " + path));
    }
    
    /**
     * Obtém uma mensagem da configuração sem o prefixo
     * @param path Caminho da mensagem
     * @return Mensagem formatada sem prefixo
     */
    public String getMessageRaw(String path) {
        return colorize(getConfig().getString("messages." + path, "&cMensagem não encontrada: " + path));
    }
    
    /**
     * Converte códigos de cor (&) em cores do Minecraft
     * @param text Texto a ser colorido
     * @return Texto com cores
     */
    public String colorize(String text) {
        return text.replace("&", "§");
    }
    
    /**
     * Converte uma string com sufixo (k, M) em valor numérico
     * Exemplos: "1k" = 1000, "5.5k" = 5500, "2M" = 2000000
     * @param input String a ser convertida
     * @return Valor numérico ou -1 se inválido
     */
    public double parseCompactValue(String input) {
        if (input == null || input.isEmpty()) {
            return -1;
        }
        
        input = input.trim().replace(",", ".");
        
        try {
            // Verificar se termina com 'k' ou 'K' (milhares)
            if (input.toLowerCase().endsWith("k")) {
                String numberPart = input.substring(0, input.length() - 1).trim();
                double value = Double.parseDouble(numberPart);
                return value * 1000;
            }
            // Verificar se termina com 'm' ou 'M' (milhões)
            else if (input.toLowerCase().endsWith("m")) {
                String numberPart = input.substring(0, input.length() - 1).trim();
                double value = Double.parseDouble(numberPart);
                return value * 1000000;
            }
            // Número normal sem sufixo
            else {
                return Double.parseDouble(input);
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Formata um valor monetário de acordo com a configuração
     * @param value Valor a ser formatado
     * @return Valor formatado
     */
    public String formatMoney(double value) {
        boolean useCompact = getConfig().getBoolean("cheque.compact-format", true);
        
        if (useCompact) {
            return formatCompactMoney(value);
        }
        
        String format = getConfig().getString("cheque.value-format", "$%,.2f");
        return String.format(format, value);
    }
    
    /**
     * Formata um valor monetário de forma compacta (1k, 200k, 1M, etc)
     * @param value Valor a ser formatado
     * @return Valor formatado de forma compacta
     */
    private String formatCompactMoney(double value) {
        String prefix = getConfig().getString("cheque.currency-symbol", "$");
        String suffix = "";
        
        if (value < 0) {
            prefix = "-" + prefix;
            value = Math.abs(value);
        }
        
        // Valores menores que 1000 - formato normal
        if (value < 1000) {
            if (value == (long) value) {
                return prefix + String.format("%d", (long) value) + suffix;
            } else {
                return prefix + String.format("%.2f", value) + suffix;
            }
        }
        // Milhões (1.000.000+)
        else if (value >= 1000000) {
            double millions = value / 1000000.0;
            if (millions == (long) millions) {
                return prefix + String.format("%dM", (long) millions) + suffix;
            } else if (millions >= 100) {
                return prefix + String.format("%.0fM", millions) + suffix;
            } else if (millions >= 10) {
                return prefix + String.format("%.1fM", millions) + suffix;
            } else {
                return prefix + String.format("%.2fM", millions) + suffix;
            }
        }
        // Milhares (1.000+)
        else {
            double thousands = value / 1000.0;
            if (thousands == (long) thousands) {
                return prefix + String.format("%dk", (long) thousands) + suffix;
            } else if (thousands >= 100) {
                return prefix + String.format("%.0fk", thousands) + suffix;
            } else if (thousands >= 10) {
                return prefix + String.format("%.1fk", thousands) + suffix;
            } else {
                return prefix + String.format("%.2fk", thousands) + suffix;
            }
        }
    }
}
