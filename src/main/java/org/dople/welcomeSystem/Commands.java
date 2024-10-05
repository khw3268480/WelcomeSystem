package org.dople.welcomeSystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        JavaPlugin instance = WelcomeSystem.getInstance();
        FileConfiguration configInstance = instance.getConfig();
        if (!(sender instanceof Player)){
            return false;

        }
        String name = sender.getName();
        List<String> already = configInstance.getStringList("Received");
        if(already.contains(name) || !(sender.hasPermission("welcome"))){
            sender.sendMessage("&f◇ &c이미 수령한 보상이거나, 신규/복귀 유저 자격이 없습니다.".replace("&", "§"));
            return false;
        }
        already.add(name);
        configInstance.set("Received", already);
        instance.saveConfig();
        ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();
        Bukkit.dispatchCommand(consoleCommandSender, "커스텀아이템 지급 " + name + " 기타-신규복귀코인");
        sender.sendMessage("&f◇ &f신규/복귀 유저 보상을 &b&n획득&f했습니다!".replace("&", "§"));
        return false;
    }
}
