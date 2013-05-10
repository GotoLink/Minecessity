package mods.minecessity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Y_EntityLightBullet extends Entity
{

    public Y_EntityLightBullet(World world)
    {
        super(world);
		size=0.02F;
        setSize(1*size,1*size);
		renderDistanceWeight = 4D;
		particlesType = new Random().nextInt(4);
		maxLife=500;
		entityBrightness = 1;
    }
	
	public Y_EntityLightBullet(World world, EntityLiving entity, float f)
	{
		this(world);
		Vec3 vec = entity.getLookVec();
		posX = entity.posX + vec.xCoord*2;
		posY = entity.posY + entity.getEyeHeight() + vec.yCoord;
		posZ = entity.posZ + vec.zCoord*2;
		motionX = vec.xCoord*f;
		motionY = vec.yCoord*f;
		motionZ = vec.zCoord*f;
		owner = entity;
		if(entity instanceof EntityPlayer) ownerName = ((EntityPlayer)entity).username;
	}

    protected void entityInit()
    {
    }

    public void onUpdate()
    {
		if((Math.abs(motionX)<0.05 && Math.abs(motionY)<0.05 && Math.abs(motionZ)<0.05)==false)
		{
			doParticles(worldObj,particlesType);
		}
		
        super.onUpdate();
		
		setPosition(posX,posY,posZ);
		moveEntity(motionX,motionY,motionZ);
		motionX*=0.96;
		motionZ*=0.96;
		motionY-=0.03;
		if(onGround)
		{
			motionX*=0.75F ; motionZ*=0.75F ;
		}
		
		if(ticksExisted>maxLife) setDead();
    }
	
	public void doParticles(World worldObj, int type)
	{
		for(int p=0 ; p<2 ; p++)
		{
			String s = "";
			int x = 0;
			if(particlesType==0 /*Fire*/)
			{
				x = new Random().nextInt(4);
				if(x==0) s="flame";
				if(x==1) s="smoke";
				if(x==2) s="lava";
				if(x==3) s="largesmoke";
			}
			if(particlesType==1 /*Explosion*/) 
			{
				x = new Random().nextInt(2);
				if(x==0) s="explode";
				if(x==1) s="smoke";
			}
			if(particlesType==2 /*Mystical*/) 
			{
				x = new Random().nextInt(3);
				if(x==0) s="portal";
				if(x==1) s="reddust";
				if(x==2) s="slime";
			}
			if(particlesType==3 /*Cute*/) 
			{
				x = new Random().nextInt(3);
				if(x==0) s="note";
				if(x==1) s="heart";
				if(x==2) s="bubble";
			}
			worldObj.spawnParticle(s,posX,posY+width,posZ,-motionX/4,-motionY/4,-motionZ/4);
		}
	}

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    }

    public boolean canBeCollidedWith()
    {
        return true;
    }
	
	public boolean canBePushed()
    {
        return true;
    }

    public float getCollisionBorderSize()
    {
        return 0F;
    }

    public boolean attackEntityFrom(Entity entity, int i)
    {
		return false;
    }

    public float getShadowSize()
    {
        return 0.0F;
    }
	
	int maxLife;
	int particlesType;
	float size;
	EntityLiving owner;
	String ownerName;
}
