package tissuegg.crysistrial.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AutoSmeltCommand implements Listener {

    private static final List<String> autoSmeltPlayers = new ArrayList<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (autoSmeltPlayers.contains(player.getName())) {
            Material blockType = event.getBlock().getType();
            ItemStack smeltedItem = null;

            switch (blockType) {
                case IRON_ORE:
                case DEEPSLATE_IRON_ORE:
                    smeltedItem = new ItemStack(Material.IRON_INGOT);
                    break;
                case GOLD_ORE:
                case DEEPSLATE_GOLD_ORE:
                    smeltedItem = new ItemStack(Material.GOLD_INGOT);
                    break;
                case ANCIENT_DEBRIS:
                    smeltedItem = new ItemStack(Material.NETHERITE_SCRAP);
                    break;
                default:
                    return;
            }

            if (smeltedItem != null) {
                event.setDropItems(false);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), smeltedItem);
            }
        }
    }

    public static boolean onCommand(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("Uso: /crysis autosmelt <player>");
            return false;
        }

        String targetName = args[1];
        if (targetName.equalsIgnoreCase("@a")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                autoSmeltPlayers.add(player.getName());
            }
            sender.sendMessage("Auto-smelt habilitado para todos los jugadores.");
            return true;
        } else {
            Player target = Bukkit.getPlayerExact(targetName);
            if (target != null) {
                autoSmeltPlayers.add(target.getName());
                sender.sendMessage("Auto-smelt habilitado para " + target.getName());
                return true;
            } else {
                sender.sendMessage("Jugador no encontrado.");
                return false;
            }
        }
    }
}
