package com.r1ckp1ckle.tagmsg;

import com.r1ckp1ckle.tagmsg.listeners.ChatListener;
import com.r1ckp1ckle.tagmsg.utils.ConfigUtils;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class TagMsg extends JavaPlugin {

    private static TagMsg instance;
    private ConfigUtils configUtils;

    @Override
    public void onLoad() {
        instance = this;
        configUtils = new ConfigUtils(this);
    }

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();
        configUtils.initSaveTask();
        getLogger().log(Level.INFO, getDescription().getName() + " by r1ckp1ckle has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, getDescription().getName() + " by r1ckp1ckle is now disabling.");
        getServer().getScheduler().cancelAllTasks();
        HandlerList.unregisterAll();
        getLogger().log(Level.INFO, getDescription().getName() + " by r1ckp1ckle has been disabled.");
        instance = null;
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ChatListener(getConfig().getString("tag-perms")), this);
    }

    private void registerCommands() {

    }
    public static TagMsg getInstance() { return instance; }

    public ConfigUtils getConfigUtils() {
        return configUtils;
    }
}
