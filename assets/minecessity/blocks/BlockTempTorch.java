package assets.minecessity.blocks;

import assets.minecessity.Minecessity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class BlockTempTorch extends BlockTorch {
	public BlockTempTorch() {
		super();
        setBlockTextureName("torch_on");
	}

	@Override
	public int tickRate(World world) {
		return 1;
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block l) {
		super.onNeighborBlockChange(world, i, j, k, l);
		removeIfNoPlayerNearby(world, i, j, k);
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		super.updateTick(world, i, j, k, random);
		removeIfNoPlayerNearby(world, i, j, k);
	}

	public void removeIfNoPlayerNearby(World world, int i, int j, int k) {
		EntityPlayer player = world.getClosestPlayer(i, j, k, 63);
		if (player == null) {
			int range = 64;
			while (world.getClosestPlayer(i, j, k, range) == null && range<128) {
				range++;
			}
			player = world.getClosestPlayer(i, j, k, range);
			if(player!=null && player.inventory.addItemStackToInventory(new ItemStack(Minecessity.tempTorch)))
			    world.setBlockToAir(i, j, k);
		}
	}
}
