package assets.minecessity.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagicBlock extends Block {
	
	public int type,idDrop;
	public MagicBlock(int i,int renderType,int drop)
    {
		super(i,Material.wood);//Material.ground before
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.type=renderType;
		this.idDrop=drop;
    }
	@Override
	@SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess access, int i, int j, int k, int side)
    {
		int meta =access.getBlockMetadata(i, j, k);
		switch(meta){
		case 4:
			if(!access.isAirBlock(i, j-1, k)&& !(Block.blocksList[access.getBlockId(i, j-1, k)] instanceof MagicBlock))
				return Block.blocksList[access.getBlockId(i,j-1,k)].getBlockTexture(access, i,j-1,k, side);
			break;
		case 5:
			if(!access.isAirBlock(i, j+1, k)&& !(Block.blocksList[access.getBlockId(i, j+1, k)] instanceof MagicBlock))
				return Block.blocksList[access.getBlockId(i,j+1,k)].getBlockTexture(access, i,j+1,k, side);
			break;
		case 6:	
			if(!access.isAirBlock(i-1, j, k) && !(Block.blocksList[access.getBlockId(i-1, j, k)] instanceof MagicBlock))
				return Block.blocksList[access.getBlockId(i-1,j,k)].getBlockTexture(access, i-1,j,k, side);
			break;
		case 7:
			if(!access.isAirBlock(i+1, j, k)&& !(Block.blocksList[access.getBlockId(i+1, j, k)] instanceof MagicBlock))
				return Block.blocksList[access.getBlockId(i+1,j,k)].getBlockTexture(access, i+1,j,k, side);
			break;
		case 8:
			if(!access.isAirBlock(i, j, k-1)&& !(Block.blocksList[access.getBlockId(i, j, k-1)] instanceof MagicBlock))
				return Block.blocksList[access.getBlockId(i,j,k-1)].getBlockTexture(access, i,j,k-1, side);
			break;
		case 9:
			if(!access.isAirBlock(i, j, k+1)&& !(Block.blocksList[access.getBlockId(i, j, k+1)] instanceof MagicBlock))
				return Block.blocksList[access.getBlockId(i,j,k+1)].getBlockTexture(access, i,j,k+1, side);
			break;
		case 10:
			if(!access.isAirBlock(i-1, j, k-1)&& !(Block.blocksList[access.getBlockId(i-1, j, k-1)] instanceof MagicBlock))
				return Block.blocksList[access.getBlockId(i-1, j, k-1)].getBlockTexture(access, i-1, j, k-1, side);
			break;
		case 11:
			if(!access.isAirBlock(i+1, j, k+1)&& !(Block.blocksList[access.getBlockId(i+1, j, k+1)] instanceof MagicBlock))
				return Block.blocksList[access.getBlockId(i+1, j, k+1)].getBlockTexture(access, i+1, j, k+1, side);
			break;
		case 12:
			if(!access.isAirBlock(i-1, j, k+1)&& !(Block.blocksList[access.getBlockId(i-1, j, k+1)] instanceof MagicBlock))
				return Block.blocksList[access.getBlockId(i-1,j,k+1)].getBlockTexture(access, i-1,j,k+1, side);
			break;
		case 13:
			if(!access.isAirBlock(i+1, j, k-1)&& !(Block.blocksList[access.getBlockId(i+1, j, k-1)] instanceof MagicBlock))
				return Block.blocksList[access.getBlockId(i+1, j, k-1)].getBlockTexture(access, i+1, j, k-1, side);
			break;
		case 0:case 1:case 2:case 3:default:
			return Block.planks.getIcon(side, meta);
		}
		return Block.planks.getIcon(side, meta);
    }
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    }
	
	@Override
    public int getRenderType()
    {
        return this.type;
    }
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.idDrop;
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
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
		ItemStack stack = entityplayer.inventory.getCurrentItem();
		if(stack!=null && stack.getItem() instanceof ItemFood)
		{
			entityplayer.heal(((ItemFood)stack.getItem()).getHealAmount()*2);
			stack.stackSize--;
			return false;
		}
		else
		{
			this.onBlockClicked(world, i, j, k, entityplayer);
		}
        return true;
    }
	@Override
	public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
		int meta = world.getBlockMetadata(i,j,k);
		if(meta<13)
			meta++;
		else 
			meta=0;
		world.setBlockMetadataWithNotify(i,j,k,meta,3);
		world.notifyBlocksOfNeighborChange(i,j,k,blockID);
    }
	public MagicBlock setBounds(float par1, float par2, float par3, float par4, float par5, float par6){
		this.setBlockBounds(par1, par2, par3, par4, par5, par6);
		return this;
	}
}
