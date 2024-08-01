package tissuegg.crysistrial.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import tissuegg.crysistrial.CrysisTrial;

public class MainCommandExecutor implements CommandExecutor {

    @SuppressWarnings("unused")
	private final CrysisTrial plugin;

    public MainCommandExecutor(CrysisTrial plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Uso: /crysis <warnlist, uhcstart, tppos, freeze, blockbreakeventcancel, autosmelt>");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "blockbreakeventcancel":
                return BlockBreakEventCancelCommand.onCommand(sender, args);

            case "uhcstart":
                return UHCStartCommand.onCommand(sender);

            case "tppos":
                return TeleportCommand.onCommand(sender, args);

            case "freeze":
                return FreezeCommand.onCommand(sender, args);

            case "warnlist":
                return WarnListCommand.onCommand(sender, args);

            case "autosmelt":
                return AutoSmeltCommand.onCommand(sender, args);

            default:
                sender.sendMessage("Comando desconocido.");
                return false;
        }
    }
}
