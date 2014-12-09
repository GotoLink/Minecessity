package assets.minecessity.items;

import assets.minecessity.Minecessity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

public class ItemCactusStick extends Item {
	public ItemCactusStick() {
		super();
		setMaxStackSize(1);
		setMaxDamage(32);
		setFull3D();
		setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int k, boolean flag) {
		super.onUpdate(itemstack, world, entity, k, flag);
		if (flag && entity instanceof EntityPlayer) {
			List<?> list = world.getEntitiesWithinAABB(EntityCreature.class, ((EntityPlayer) entity).boundingBox.expand(1, 1, 1));
			if (!list.isEmpty()) {
				for (Object i : list) {
					EntityCreature entities = (EntityCreature) i;
					if (!entities.isDead && ((EntityPlayer) entity).ticksExisted % 20 == 0) {
						entities.attackEntityFrom(DamageSource.cactus, 2);
						itemstack.damageItem(1, (EntityPlayer) entity);
					}
				}
			}
		}
	}
}
