package com.r1ckp1ckle.tagmsg.utils;

import com.r1ckp1ckle.tagmsg.TagMsg;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.logging.Level;

public class ConfigUtils {
    private TagMsg plugin;
    private File configFile;
    private FileConfiguration config;

    public ConfigUtils(TagMsg plugin) {
        this.plugin = plugin;
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to load plugin files.");
            plugin.getLogger().log(Level.SEVERE, "Error: " + e.getMessage());
        }
    }

    public void initSaveTask() {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this::saveFiles, 200L, 200L);
    }

    public void reloadFiles() {
        try {
            config.load(configFile);
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to load plugin files.");
            plugin.getLogger().log(Level.SEVERE, "Error: " + e.getMessage());
        }
    }

    public void saveFiles() {
        try {
            config.save(configFile);
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to save plugin files.");
            plugin.getLogger().log(Level.SEVERE, "Error: " + e.getMessage());
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
