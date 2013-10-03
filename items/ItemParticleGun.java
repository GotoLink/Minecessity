package assets.minecessity.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import assets.minecessity.EntityLightBullet;

public class ItemParticleGun extends Item
{
    public ItemParticleGun(int i)
    {
        super(i);
		setMaxStackSize(1);
		setMaxDamage(128);
		setCreativeTab(CreativeTabs.tabRedstone);
    }
    @Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer e)
    {
		EntityLightBullet bullet = new EntityLightBullet(world,e);
		if(!world.isRemote)
			world.spawnEntityInWorld(bullet);
		
		e.motionX *= 0.8;
		e.motionY *= 0.8;
		e.motionZ *= 0.8;
		e.motionX += bullet.motionX*-0.2;
		if(e.onGround)
			e.motionY += bullet.motionY*-0.2;
		e.motionZ += bullet.motionZ*-0.2;
		
		itemstack.damageItem(1, e);
        return itemstack;
    }
}
