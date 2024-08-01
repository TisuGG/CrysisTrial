package tissuegg.crysistrial.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakEventCancelCommand implements Listener {

    private static final List<String> blockedPlayers = new ArrayList<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (blockedPlayers.contains(player.getName())) {
            event.setCancelled(true);
            player.sendMessage("Tu capacidad de romper bloques ha sido deshabilitada!");
        }
    }

    public static boolean onCommand(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("Uso: /crysis blockbreakeventcancel <player>");
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[1]);
        if (target != null) {
            blockedPlayers.add(target.getName());
            sender.sendMessage("Capacidad de romper bloques deshabilitada para " + target.getName());
            return true;
        } else {
            sender.sendMessage("Jugador no encontrado.");
            return false;
        }
    }
}
