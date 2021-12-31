package io.github.R3charged.Commands;

import io.github.R3charged.ReferalMap;
import io.github.R3charged.UUIDFetcher;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Refer {

    private int MAX_REFS = 5;

    public void execute(CommandSender sender, String player) {
        int numref = 0;
        if (sender instanceof Player) {
            numref = ReferalMap.getReferred(((Player) sender).getUniqueId());
            if (numref > MAX_REFS) {
                sender.sendMessage(ChatColor.RED + "You have referred the maximum number of players.");
                return;
            }
        }
        try {
            UUID uuid = UUIDFetcher.getUUID(player);
            if (ReferalMap.get().containsKey(uuid)) {
                sender.sendMessage(player + " has already been referred.");
                return;
            }
            if (sender instanceof Player) {
                ReferalMap.get().put(uuid, ((Player) sender).getUniqueId());
                sender.sendMessage(player + " has been added. " +
                        (MAX_REFS - numref - 1) + " referrals remaining.");
            } else {
                ReferalMap.get().put(uuid, ReferalMap.SERVER_ID);
                sender.sendMessage(player + " has been added.");
            }
            ReferalMap.serialize();
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "That player does not exist.");
        }
    }

    public void execute(CommandSender sender) {
        String[] arr;
        if (sender instanceof Player) {
            arr = ReferalMap.getReferredList(((Player) sender).getUniqueId());
        } else {
            arr = ReferalMap.getReferredList(ReferalMap.SERVER_ID);
        }
        if (arr.length == 0) {
            sender.sendMessage("You have not invited anybody.");
            return;
        }
        String s = ChatColor.UNDERLINE + "Referred" + ChatColor.RESET;
        for (String name : arr) {
            s += "\n" + name;
        }
        sender.sendMessage(s);
    }
}
