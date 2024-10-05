package org.dople.welcomeSystem;

import com.hj.rpgsharp.rpg.apis.rpgsharp.events.area.AreaEnterEvent;
import com.hj.rpgsharp.rpg.apis.rpgsharp.events.skill.SkillCastEvent;
import dev.efnilite.ip.IP;
import dev.efnilite.ip.api.ParkourAPI;
import dev.efnilite.ip.api.event.ParkourFallEvent;
import dev.efnilite.ip.api.event.ParkourScoreEvent;
import dev.efnilite.ip.player.ParkourPlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class JumpMap implements Listener {

    JavaPlugin instance = WelcomeSystem.getInstance();
    FileConfiguration configInstance = instance.getConfig();

    public int getJumpMapCount(String name){
        List<String> already = configInstance.getStringList("JumpMap");
        return Collections.frequency(already, name);
    }


//    @EventHandler
//    public void onJumpMapEnter(AreaEnterEvent e) {
//        Player player = e.getPlayer();
//        if (e.getArea().getId().equals("JumpMap")) {
//            String name = player.getName();
//            List<String> already = configInstance.getStringList("JumpMap");
//            if (already.contains(name)) {
//                player.sendMessage("&f◇ &c오늘 이미 점프맵 보상을 수령하여, 더 이상 획득할 수 없습니다.".replace("&", "§"));
//                return;
//            }
//            already.add(name);
//            configInstance.set("JumpMap", already);
//            instance.saveConfig();
//            player.setOp(true);
//            player.performCommand("랜덤박스 지급 점프맵");
//            player.setOp(false);
//            player.sendMessage("&f◇ &f점프맵 보상을 수령했습니다!".replace("&", "§"));
//        }
//        if( e.getArea().getId().equals("JumpMapDown")){
//            ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();
//            Bukkit.dispatchCommand(consoleCommandSender, "warp jump "+player.getName());
//        }
//    }

    @EventHandler
    public void onSkillCast(SkillCastEvent e){
        String world = e.getPlayer().getWorld().getName();
        if (world.equals("jumps")){
            e.setCancelled(true);
            e.getPlayer().sendMessage("&f◇ &c점프맵에서 스킬을 사용할 수 없습니다!".replace("&", "§"));
        }
    }
}
