package com.maxzxwd.fitsvegetationonly;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.material.Directional;

public class BlockListeners implements Listener {
    public static final Material[] ONLY_FISTS =
        { Material.CROPS, Material.PUMPKIN, Material.MELON_BLOCK, Material.SOIL, Material.COCOA};
    public static final Material[] PISTON_DONT_MOVE =
        { Material.BEDROCK, Material.OBSIDIAN, Material.AIR, Material.CHEST, Material.FURNACE, Material.BURNING_FURNACE, Material.JUKEBOX,
            Material.ENCHANTMENT_TABLE, Material.ENDER_PORTAL_FRAME, Material.ENDER_CHEST, Material.SIGN_POST, Material.WALL_SIGN, Material.DISPENSER,
            Material.NOTE_BLOCK, Material.BREWING_STAND, Material.MOB_SPAWNER, Material.TRAPPED_CHEST };

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockFlow(BlockFromToEvent event) {
        event.setCancelled(Utils.contains(ONLY_FISTS, event.getToBlock().getType()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPhysics(BlockPhysicsEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.PISTON_BASE || block.getType() == Material.PISTON_STICKY_BASE) {
            BlockFace face = ((Directional) block.getState().getData()).getFacing();

            List<Block> blocks = new ArrayList<>(12);
            Location loc = block.getLocation().clone();
            for (int i = 1; i <= 12; i++) {
                if (Utils.contains(PISTON_DONT_MOVE, loc.add(face.getModX(), face.getModY(), face.getModZ()).getBlock().getType())) {
                    break;
                }

                blocks.add(loc.getBlock());
            }

            event.setCancelled(onPistonMove(blocks));
        }
    }

    public boolean onPistonMove(List<Block> blocks) {
        boolean cancelled = false;

        for (Block b : blocks) {
            if (Utils.contains(ONLY_FISTS, b.getType())) {
                cancelled = true;
                break;
            }
        }

        return cancelled;
    }
}