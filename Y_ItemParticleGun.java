package mods.minecessity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Y_ItemParticleGun extends Item
{
    protected Y_ItemParticleGun(int i)
    {
        super(i);
		setMaxStackSize(1);
		setMaxDamage(128);
    }

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer e)
    {
		Y_EntityLightBullet bullet = new Y_EntityLightBullet(world,e,1.2F);
		world.spawnEntityInWorld(bullet);
		
		e.motionX *= 0.8;
		e.motionY *= 0.8;
		e.motionZ *= 0.8;
		e.motionX += bullet.motionX*-0.2;
		e.motionY += bullet.motionY*-0.2;
		e.motionZ += bullet.motionZ*-0.2;
		
		itemstack.damageItem(1, e);
        return itemstack;
    }
}
