package mods.minecessity;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityParticleBlock extends TileEntity
{
	int particlesType;
    public TileEntityParticleBlock()
    {
        particlesType=0;
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("P Type",particlesType);
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        particlesType = nbttagcompound.getInteger("P Type");
    }
	
	public void changeType()
	{
		if(particlesType<3) particlesType++;
		else particlesType=0;
		onInventoryChanged();
	}
	
	public void shootParticles(World world,int i,int j,int k)
	{
		EntityLightBullet bullet = new EntityLightBullet(world);
		bullet.setPosition(i+0.5,j+1,k+0.5);
		bullet.maxLife = 100;
		bullet.setVelocity(new Random().nextFloat()/4-1/8, new Random().nextFloat()*3+1,new Random().nextFloat()/4-1/8);
		bullet.particlesType=particlesType;
		if(!world.isRemote)
			world.spawnEntityInWorld(bullet);
	}
}
