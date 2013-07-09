package assets.minecessity.blocks;

import java.util.List;
import java.util.Random;

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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockProjectDeflector extends Block
{
    public BlockProjectDeflector(int i)
    {
        super(i, Material.ground);
        setTickRandomly(true);
        setCreativeTab(CreativeTabs.tabRedstone);
    }
    @Override
	public int tickRate(World world)
    {
        return 1;
    }
    @Override
	public void updateTick(World world, int i, int j, int k, Random random)
    {
		super.updateTick(world,i,j,k,random);
		deflectProjectiles(world,i,j,k);
    }

	public void deflectProjectiles(World world, int i, int j, int k)
	{
		int p = 16;
		List list = world.getEntitiesWithinAABB(Entity.class , AxisAlignedBB.getBoundingBox(i-p,j-p,k-p,i+p,j+p,k+p));
		if(!list.isEmpty())
		{
			for(int p1 = 0 ; p1<list.size() ; p1++)
			{
				Entity e = (Entity)list.get(p1);
				if(e instanceof IProjectile || e instanceof EntityFireball /*|| e instanceof EntityFX*/)
				{
					int p3 = new Random().nextInt(5);
					double xM = 0;
					double yM = 0;
					double zM = 0;
					switch(p3){
						case 0:{xM=e.motionX ; yM=e.motionZ ; zM=e.motionY ;break;}
						case 1:{xM=e.motionZ ; yM=e.motionY ; zM=e.motionX ;break;}
						case 2:{xM=e.motionY ; yM=e.motionX ; zM=e.motionZ ;break;}
						case 3:{xM=e.motionY ; yM=e.motionZ ; zM=e.motionX ;break;}
						case 4:{xM=e.motionZ ; yM=e.motionX ; zM=e.motionY ;break;}
					}
					//e.setVelocity(xM,yM,zM);
				}
				if(e instanceof EntityLightningBolt)
				{
					e.setPosition(e.posX+new Random().nextGaussian()*4-2, e.posY+new Random().nextGaussian()*4-2, e.posZ+new Random().nextGaussian()*4-2);
				}
				if(e instanceof EntityTNTPrimed)
				{
					if(e.motionY<=0)
					{
						//e.setVelocity(new Random().nextGaussian()*2-1 , new Random().nextGaussian()*2 , new Random().nextGaussian()*2-1);
						((EntityTNTPrimed)e).fuse/=new Random().nextFloat()/2+1;
					}
				}
			}
		}
	}
}
