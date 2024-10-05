package org.dople.welcomeSystem;

import com.binggre.versus.apis.VersusEvent;
import com.binggre.versus.apis.VersusRankPromoteEvent;
import com.binggre.versus.apis.VersusStopEvent;
import com.binggre.versus.objcets.PlayerVersus;
import com.binggre.versus.objcets.enums.VersusType;
import com.hj.rpgsharp.rpg.apis.rpgsharp.events.area.AreaEnterEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class Versus implements Listener {
    JavaPlugin instance = WelcomeSystem.getInstance();
    FileConfiguration configInstance = instance.getConfig();

    public int getVersusCount(String name){
        List<String> already = configInstance.getStringList("Versus");
        return Collections.frequency(already, name);
    }

    @EventHandler
    public void onRankVersusEnd(VersusStopEvent e) {
        if(e.getVersusType().equals(VersusType.RANK)){
            Player winner = e.getWinner().toPlayer();
            String name = winner.getName();
            List<String> already = configInstance.getStringList("Versus");
            if (Collections.frequency(already, name)>=3) {
                return;
            }
            already.add(name);
            configInstance.set("Versus", already);
            instance.saveConfig();
            winner.setOp(true);
            winner.performCommand("커스텀아이템 지급 "+winner.getName()+" 대전코인");
            winner.setOp(false);
            winner.sendMessage(("&f◇ &f대전 임무 보상을 획득했습니다! &7&o( 오늘 획득 가능한 보상 갯수: "+(3-Collections.frequency(already, name))+"개").replace("&", "§"));
        }
    }
}
