package assets.minecessity.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockMobAttract extends Block {
	public BlockMobAttract(int i) {
		super(i, Material.ground);
		setTickRandomly(true);
		setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public int tickRate(World world) {
		return 2;
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		List<?> list = world.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(i - 8, j - 8, k - 8, i + 8, j + 8, k + 8));
		if (!list.isEmpty()) {
			for (int p = 0; p < list.size(); p++) {
				EntityCreature entities = (EntityCreature) list.get(p);
				if (entities.getDistance(i, j, k) > 2) {
					float f = 8F;
					if (entities instanceof EntityMob)
						f = 20F;
					PathEntity path = world.getEntityPathToXYZ(entities, i, j, k, f, false, false, true, true);
					entities.setPathToEntity(path);
				}
			}
		}
	}
}
