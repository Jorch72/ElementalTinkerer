/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Dec 2012
package vazkii.tinkerer.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import vazkii.tinkerer.gui.GuiElementalDesk;
import vazkii.tinkerer.reference.GuiIDs;
import vazkii.tinkerer.tile.TileEntityElementalDesk;
import vazkii.tinkerer.tile.container.ContainerElementalDesk;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * GuiHandler
 *
 * Handler for the GUIs in the mod. This class constructs
 * the containers or guis depending on the side.
 *
 * @author Vazkii
 */
public class GuiHandler implements IGuiHandler {

	public static final GuiHandler INSTANCE = new GuiHandler();

	private GuiHandler() { }

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case GuiIDs.ID_ELEMENTAL_DESK :
			return new ContainerElementalDesk(player.inventory, (TileEntityElementalDesk) world.getBlockTileEntity(x, y, z));

		// Will be expanded when more guis are added
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case GuiIDs.ID_ELEMENTAL_DESK :
			return new GuiElementalDesk(player.inventory, (TileEntityElementalDesk) world.getBlockTileEntity(x, y, z));
		}


		return null;
	}



}