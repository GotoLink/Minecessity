package assets.minecessity;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class LavaEntitySlime extends EntitySlime {
	public LavaEntitySlime(World world) {
		super(world);
	}

	@Override
	public boolean getCanSpawnHere() {
		boolean nearLava = false;
		int i = (int) Math.floor(posX);
		int j = (int) Math.floor(posY);
		int k = (int) Math.floor(posZ);
		for (int i1 = i - 16; i1 <= i + 16; i1++) {
			for (int j1 = j - 6; j1 <= j + 6; j1++) {
				for (int k1 = k - 16; k1 <= k + 16; k1++) {
					if (worldObj.getBlockMaterial(i1, j1, k1) == Material.lava)
						nearLava = true;
				}
			}
		}
		return posY < 16D && nearLava && new Random().nextInt(20) == 0;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		int i = Minecessity.slimeSpawnLimit;
		return i < 0 ? 0 : i;
	}

	@Override
	protected int getDropItemId() {
		return Item.slimeBall.itemID;
	}
}
