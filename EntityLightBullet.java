package mods.minecessity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityLightBullet extends EntityThrowable
{
	int maxLife;
	int particlesType;
	public EntityLightBullet(World world)
    {
        super(world);
		setSize(0.02F,0.02F);
		renderDistanceWeight = 4D;
		particlesType = new Random().nextInt(4);
		maxLife=500;
    }
	
	public EntityLightBullet(World world, EntityLiving entity)
	{
		super(world,entity);
		setSize(0.02F,0.02F);
		renderDistanceWeight = 4D;
		particlesType = new Random().nextInt(4);
		maxLife=500;
	}
	@Override
	public float getBrightness(float par1){
		return 1;
	}
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if( !(Math.abs(motionX)<0.05 && Math.abs(motionY)<0.05 && Math.abs(motionZ)<0.05))
		{
			doParticles(worldObj,particlesType);
		}
		/*
		setPosition(posX,posY,posZ);
		moveEntity(motionX,motionY,motionZ);
		motionX*=0.96;
		motionZ*=0.96;
		motionY-=0.03;
		if(onGround)
		{
			motionX*=0.75F ; motionZ*=0.75F ;
		}*/
		
		if(!worldObj.isRemote && ticksExisted>maxLife) 
			setDead();
    }

	public void doParticles(World worldObj, int type)
	{
		for(int p=0 ; p<2 ; p++)
		{
			String s = "";
			int x = new Random().nextInt(3);
			switch(particlesType){
			case 0: /*Fire*/
				s=(x==0?"flame":x==1?"lava":"largesmoke");
				break;
			case 1: /*Explosion*/
				s=(x==0?"explode":x==1?"smoke":"largeexplode");
				break;
			case 2: /*Mystical*/
				s=(x==0?"portal":x==1?"reddust":"slime");
				break;
			case 3: /*Cute*/ 
				s=(x==0?"note":x==1?"heart":"bubble");
				break;
			}
			worldObj.spawnParticle(s,posX,posY+width,posZ,-motionX/4,-motionY/4,-motionZ/4);
		}
	}
	@Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
		super.writeEntityToNBT(nbttagcompound);
    }
	@Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
		super.readEntityFromNBT(nbttagcompound);
    }
	@Override
    public boolean canBeCollidedWith()
    {
        return true;
    }
	@Override
	public boolean canBePushed()
    {
        return true;
    }
	@Override
    public float getCollisionBorderSize()
    {
        return 0F;
    }
    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, int i)
    {
		return false;
    }
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
	}
	
}
