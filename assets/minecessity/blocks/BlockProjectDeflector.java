package assets.minecessity.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockProjectDeflector extends Block {
    static int range = 16;
	public BlockProjectDeflector() {
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
		return 1;
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		deflectProjectiles(world, i, j, k);
        world.scheduleBlockUpdate(i, j, k, this, tickRate(world));
	}

	public void deflectProjectiles(World world, int i, int j, int k) {
		List<?> list = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(i - range, j - range, k - range, i + range, j + range, k + range));
		if (!list.isEmpty()) {
			for (Object p1 : list) {
				Entity e = (Entity) p1;
				if (e instanceof IProjectile || e instanceof EntityFireball) {
                    double xM = e.posX - i; double yM = e.posY - j; double zM = e.posZ - k;
                    if(xM!=0)
					    e.motionX *= 1/xM;
                    if(yM!=0)
                        e.motionY *= 1/yM;
                    if(zM!=0)
                        e.motionZ *= 1/zM;
				}
				if (e instanceof EntityLightningBolt) {
					e.setPosition(e.posX + new Random().nextGaussian() * 4 - 2, e.posY + new Random().nextGaussian() * 4 - 2, e.posZ + new Random().nextGaussian() * 4 - 2);
				}
				if (e instanceof EntityTNTPrimed) {
					if (e.motionY <= 0) {
						//e.setVelocity(new Random().nextGaussian()*2-1 , new Random().nextGaussian()*2 , new Random().nextGaussian()*2-1);
						((EntityTNTPrimed) e).fuse /= new Random().nextFloat() / 2 + 1;
					}
				}
			}
		}
	}
}
