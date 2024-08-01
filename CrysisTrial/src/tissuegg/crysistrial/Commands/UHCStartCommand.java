package tissuegg.crysistrial.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import tissuegg.crysistrial.CrysisTrial;

public class UHCStartCommand {

    public static boolean onCommand(CommandSender sender) {
        new BukkitRunnable() {
            int countdown = 10;

            @Override
            public void run() {
                if (countdown > 0) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "El juego comenzará en " + countdown + " segundos...");
                    countdown--;
                } else {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.RED + "HA EMPEZADO EL JUEGO", "", 10, 70, 20);
                        player.setHealth(20.0);
                        player.setFoodLevel(20);
                    }
                    Bukkit.broadcastMessage(ChatColor.RED + "¡HA EMPEZADO EL JUEGO!");
                    Bukkit.getWorlds().forEach(world -> world.setGameRule(GameRule.NATURAL_REGENERATION, false));
                    cancel();
                }
            }
        }.runTaskTimer(CrysisTrial.getPlugin(CrysisTrial.class), 0, 20);

        return true;
    }
}
