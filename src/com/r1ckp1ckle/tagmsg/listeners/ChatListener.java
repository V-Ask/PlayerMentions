package com.r1ckp1ckle.tagmsg.listeners;

import com.r1ckp1ckle.tagmsg.TagMsg;
import com.r1ckp1ckle.tagmsg.utils.MessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    String tagPerms;

    public ChatListener(String tagPerms) {
        this.tagPerms = tagPerms;
    }

    @EventHandler
    private void ChatMessageSent (AsyncPlayerChatEvent event) {
        if(event.getPlayer().hasPermission(tagPerms)) {
            MessageUtils.Tag(event.getPlayer(), event.getRecipients(), event.getMessage());
            event.setCancelled(true);
        }
    }
}
