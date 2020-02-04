package com.r1ckp1ckle.tagmsg.utils;

import com.r1ckp1ckle.tagmsg.TagMsg;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.logging.Level;

public class MessageUtils {
    public static void Tag(Player player, Set<Player> recipients, String msg) {
        String chatColorTagged = TagMsg.getInstance().getConfig().getString("player-tagged-chatcolor");
        String chatColor = TagMsg.getInstance().getConfig().getString("player-name-chatcolor");
        Sound sound = Sound.valueOf(TagMsg.getInstance().getConfig().getString("tag-sound"));
        if(sound == null) {
            TagMsg.getInstance().getServer().getLogger().log(Level.WARNING, String.format("The sound %s is invalid. No notification sound will be played.", TagMsg.getInstance().getConfig().getString("tag-sound")));
        }
        for(Player recipient : recipients) {
            String newMsg = "<" + player.getDisplayName() + "> ";
            for(String s : msg.split(" ")) {
                if(!s.startsWith("@")) {
                    newMsg += ChatColor.WHITE + s + " ";
                    continue;
                }
                if(Bukkit.getPlayer(s.substring(1)) == null) {
                    newMsg += ChatColor.WHITE + s + " ";
                    continue;
                }
                if(recipient.getName().equals(s.substring(1))) {
                    if(sound != null) recipient.playSound(recipient.getLocation(), sound, 1f, 1f);
                    newMsg += ChatColor.translateAlternateColorCodes('&', chatColorTagged + s + " ");
                    continue;
                }
                if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(s.substring(1)))) {
                    if(Bukkit.getPlayer(s.substring(1)).getName().equals(s.substring(1))) newMsg += ChatColor.translateAlternateColorCodes('&', chatColor + s + " ");
                    else newMsg += ChatColor.WHITE + s + " ";
                } else {
                    newMsg += ChatColor.WHITE + s + " ";
                }
            }
            recipient.sendMessage(newMsg);
        }
    }
}
