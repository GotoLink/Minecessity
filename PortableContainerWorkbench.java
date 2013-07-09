package assets.minecessity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class PortableContainerWorkbench extends ContainerWorkbench
{
    public PortableContainerWorkbench(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
    {
        super(inventoryplayer, world, i, j, k);
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
    	return true;
    }
}
