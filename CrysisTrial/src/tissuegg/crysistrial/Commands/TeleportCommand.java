package tissuegg.crysistrial.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand {

    public static boolean onCommand(CommandSender sender, String[] args) {
        if (args.length != 5) {
            sender.sendMessage("Uso: /crysis tppos <player> <x> <y> <z>");
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[1]);
        if (target != null) {
            try {
                double x = Double.parseDouble(args[2]);
                double y = Double.parseDouble(args[3]);
                double z = Double.parseDouble(args[4]);
                Location location = new Location(target.getWorld(), x, y, z);
                target.teleport(location);
                sender.sendMessage(ChatColor.GREEN + "Teletransportado " + target.getName() + " a las coordenadas: " + x + ", " + y + ", " + z);
                return true;
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Las coordenadas deben ser números.");
                return false;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Jugador no encontrado.");
            return false;
        }
    }
}
