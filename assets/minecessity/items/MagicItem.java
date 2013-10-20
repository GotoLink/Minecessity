package assets.minecessity.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MagicItem extends Item {
	protected int block;

	public MagicItem(int i, int blockID) {
		super(i);
		this.block = blockID;
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int i, int j, int k, int l, float f1, float f2, float f3) {
		//super.onItemUse(itemStack,player,world,i,j,k,l, f1, f2, f3);
		if (l == 0)
			j--;
		if (l == 1)
			j++;
		if (l == 2)
			k--;
		if (l == 3)
			k++;
		if (l == 4)
			i--;
		if (l == 5)
			i++;
		world.setBlock(i, j, k, block);
		if (world.getBlockId(i, j, k) == block)
			itemStack.stackSize--;
		return true;
	}
}
