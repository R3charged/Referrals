package io.github.R3charged;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.OfflinePlayerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import io.github.R3charged.Commands.Ancestry;
import io.github.R3charged.Commands.Refer;
import io.github.R3charged.Commands.Unrefer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Referrals extends JavaPlugin {

    private static Referrals i;
    {
        i = this;
    }
    @Override
    public void onEnable() {
        getDir().mkdir();
        ReferalMap.deserialize();
        regCmds();
        getServer().getPluginManager().registerEvents(new RefListener(), this);
    }

    @Override
    public void onDisable() {
        ReferalMap.serialize();
    }

    private void regCmds() {
        new CommandAPICommand("refer").executes((S, args) -> {
            new Refer().execute(S, (String) args[0]);
        }).withArguments(new StringArgument("Player")).register();
        new CommandAPICommand("refer").executes((S, args) -> {
            new Refer().execute(S);
        }).register();
        new CommandAPICommand("unrefer").executes((S, args) -> {
            new Unrefer().execute(S, (String) args[0]);
        }).withArguments(new StringArgument("Player").replaceSuggestions(sender -> {
            return ReferalMap.getReferredList(((Player) sender.sender()).getUniqueId());
        })).register();
        new CommandAPICommand("trace").executes((S, args) -> {
            new Ancestry().execute(S, (String) args[0]);
        }).withArguments(new StringArgument("Player")).register();
    }

    public static File getDir() {
        return i.getDataFolder();
    }
}
