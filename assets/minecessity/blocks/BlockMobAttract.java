package assets.minecessity.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockMobAttract extends Block {
    public static int range = 16;
	public BlockMobAttract() {
		super(Material.circuits);
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
					PathEntity path = world.getEntityPathToXYZ(entities, i, j, k, f, true, true, false, true);
					entities.setPathToEntity(path);
                    entities.getNavigator().setPath(path, f/10);
				}
			}
		}
        world.scheduleBlockUpdate(i, j, k, world.getBlock(i, j, k), tickRate(world));
	}
}
