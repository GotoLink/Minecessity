package assets.minecessity.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagicBlock extends Block {
	public int type;
	public MagicBlock(int renderType) {
		super(Material.circuits);//Material.ground would affect color
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.type = renderType;
        this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess access, int i, int j, int k, int side) {
		int meta = access.getBlockMetadata(i, j, k);
		switch (meta) {
		case 4:
			if (!access.isAirBlock(i, j - 1, k) && !(access.getBlock(i, j - 1, k) == this))
				return access.getBlock(i, j - 1, k).getIcon(access, i, j - 1, k, side);
			break;
		case 5:
			if (!access.isAirBlock(i, j + 1, k) && !(access.getBlock(i, j + 1, k) == this))
				return access.getBlock(i, j + 1, k).getIcon(access, i, j + 1, k, side);
			break;
		case 6:
			if (!access.isAirBlock(i - 1, j, k) && !(access.getBlock(i - 1, j, k) == this))
				return access.getBlock(i - 1, j, k).getIcon(access, i - 1, j, k, side);
			break;
		case 7:
			if (!access.isAirBlock(i + 1, j, k) && !(access.getBlock(i + 1, j, k) == this))
				return access.getBlock(i + 1, j, k).getIcon(access, i + 1, j, k, side);
			break;
		case 8:
			if (!access.isAirBlock(i, j, k - 1) && !(access.getBlock(i, j, k - 1) == this))
				return access.getBlock(i, j, k - 1).getIcon(access, i, j, k - 1, side);
			break;
		case 9:
			if (!access.isAirBlock(i, j, k + 1) && !(access.getBlock(i, j, k + 1) == this))
				return access.getBlock(i, j, k + 1).getIcon(access, i, j, k + 1, side);
			break;
		case 10:
			if (!access.isAirBlock(i - 1, j, k - 1) && !(access.getBlock(i - 1, j, k - 1) == this))
				return access.getBlock(i - 1, j, k - 1).getIcon(access, i - 1, j, k - 1, side);
			break;
		case 11:
			if (!access.isAirBlock(i + 1, j, k + 1) && !(access.getBlock(i + 1, j, k + 1) == this))
				return access.getBlock(i + 1, j, k + 1).getIcon(access, i + 1, j, k + 1, side);
			break;
		case 12:
			if (!access.isAirBlock(i - 1, j, k + 1) && !(access.getBlock(i - 1, j, k + 1) == this))
				return access.getBlock(i - 1, j, k + 1).getIcon(access, i - 1, j, k + 1, side);
			break;
		case 13:
			if (!access.isAirBlock(i + 1, j, k - 1) && !(access.getBlock(i + 1, j, k - 1) instanceof MagicBlock))
				return access.getBlock(i + 1, j, k - 1).getIcon(access, i + 1, j, k - 1, side);
			break;
		default:
			return Blocks.planks.getIcon(side, meta);
		}
		return Blocks.planks.getIcon(side, meta);
	}

	@Override
	public int getRenderType() {
		return this.type;
	}

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		ItemStack stack = entityplayer.inventory.getCurrentItem();
		if (stack != null && stack.getItem() instanceof ItemFood) {
			entityplayer.heal(((ItemFood) stack.getItem()).func_150905_g(stack) * 2);
			stack.stackSize--;
			return false;
		} else {
			this.onBlockClicked(world, i, j, k, entityplayer);
		}
		return true;
	}

	@Override
	public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer) {
		int meta = world.getBlockMetadata(i, j, k);
		if (meta < 13)
			meta++;
		else
			meta = 0;
		world.setBlockMetadataWithNotify(i, j, k, meta, 3);
		world.notifyBlockChange(i, j, k, this);
	}

	public MagicBlock setBounds(float par1, float par2, float par3, float par4, float par5, float par6) {
		this.setBlockBounds(par1, par2, par3, par4, par5, par6);
		return this;
	}
}
