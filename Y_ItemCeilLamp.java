package mods.minecessity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Y_ItemCeilLamp extends Item
{
    protected Y_ItemCeilLamp(int i)
    {
        super(i);
    }

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
		super.onItemUse(itemstack,entityplayer,world,i,j,k,l);
		
		if(l==0)j--;
		if(l==1)j++;
		if(l==2)k--;
		if(l==3)k++;
		if(l==4)i--;
		if(l==5)i++;
		
		if(!world.isAirBlock(i,j+1,k))
			world.setBlock(i,j,k,Minecessity.ceilLamp.blockID);
			
		if(world.getBlockId(i,j,k)==Minecessity.ceilLamp.blockID)itemstack.stackSize--;
		return true;
	}
}
