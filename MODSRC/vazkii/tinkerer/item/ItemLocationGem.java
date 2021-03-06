/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Feb 2013
package vazkii.tinkerer.item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import vazkii.tinkerer.helper.ItemNBTHelper;
import vazkii.tinkerer.helper.MathHelper;
import vazkii.tinkerer.reference.FormattingCode;

/**
 * ItemLocationGem
 *
 * The Gem of Location item, this item stores some
 * NBT data for X, Y, Z and dimension. They can also
 * be colored for teleporting.
 *
 * @author Vazkii
 */
public class ItemLocationGem extends ItemET {

	public ItemLocationGem(int par1) {
		super(par1);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		setValues(par1ItemStack, par4, par5, par6, par2EntityPlayer.dimension, par7);

		return true;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(isAttuned(par1ItemStack)) {
			int x = getX(par1ItemStack);
			int y = getY(par1ItemStack);
			int z = getZ(par1ItemStack);
			par3List.add(FormattingCode.GRAY + "X: " + x);
			par3List.add(FormattingCode.GRAY + "Y: " + y);
			par3List.add(FormattingCode.GRAY + "Z: " + z);
			par3List.add(FormattingCode.GRAY + "Side: " + ForgeDirection.getOrientation(getSide(par1ItemStack)));
			if(getDim(par1ItemStack) != par2EntityPlayer.dimension)
				par3List.add(FormattingCode.RED + "Different Dimension");
			else par3List.add(FormattingCode.BLUE + "Distance: " + new BigDecimal(MathHelper.pointDistanceSpace(x, y, z, par2EntityPlayer.posX, par2EntityPlayer.posY, par2EntityPlayer.posZ)).setScale(2, RoundingMode.UP).toString() + "m");

			int block = par2EntityPlayer.worldObj.getBlockId(x, y, z);
			par3List.add(FormattingCode.GREEN + (par2EntityPlayer.worldObj.isAirBlock(x, y, z) ? "Air" : Item.itemsList[block].getItemDisplayName(new ItemStack(block, 1, par2EntityPlayer.worldObj.getBlockMetadata(x, y, z)))));
		}
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return isAttuned(par1ItemStack);
	}

	public static void setValues(ItemStack stack, int x, int y, int z, int dim, int side) {
		ItemNBTHelper.setInt(stack, "X", x);
		ItemNBTHelper.setInt(stack, "Y", y);
		ItemNBTHelper.setInt(stack, "Z", z);
		ItemNBTHelper.setInt(stack, "dim", dim);
		ItemNBTHelper.setInt(stack, "side", side);
	}

	public static boolean isAttuned(ItemStack stack) {
		return ItemNBTHelper.detectNBT(stack);
	}

	public static int getX(ItemStack stack) {
		if(!isAttuned(stack))
			return 0;

		return ItemNBTHelper.getInt(stack, "X", 0);
	}

	public static int getY(ItemStack stack) {
		if(!isAttuned(stack))
			return 0;

		return ItemNBTHelper.getInt(stack, "Y", 0);
	}

	public static int getZ(ItemStack stack) {
		if(!isAttuned(stack))
			return 0;

		return ItemNBTHelper.getInt(stack, "Z", 0);
	}

	public static int getDim(ItemStack stack) {
		if(!isAttuned(stack))
			return 0;

		return ItemNBTHelper.getInt(stack, "dim", 0);
	}

	public static int getSide(ItemStack stack) {
		if(!isAttuned(stack))
			return 0;

		return ItemNBTHelper.getInt(stack, "side", 0);
	}

}
