package assets.minecessity.items;

import assets.minecessity.EntityLightBullet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemParticleGun extends Item {
	public ItemParticleGun() {
		super();
		setMaxStackSize(1);
		setMaxDamage(128);
		setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer e) {
        if (!world.isRemote) {
            EntityLightBullet bullet = new EntityLightBullet(world, e);
            e.motionX *= 0.8;
            e.motionY *= 0.8;
            e.motionZ *= 0.8;
            e.motionX += bullet.motionX * -0.2;
            if (e.onGround)
                e.motionY += bullet.motionY * -0.2;
            e.motionZ += bullet.motionZ * -0.2;
            world.spawnEntityInWorld(bullet);
        }
		itemstack.damageItem(1, e);
		return itemstack;
	}
}
