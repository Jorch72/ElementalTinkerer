/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Feb 2013
package vazkii.tinkerer.helper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.tinkerer.reference.BlockNames;
import vazkii.tinkerer.reference.WorldGenRates;

/**
 * ElementiumOreGenerationHelper
 *
 * Helper for generating Elementium Ore. Elementium ore
 * should only generate if no veins generated in nearby
 * chunks.
 *
 * @author Vazkii
 */
public class ElementiumOreGenerationHelper {

	public static final Map<Integer, Map<Integer, ChunkCoordinates>> veins = new HashMap();

	public static boolean isVeinAt(int x, int z) {
		if(!veins.containsKey(x))
			return false;

		Map<Integer, ChunkCoordinates> mapX = veins.get(x);
		return mapX.containsKey(z);
	}

	public static void setVeinAt(int x, int z, ChunkCoordinates coordinates) {
		if(!veins.containsKey(x))
			veins.put(x, new HashMap());

		veins.get(x).put(z, coordinates);
	}

	public static void setVeinAtWrite(int x, int z, ChunkCoordinates coordinates, World world) {
		setVeinAt(x, z, coordinates);
		writeToNBT(world);
	}

	public static void writeToNBT(World world) {
		NBTTagCompound worldCmp = IOHelper.getWorldCache(world);
		if(worldCmp == null)
			return;

		NBTTagCompound subCmp = new NBTTagCompound();
		for(int x : veins.keySet()) {
			NBTTagCompound xCmp = new NBTTagCompound();
			Map<Integer, ChunkCoordinates> veinsAtX = veins.get(x);
			for(int z : veinsAtX.keySet()) {
				NBTTagCompound zCmp = new NBTTagCompound();
				ChunkCoordinates coords = veinsAtX.get(z);
				zCmp.setInteger("x", coords.posX);
				zCmp.setInteger("y", coords.posY);
				zCmp.setInteger("z", coords.posZ);

				xCmp.setCompoundTag("z" + z, zCmp);

			}
			subCmp.setCompoundTag("x" + x, xCmp);
		}
		worldCmp.setCompoundTag(BlockNames.ELEMENTIUM_ORE_SPAWNER_DISPLAY_NAME, subCmp);
		IOHelper.updateWorldNBTTagCompound(world, worldCmp);
	}

	public static void readFromNBT() {
		NBTTagCompound worldCmp = IOHelper.getWorldCache(MiscHelper.getServer().worldServers[0]);
		if(worldCmp.hasKey(BlockNames.ELEMENTIUM_ORE_NAME)) {
			NBTTagCompound subCmp = worldCmp.getCompoundTag(BlockNames.ELEMENTIUM_ORE_NAME);
			Collection<NBTBase> tags = subCmp.getTags();
			for(NBTBase tag : tags) {
				String name = tag.getName();
				if(tag instanceof NBTTagCompound && name.startsWith("x")) {
					NBTTagCompound xCmp = (NBTTagCompound)tag;
					int x = Integer.parseInt(name.substring(1));
					Collection<NBTBase> xTags = xCmp.getTags();
					for(NBTBase tag1 : xTags) {
						String name1 = tag1.getName();
						if(tag1 instanceof NBTTagCompound && name1.startsWith("z")) {
							int z = Integer.parseInt(name1.substring(1));
							int veinX = ((NBTTagCompound)tag1).getInteger("x");
							int veinY = ((NBTTagCompound)tag1).getInteger("y");
							int veinZ = ((NBTTagCompound)tag1).getInteger("z");

							setVeinAt(x, z, new ChunkCoordinates(veinX, veinY, veinZ));
						}
					}
				}
			}
		}
	}

	public static boolean checkAround(int xCenter, int zCenter) {
		for(int x = xCenter - WorldGenRates.ELEMENTIUM_ORE_RANGE_CHECK; x < xCenter + WorldGenRates.ELEMENTIUM_ORE_RANGE_CHECK; x++)
			for(int z = zCenter - WorldGenRates.ELEMENTIUM_ORE_RANGE_CHECK; z < zCenter + WorldGenRates.ELEMENTIUM_ORE_RANGE_CHECK; z++)
				if(isVeinAt(x, z))
					return true;
		return false;
	}

}
