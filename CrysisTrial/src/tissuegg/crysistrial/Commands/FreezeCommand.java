package tissuegg.crysistrial.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class FreezeCommand implements Listener {

    private static final List<String> frozenPlayers = new ArrayList<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (frozenPlayers.contains(player.getName())) {
            event.setCancelled(true);
            player.sendMessage("Estás congelado y no puedes moverte!");
        }
    }

    public static boolean onCommand(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("Uso: /crysis freeze <player>");
            return false;
        }

        String targetName = args[1];
        if (targetName.equalsIgnoreCase("@a")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                frozenPlayers.add(player.getName());
            }
            sender.sendMessage("Todos los jugadores han sido congelados.");
            return true;
        } else {
            Player target = Bukkit.getPlayerExact(targetName);
            if (target != null) {
                frozenPlayers.add(target.getName());
                sender.sendMessage("Jugador " + target.getName() + " ha sido congelado.");
                return true;
            } else {
                sender.sendMessage("Jugador no encontrado.");
                return false;
            }
        }
    }
}
