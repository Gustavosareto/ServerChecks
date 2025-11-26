package com.serverchecks.economy;

import com.serverchecks.ServerChecks;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * Analisador de economia do servidor
 * Calcula médias e totais de economia de forma assíncrona
 */
public class EconomyAnalyzer {
    
    private final ServerChecks plugin;
    private BukkitRunnable scanTask;
    
    // Dados calculados
    private double averageMoney = 0;
    private double totalMoney = 0;
    private int playersAnalyzed = 0;
    private long lastUpdate = 0;
    
    public EconomyAnalyzer(ServerChecks plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Inicia o scanner de economia em intervalos regulares
     * 
     * @param intervalMinutes Intervalo em minutos entre cada análise
     */
    public void startScan(int intervalMinutes) {
        // Parar scan anterior se existir
        stopScan();
        
        // Fazer primeira análise imediatamente (assíncrona)
        performScan();
        
        // Criar task repetida
        long intervalTicks = intervalMinutes * 60 * 20L; // Converter minutos para ticks
        
        scanTask = new BukkitRunnable() {
            @Override
            public void run() {
                performScan();
            }
        };
        
        scanTask.runTaskTimerAsynchronously(plugin, intervalTicks, intervalTicks);
    }
    
    /**
     * Para o scanner de economia
     */
    public void stopScan() {
        if (scanTask != null) {
            scanTask.cancel();
            scanTask = null;
        }
    }
    
    /**
     * Realiza uma análise completa da economia
     * Executa de forma assíncrona
     */
    private void performScan() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    boolean includeOffline = plugin.getConfig().getBoolean(
                            "economy-scan.include-offline-players", true);
                    
                    double total = 0;
                    int count = 0;
                    
                    if (includeOffline) {
                        // Analisar todos os jogadores (online e offline)
                        OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();
                        
                        for (OfflinePlayer player : offlinePlayers) {
                            if (player == null) continue;
                            
                            try {
                                double balance = plugin.getEconomy().getBalance(player);
                                if (balance > 0) {
                                    total += balance;
                                    count++;
                                }
                            } catch (Exception e) {
                                // Ignorar erros individuais
                            }
                        }
                    } else {
                        // Analisar apenas jogadores online
                        for (org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
                            try {
                                double balance = plugin.getEconomy().getBalance(player);
                                if (balance > 0) {
                                    total += balance;
                                    count++;
                                }
                            } catch (Exception e) {
                                // Ignorar erros individuais
                            }
                        }
                    }
                    
                    // Atualizar valores
                    totalMoney = total;
                    playersAnalyzed = count;
                    averageMoney = count > 0 ? total / count : 0;
                    lastUpdate = System.currentTimeMillis();
                    
                    plugin.getLogger().info("Análise econômica concluída: " + 
                            count + " jogadores, Total: " + plugin.formatMoney(total) + 
                            ", Média: " + plugin.formatMoney(averageMoney));
                    
                } catch (Exception e) {
                    plugin.getLogger().warning("Erro ao realizar análise econômica: " + e.getMessage());
                }
            }
        });
    }
    
    /**
     * Obtém a média de dinheiro dos jogadores
     * 
     * @return Média de dinheiro
     */
    public double getAverage() {
        return averageMoney;
    }
    
    /**
     * Obtém o total de dinheiro em circulação
     * 
     * @return Total de dinheiro
     */
    public double getTotal() {
        return totalMoney;
    }
    
    /**
     * Obtém o número de jogadores analisados
     * 
     * @return Número de jogadores
     */
    public int getPlayersAnalyzed() {
        return playersAnalyzed;
    }
    
    /**
     * Obtém o timestamp da última atualização
     * 
     * @return Timestamp em milissegundos
     */
    public long getLastUpdate() {
        return lastUpdate;
    }
    
    /**
     * Verifica se um valor está muito acima da média
     * 
     * @param value Valor a verificar
     * @return Percentual acima da média (ex: 100 = 100x a média)
     */
    public double getPercentageAboveAverage(double value) {
        if (averageMoney <= 0) {
            return 0;
        }
        return value / averageMoney;
    }
    
    /**
     * Verifica se um valor deve disparar aviso
     * 
     * @param value Valor a verificar
     * @return true se deve disparar aviso
     */
    public boolean shouldWarn(double value) {
        double threshold = plugin.getConfig().getDouble(
                "economy-scan.warn-threshold-percentage", 10000) / 100.0;
        
        return getPercentageAboveAverage(value) > threshold;
    }
}
