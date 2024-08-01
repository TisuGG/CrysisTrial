package tissuegg.crysistrial;

import org.bukkit.plugin.java.JavaPlugin;

import tissuegg.crysistrial.Commands.AutoSmeltCommand;
import tissuegg.crysistrial.Commands.BlockBreakEventCancelCommand;
import tissuegg.crysistrial.Commands.FreezeCommand;
import tissuegg.crysistrial.Commands.MainCommandExecutor;

public class CrysisTrial extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("crysis").setExecutor(new MainCommandExecutor(this));
        this.getServer().getPluginManager().registerEvents(new BlockBreakEventCancelCommand(), this);
        this.getServer().getPluginManager().registerEvents(new FreezeCommand(), this);
        this.getServer().getPluginManager().registerEvents(new AutoSmeltCommand(), this);
    }
}