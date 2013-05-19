package mods.minecessity.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockMobAttract extends Block
{
    public BlockMobAttract(int i)
    {
        super(i, Material.ground);
        setTickRandomly(true);
    }
    @Override
	public int tickRate(World world)
    {
        return 2;
    }
    @Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
		List list = world.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(i,j,k,i+1,j+1,k+1).expand(16,16,16));
		if(!list.isEmpty())
		{
			for(int p=0 ; p<list.size() ; p++)
			{
				EntityCreature entities = (EntityCreature)list.get(p);
				if(entities.getDistance(i,j,k)>2)
				{
					float f = 8F;
					if(entities instanceof EntityMob) f=20F;
					PathEntity path = world.getEntityPathToXYZ(entities,i,j,k,f,false,false,true,true);
					entities.setPathToEntity(path);
				}
			}
		}
    }
}
