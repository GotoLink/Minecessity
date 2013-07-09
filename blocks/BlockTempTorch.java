package assets.minecessity.blocks;

import java.util.Random;

import assets.minecessity.Minecessity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockTempTorch extends BlockTorch
{
    public BlockTempTorch(int i)
    {
        super(i);
        func_111022_d("torch_on");
    }
    @Override
	public int tickRate(World world)
    {
        return 1;
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
	
	public void removeIfNoPlayerNearby(World world,int i,int j,int k)
	{
		EntityPlayer player = world.getClosestPlayer(i, j, k, 63);
		if (player==null)
		{
			int range=64;
			while (world.getClosestPlayer(i, j, k, range)==null){
				range++;
			}
			player= world.getClosestPlayer(i, j, k, range);
			player.inventory.addItemStackToInventory(new ItemStack(Minecessity.tempTorch));
			world.setBlockToAir(i,j,k);
		}
	}
}
