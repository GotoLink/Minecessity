package mods.minecessity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class Y_ItemPrtbWorkBence extends Item
{
    protected Y_ItemPrtbWorkBence(int i)
    {
        super(i);
		setMaxStackSize(1);
		setMaxDamage(100);
    }

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer e)
    {
		int i = (int)Math.floor(e.posX);
		int j = (int)Math.floor(e.boundingBox.minY);
		int k = (int)Math.floor(e.posZ);
		ModLoader.openGUI(e, new Y_GuiCrafting(e.inventory, world, i,j,k));
        return itemstack;
    }
}
