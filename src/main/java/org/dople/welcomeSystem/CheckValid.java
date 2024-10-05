package org.dople.welcomeSystem;

import com.hj.rpgsharp.rpg.apis.rpgsharp.RPGPlayerAPI;
import com.hj.rpgsharp.rpg.apis.rpgsharp.RPGSharpAPI;
import com.hj.rpgsharp.rpg.objects.RPGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CheckValid implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        RPGPlayerAPI rpgPlayerAPI = RPGSharpAPI.getRPGPlayerAPI();
        RPGPlayer rpgPlayer = rpgPlayerAPI.getRPGPlayer((Player) sender);
        if(sender.hasPermission("welcome") && rpgPlayer.getLevel() <=200){
            sender.sendMessage("&f◇ &3&n&o".replace('&', '§') + sender.getName() + "&f 님은 &e신규/복귀 이벤트 &f유저 대상자가 &b&n맞습니다.".replace('&', '§'));
            return true;
        }
        sender.sendMessage("&f◇ &3&n&o".replace('&', '§') + sender.getName() + "&f 님은 &e신규/복귀 이벤트 &f유저 대상자가 &c&n아닙니다.".replace('&', '§'));
        return false;
    }
}
