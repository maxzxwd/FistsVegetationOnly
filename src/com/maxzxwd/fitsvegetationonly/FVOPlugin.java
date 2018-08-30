package com.maxzxwd.fitsvegetationonly;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class FVOPlugin extends JavaPlugin {
    private final BlockListeners blockListeners = new BlockListeners();

    @Override
    public void onEnable() {
        super.onEnable();

        getServer().getPluginManager().registerEvents(blockListeners, this);
    }

    @Override
    public void onDisable() {
        super.onDisable();

        HandlerList.unregisterAll(blockListeners);
    }
}
