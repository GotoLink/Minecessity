package mods.minecessity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Y_ItemChair extends Item
{
    protected Y_ItemChair(int i)
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
		
		int dire = MathHelper.floor_double((double)((entityplayer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		
		world.setBlock(i,j,k,Minecessity.chair.blockID);
		if(world.getBlockId(i,j,k)==Minecessity.chair.blockID)
		{
			//((Y_BlockChair)Block.blocksList[world.getBlockId(i,j,k)]).
			itemstack.stackSize--;
		}
		return true;
	}
}
