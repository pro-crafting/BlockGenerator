package com.pro_crafting.mc.blockgenerator.provider;

import java.io.File;
import java.io.FileInputStream;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockState;
import org.bukkit.block.data.BlockData;

import com.pro_crafting.mc.common.Point;
import com.pro_crafting.mc.common.Size;
import com.pro_crafting.mc.blockgenerator.criteria.Criteria;

public class SchematicProvider implements SizeProvider {
    private Criteria criteria;

    private Clipboard clipboard;

    public SchematicProvider(Criteria criteria, File file) {
        this.criteria = criteria;

        try {
            this.clipboard = load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Clipboard load(File file) throws Exception {

        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            return reader.read();
        }
    }


    public BlockData getBlockData(Point point, BlockData current) {
        if (!criteria.matches(point, current)) {
            return current;
        }

        BlockVector3 vector3 = clipboard.getRegion().getMinimumPoint().add(point.getX(), point.getY(), point.getZ());

        BlockState block = clipboard.getBlock(vector3);


        return BukkitAdapter.adapt(block);
    }

    public Criteria getCriteria() {
        return this.criteria;
    }


    public Size getSize() {
        return new Size(clipboard.getDimensions().getBlockX(), clipboard.getDimensions().getBlockY(), clipboard.getDimensions().getBlockZ());
    }

    public Point getOrigin() {
        return new Point(clipboard.getRegion().getMinimumPoint().getBlockX(), clipboard.getRegion().getMinimumPoint().getBlockY(), clipboard.getRegion().getMinimumPoint().getBlockZ());
    }
}
