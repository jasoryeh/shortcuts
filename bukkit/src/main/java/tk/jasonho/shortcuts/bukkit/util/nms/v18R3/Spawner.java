package tk.jasonho.shortcuts.bukkit.util.nms.v18R3;

import net.minecraft.server.v1_8_R3.MobSpawnerAbstract;
import net.minecraft.server.v1_8_R3.TileEntityMobSpawner;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftCreatureSpawner;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Since Spigot API for 1.8 and below is useless ugh.
 */
public class Spawner {

    private final Block spawner;

    public Spawner(Block spawner) {
        if (!verifySpawner(spawner)) {
            throw new IllegalArgumentException("Must be a spawner!");
        }
        this.spawner = spawner;
    }

    private static boolean verifySpawner(Block block) {
        return block.getType() == Material.MOB_SPAWNER;
    }

    public boolean isSpawner() {
        return this.spawner.getType() == Material.MOB_SPAWNER;
    }

    public CreatureSpawner getBukkitSpawner() {
        return (CreatureSpawner) this.spawner.getState();
    }

    public void setCurrentDelay(int delay) {
        this.getBukkitSpawner().setDelay(delay);
    }

    public void setEntity(EntityType type) {
        this.getBukkitSpawner().setSpawnedType(type);
    }

    public EntityType getEntity() {
        return this.getBukkitSpawner().getSpawnedType();
    }

    public CraftCreatureSpawner getCreatureSpawnerV18() {
        return (CraftCreatureSpawner) this.getBukkitSpawner();
    }

    public TileEntityMobSpawner getSpawnerTileEntityData() {
        return this.getCreatureSpawnerV18().getTileEntity();
    }

    public MobSpawnerAbstract getMobSpawnerAbstract() {
        return this.getSpawnerTileEntityData().getSpawner();
    }

    public boolean isActivated() {
        try {
            Method g = getMobSpawnerAbstract().getClass().getDeclaredMethod("g");
            g.setAccessible(true);
            return (boolean) g.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void spawnNow() {
        try {
            Method c = getMobSpawnerAbstract().getClass().getDeclaredMethod("c");
            c.setAccessible(true);
            c.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setMinMaxDelay(int delay) {
        this.modifyField("minSpawnDelay", delay);
        this.modifyField("maxSpawnDelay", delay);
    }

    public void setSpawnCount(int amount) {
        this.modifyField("spawnCount", amount);
    }

    public void setSpawnRange(int amount) {
        this.modifyField("spawnRange", amount);
    }

    public void setRequiredPlayerRange(int range) {
        this.modifyField("requiredPlayerRange", range);
    }

    public void setMaxNearbyEntities(int max) {
        this.modifyField("maxNearbyEntities", max);
    }

    public int getMinDelay() {
        return (int) this.getField("minSpawnDelay");
    }

    public int getMaxDelay() {
        return (int) this.getField("maxSpawnDelay");
    }

    public int getSpawnCount() {
        return (int) this.getField("spawnCount");
    }

    public int getSpawnRange() {
        return (int) this.getField("spawnRange");
    }

    public int getRequiredPlayerRange() {
        return (int) this.getField("requiredPlayerRange");
    }

    public int getMaxNearbyEntities() {
        return (int) this.getField("maxNearbyEntities");
    }

    // Helpers

    /**
     * Modifies a single field in MobSpawnerAbstract
     * <p>
     * see getMobSpawnerAbstract's return type's class
     *
     * @param field Variable name
     * @param v     New value for variable
     */
    private void modifyField(String field, Object v) {
        try {
            Field f1 = MobSpawnerAbstract.class.getDeclaredField(field);
            f1.setAccessible(true);
            f1.set(this.getMobSpawnerAbstract(), v);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private Object getField(String field) {
        try {
            Field f1 = MobSpawnerAbstract.class.getDeclaredField(field);
            f1.setAccessible(true);
            return f1.get(this.getMobSpawnerAbstract());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }
}

