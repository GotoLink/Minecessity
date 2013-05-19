package mods.minecessity.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagicBlock extends Block {
	
	protected MagicBlock(int i)
    {
		super(i,Material.wood);//Material.ground before
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.blockIcon=Block.planks.getIcon(0, 0);
    }
    
    @Override
    public int tickRate(World par1World)
    {
        return 1;
    }
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
		world.notifyBlocksOfNeighborChange(i,j,k,blockID);
    }
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
		ItemStack stack = entityplayer.inventory.getCurrentItem();
		if(stack!=null)
		{
			Item item = stack.getItem();
			if(item instanceof ItemFood)
			{
				entityplayer.heal(((ItemFood)item).getHealAmount()*2);
				stack.stackSize--;
				return false;
			}
		}
        return true;
    }
	@Override
	public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
		int meta = world.getBlockMetadata(i,j,k);
		
		if(meta<7)
			meta++;
		else 
			meta=0;
		
		world.setBlockMetadataWithNotify(i,j,k,meta,3);
		if(!world.isRemote){
			if(meta==0) blockIcon = Block.planks.getIcon(0, 0);
			if(meta==1 && !world.isAirBlock(i,j-1,k))
				blockIcon = Block.blocksList[world.getBlockId(i,j-1,k)].getBlockTexture(world, i,j-1,k, 0);
			if(meta==2 && !world.isAirBlock(i,j+1,k))
				blockIcon = Block.blocksList[world.getBlockId(i,j+1,k)].getBlockTexture(world, i,j+1,k, 0);
			if(meta==3 && !world.isAirBlock(i-1,j,k))
				blockIcon = Block.blocksList[world.getBlockId(i-1,j,k)].getBlockTexture(world, i-1,j,k, 0);
			if(meta==4 && !world.isAirBlock(i+1,j,k))
				blockIcon = Block.blocksList[world.getBlockId(i+1,j,k)].getBlockTexture(world, i+1,j,k, 0);
			if(meta==5 && !world.isAirBlock(i,j,k-1))
				blockIcon = Block.blocksList[world.getBlockId(i,j,k-1)].getBlockTexture(world, i,j,k-1, 0);
			if(meta==6 && !world.isAirBlock(i,j,k+1))
				blockIcon = Block.blocksList[world.getBlockId(i,j,k+1)].getBlockTexture(world, i,j,k+1, 0);
			if(meta==7)
			{
				ItemStack item = entityplayer.inventory.getCurrentItem();
				if(item!=null && item.getItem() instanceof ItemBlock)
				{
					blockIcon = Block.blocksList[item.itemID].getBlockTexture(world, i,j-1,k, 0);
				}
			}
		}
		world.notifyBlocksOfNeighborChange(i,j,k,blockID);
    }
}
