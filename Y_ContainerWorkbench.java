package mods.minecessity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class Y_ContainerWorkbench extends ContainerWorkbench
{

    public Y_ContainerWorkbench(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
    {
        super(inventoryplayer, world, i, j, k);
    }

    public boolean isUsableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }
}
