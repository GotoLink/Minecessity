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
    static int range = 16;
	public BlockMobAttract() {
		super(Material.ground);
        setCreativeTab(CreativeTabs.tabRedstone);
	}

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta){
        world.scheduleBlockUpdate(x, y, z, this, tickRate(world));
        return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
    }

	@Override
	public int tickRate(World world) {
		return 2;
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		List<?> list = world.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(i - range, j - range, k - range, i + range, j + range, k + range));
		if (!list.isEmpty()) {
			for (Object p : list) {
				EntityCreature entities = (EntityCreature) p;
				if (entities.getDistance(i, j, k) > 2) {
					float f = 8F;
					if (entities instanceof EntityMob)
						f = 20F;
					PathEntity path = world.getEntityPathToXYZ(entities, i, j, k, f, false, false, true, true);
					entities.setPathToEntity(path);
				}
			}
		}
        world.scheduleBlockUpdate(i, j, k, world.getBlock(i, j, k), tickRate(world));
	}
}
