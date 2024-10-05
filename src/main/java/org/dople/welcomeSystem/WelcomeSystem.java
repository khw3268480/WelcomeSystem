package org.dople.welcomeSystem;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.util.List;

public final class WelcomeSystem extends JavaPlugin {

    private static FileConfiguration configInstance;

    private static JavaPlugin instance;

    public static FileConfiguration getConfigInstance() {
        return configInstance;
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                int hour = LocalDateTime.now().getHour();
                int minute = LocalDateTime.now().getMinute();
                int second = LocalDateTime.now().getSecond();
                if( hour == 0 && minute == 0 && (second == 0 || second == 1 || second == 2)){
                    List<String> versusData = configInstance.getStringList("Versus");
                    List<String> jumpMapData = configInstance.getStringList("JumpMap");
                    versusData.clear();
                    jumpMapData.clear();
                    configInstance.set("Versus", versusData);
                    configInstance.set("JumpMap", jumpMapData);
                    System.out.println("클리어됨");
                    instance.saveConfig();
                }
                else{
//                    System.out.println("클리어 안됨");
//                    System.out.println(String.format("%d %d %d", hour, minute, second));
                }

            }
        }, 20L, 20L);
        saveDefaultConfig();
        configInstance = getConfig();
        instance = this;
        getCommand("신규복귀유저보상").setExecutor(new Commands());
        getCommand("신규복귀자격확인").setExecutor(new CheckValid());
        Bukkit.getPluginManager().registerEvents(new JumpMap(), this);
        Bukkit.getPluginManager().registerEvents(new Versus(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
