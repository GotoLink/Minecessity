package assets.minecessity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;

public class EntityLightBullet extends EntityThrowable implements IEntityAdditionalSpawnData{
	public final int maxLife;
	protected int particlesType;

	public EntityLightBullet(World world) {
		super(world);
		this.setSize(0.02F, 0.02F);
		this.renderDistanceWeight = 4D;
		this.maxLife = 100;
	}

    public EntityLightBullet(World world, int type){
        this(world);
        this.setParticleType(type);
    }

	public EntityLightBullet(World world, EntityLivingBase entity) {
		super(world, entity);
		this.setSize(0.02F, 0.02F);
		this.renderDistanceWeight = 4D;
		this.maxLife = 500;
		this.particlesType = rand.nextInt(4);
	}

	@Override
	public float getBrightness(float par1) {
		return 1;
	}

	@Override
	public void onUpdate() {
		if (!(Math.abs(this.motionX) < 0.05 && Math.abs(this.motionY) < 0.05 && Math.abs(this.motionZ) < 0.05)) {
			String s;
			int x;
			for (int p = 0; p < 2; p++) {
				x = rand.nextInt(3);
				switch (this.particlesType) {
				case 0:/* Fire */
					s = (x == 0 ? "flame" : x == 1 ? "lava" : "largesmoke");
					break;
				case 1:/* Explosion */
					s = (x == 0 ? "explode" : x == 1 ? "smoke" : "largeexplode");
					break;
				case 2:/* Mystical */
					s = (x == 0 ? "portal" : x == 1 ? "reddust" : "slime");
					break;
				default:/* Cute */
					s = (x == 0 ? "note" : x == 1 ? "heart" : "bubble");
					break;
				}
				this.worldObj.spawnParticle(s, posX, posY + width, posZ, -motionX / 4, -motionY / 4, -motionZ / 4);
			}
		}
		if (!this.worldObj.isRemote && this.ticksExisted > this.maxLife)
			setDead();
		super.onUpdate();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("type", this.particlesType);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.particlesType = par1NBTTagCompound.getInteger("type");
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public float getCollisionBorderSize() {
		return 0F;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float i) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}

	public EntityLightBullet setParticleType(int type) {
		this.particlesType = type;
		return this;
	}

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeByte(particlesType);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        setParticleType(additionalData.readByte());
    }
}
