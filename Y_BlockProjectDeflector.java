package mods.minecessity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class Y_BlockProjectDeflector extends Block
{
    protected Y_BlockProjectDeflector(int i)
    {
        super(i, Material.ground);
        setTickRandomly(true);
    }
	
	public int tickRate()
    {
        return 1;
    }
	
	public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
		super.randomDisplayTick(world,i,j,k,random);
		deflectProjectiles(world,i,j,k);
    }
	
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
				if(e instanceof EntityArrow || e instanceof EntitySnowball || e instanceof EntityFireball || e instanceof EntityFX || e instanceof EntityEgg)
				{
					int p3 = new Random().nextInt(5);
					double xM = 0;
					double yM = 0;
					double zM = 0;
					if(p3==0){xM=e.motionX ; yM=e.motionZ ; zM=e.motionY ;}
					if(p3==1){xM=e.motionZ ; yM=e.motionY ; zM=e.motionX ;}
					if(p3==2){xM=e.motionY ; yM=e.motionX ; zM=e.motionZ ;}
					if(p3==3){xM=e.motionY ; yM=e.motionZ ; zM=e.motionX ;}
					if(p3==4){xM=e.motionZ ; yM=e.motionX ; zM=e.motionY ;}
					if(p3==5){xM=e.motionX ; yM=e.motionY ; zM=e.motionZ ;}
					e.setVelocity(xM,yM,zM);
				}
				if(e instanceof EntityLightningBolt)
				{
					e.setPosition(e.posX+new Random().nextGaussian()*4-2, e.posY+new Random().nextGaussian()*4-2, e.posZ+new Random().nextGaussian()*4-2);
				}
				if(e instanceof EntityTNTPrimed)
				{
					if(e.motionY<=0)
					{
						e.setVelocity(new Random().nextGaussian()*2-1 , new Random().nextGaussian()*2 , new Random().nextGaussian()*2-1);
						((EntityTNTPrimed)e).fuse/=new Random().nextFloat()/2+1;
					}
				}
			}
		}
	}
}
