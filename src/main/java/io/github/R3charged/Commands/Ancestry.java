package io.github.R3charged.Commands;

import io.github.R3charged.ReferalMap;
import io.github.R3charged.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Ancestry {
    private final String arrow = "\u2190";

    public void execute(CommandSender sender, String player) {
        try {
            UUID uuid = UUIDFetcher.getUUID(player);
            if (!ReferalMap.get().containsKey(uuid)) {
                sender.sendMessage(player + " has not been referred.");
                return;
            }
            String msg = ChatColor.GREEN + player + " ";
            uuid = ReferalMap.get().get(uuid);
            while (!uuid.equals(ReferalMap.SERVER_ID)) {

                msg += ChatColor.GRAY + arrow + ChatColor.RESET + " " + getName(uuid);
                uuid = ReferalMap.get().get(uuid);
            }
            sender.sendMessage(msg);
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "That player does not exist.");
        }
    }

    private String getName(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }
    /**
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            commandSender.sendMessage(ChatColor.RED + "Please indicate a player.");
            return true;
        }
        try {
            UUID id = RefColl.get().get(Bukkit.getOfflinePlayer(strings[0]).getUniqueId());

            String msg = ChatColor.GREEN + strings[0] + " ";
            while (!id.equals(RefColl.SERVER_ID)) {

                msg += ChatColor.GRAY + arrow + ChatColor.RESET + " " + getName(id);
                id = RefColl.get().get(id);
            }
            commandSender.sendMessage(msg);
        } catch (Exception e) {
            commandSender.sendMessage(ChatColor.RED + "That is not a player in this server.");
        }
        return true;
    }

    private String getName(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }
    **/
}
