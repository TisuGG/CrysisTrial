package tissuegg.crysistrial.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WarnListCommand {

    public static boolean onCommand(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("Uso: /crysis warnlist <player>");
            return false;
        }

        String targetName = args[1];
        Player target = Bukkit.getPlayerExact(targetName);
        if (target != null) {
            String uuid = target.getUniqueId().toString();
            File file = new File("warnlist.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(target.getName() + ": " + uuid);
                writer.newLine();
                sender.sendMessage("Jugador " + target.getName() + " ha sido añadido a la lista de advertencia.");
                return true;
            } catch (IOException e) {
                sender.sendMessage("No se pudo escribir en el archivo.");
                e.printStackTrace();
                return false;
            }
        } else {
            sender.sendMessage("Jugador no encontrado.");
            return false;
        }
    }
}
