package mods.minecessity.items;

import mods.minecessity.Minecessity;
import mods.minecessity.Y_GuiCrafting;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class ItemPrtbWorkBence extends Item
{
    public ItemPrtbWorkBence(int i)
    {
        super(i);
		setMaxStackSize(1);
		setMaxDamage(100);
		setCreativeTab(CreativeTabs.tabTools);
    }
    @Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer e)
    {
		int i = (int)Math.floor(e.posX);
		int j = (int)Math.floor(e.boundingBox.minY);
		int k = (int)Math.floor(e.posZ);
		e.openGui(Minecessity.instance,Minecessity.proxy.GUI_ID,world,i,j,k);
        return itemstack;
    }
}
