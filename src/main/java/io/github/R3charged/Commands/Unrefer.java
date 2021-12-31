package io.github.R3charged.Commands;

import io.github.R3charged.ReferalMap;
import io.github.R3charged.UUIDFetcher;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Unrefer {

    public void execute(CommandSender sender, String player) {
        try {
            UUID uuid = UUIDFetcher.getUUID(player);
            if (!ReferalMap.get().containsKey(uuid)) {
                sender.sendMessage(player + " has not been referred.");
                return;
            }
            if (ReferalMap.get().get(uuid).equals(((Player) sender).getUniqueId())) {
                ReferalMap.get().remove(uuid);
                sender.sendMessage(player + " has been removed.");
                ReferalMap.serialize();
            } else {
                sender.sendMessage(ChatColor.RED + "You cannot unrefer this player.");
            }
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "That player does not exist.");
        }
    }

    /**
    @Override
    public void execute() {
        UUID invitee = RefColl.get().get(target);
        if(sender.getUniqueId().equals(invitee)) {
            RefColl.get().remove(target);
            sender.sendMessage(targetName + " has been removed.");
            RefColl.serialize();
        } else {
            sender.sendMessage(ChatColor.RED + "You cannot unrefer this player.");
        }
    }

    public void adminExecute() {
        RefColl.get().remove(target);
        RefColl.serialize();
    }
    **/
}
