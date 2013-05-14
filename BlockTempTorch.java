package mods.minecessity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class BlockTempTorch extends BlockTorch
{
    protected BlockTempTorch(int i)
    {
        super(i);
    }
    @Override
	public int tickRate(World world)
    {
        return 1;
    }
    @Override
	public void onBlockAdded(World world, int i, int j, int k)
    {
		super.onBlockAdded(world,i,j,k);
		removeIfNoPlayerNearby(world,i,j,k);
    }
    @Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
		super.onNeighborBlockChange(world,i,j,k,l);
		removeIfNoPlayerNearby(world,i,j,k);
    }
    @Override
	public void updateTick(World world, int i, int j, int k, Random random)
    {
		super.updateTick(world,i,j,k,random);
		removeIfNoPlayerNearby(world,i,j,k);
    }
    @Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
		super.randomDisplayTick(world,i,j,k,random);
		removeIfNoPlayerNearby(world,i,j,k);
    }
	
	public void removeIfNoPlayerNearby(World world,int i,int j,int k)
	{
		EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;
		if(player.getDistance(i,j,k)>63)
		{
			player.inventory.addItemStackToInventory(new ItemStack(Minecessity.tempTorch));
			world.setBlockToAir(i,j,k);
		}
	}
}
