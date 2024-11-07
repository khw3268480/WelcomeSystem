package org.dople.welcomeSystem;

import com.hj.rpgsharp.rpg.apis.rpgsharp.events.area.AreaEnterEvent;
import com.hj.rpgsharp.rpg.apis.rpgsharp.events.skill.SkillCastEvent;
import com.hj.rpgsharp.rpg.apis.rpgsharp.utils.CommandUtil;
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
import us.ajg0702.parkour.api.events.PlayerEndParkourEvent;

import java.util.Collections;
import java.util.List;

public class JumpMap implements Listener {

    JavaPlugin instance = WelcomeSystem.getInstance();
    FileConfiguration configInstance = instance.getConfig();

    public int getJumpMapCount(String name){
        List<String> already = configInstance.getStringList("JumpMap");
        return Collections.frequency(already, name);
    }


    @EventHandler
    public void onJumpMapEnter(PlayerEndParkourEvent e) {
        Player player = e.getPlayer();
        if (e.getFallScore() >= 30) {
            String name = player.getName();
            List<String> already = configInstance.getStringList("JumpMap");
            if (already.contains(name)) {
                player.sendMessage("&f◇ &c오늘 이미 점프맵 보상을 수령하여, 더 이상 획득할 수 없습니다.".replace("&", "§"));
                return;
            }
            already.add(name);
            configInstance.set("JumpMap", already);
            instance.saveConfig();
            CommandUtil.runCommand(player, "랜덤박스 지급 점프맵", true);
            CommandUtil.runCommand(player, "커스텀아이템 지급 "+player.getName()+" 기타-냉기", true);
            player.sendMessage("&f◇ &f점프맵 보상을 수령했습니다!".replace("&", "§"));
        }
    }

    @EventHandler
    public void onSkillCast(SkillCastEvent e){
        String world = e.getPlayer().getWorld().getName();
        if (world.equals("elven")){
            e.setCancelled(true);
            e.getPlayer().sendMessage("&f◇ &c점프맵에서 스킬을 사용할 수 없습니다!".replace("&", "§"));
        }
    }
}
