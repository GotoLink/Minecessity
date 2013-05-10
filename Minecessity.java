package mods.minecessity;

import java.io.File;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
@Mod(modid = "minecessity",version="alpha")
@NetworkMod(clientSideRequired=true,serverSideRequired=false)
public class Minecessity
{
	@Instance("minecessity")
	public static Minecessity instance;
	@SidedProxy()
	public static CommonProxy proxy;
	public static String dir = (new StringBuilder()).append(Minecraft.getMinecraftDir()).append("/").append("Minecessity.properties").toString();
    public static Y_PropFile props=new Y_PropFile((new File(dir)).getPath());
    public static Block table,mobAttractor,projDeflector,particleBlock,chair,ceilLamp;
	public static BlockTorch tempTorch;
	public static Item tableItem,cactusStick,portableWorkBench,particleGun,chairItem,ceilLampItem;
	public static void prepareProps()
	{
		props.getInt("Table block ID",110);
		props.getInt("Mob Attractor block ID",111);
		props.getInt("Temporary Torch block ID",112);
		props.getInt("Deflector block ID",113);
		props.getInt("Particle block ID",114);
		props.getInt("Chair block ID",115);
		props.getInt("Celing Lamp blok ID",116);
		
		props.getInt("Table item ID",26508);
		props.getInt("Cactus Picker item ID",26509);
		props.getInt("Portable Workbench item ID",26510);
		props.getInt("Particles Gun item ID",26511);
		props.getInt("Chair item ID",26512);
		props.getInt("Celing Lamp item ID",26513);
		
		props.getInt("Deflector Effective Range",8);
		props.getInt("Slime spawn limit each chunk",3);
	}
	
	public static int rendererTable,rendererChair,rendererCeilLamp;
	
	public static int TableBlockId = props.getInt("Table block ID");
	public static int MobAttractorId = props.getInt("Mob Attractor block ID");
	public static int TemporaryTorchId = props.getInt("Temporary Torch block ID");
	public static int DeflectorId = props.getInt("Deflector block ID");
	public static int ChairBlockId = props.getInt("Chair block ID");
	public static int ParticleBlock = props.getInt("Particle block ID");
	public static int CeilLampBlockId = props.getInt("Celing Lamp blok ID");
	
	public static int TableItemId = props.getInt("Table item ID");
	public static int CactusPickerId = props.getInt("Cactus Picker item ID");
	public static int PortableWorkBenchId = props.getInt("Portable Workbench item ID");
	public static int ParticleGunId = props.getInt("Particles Gun item ID");
	public static int ChairItemId = props.getInt("Chair item ID");
	public static int CeilLampItemId = props.getInt("Celing Lamp item ID");
	
	public static int DeflectorEffectiveRange = props.getInt("Deflector Effective Range");
	
    protected Minecessity()
    {
    	prepareProps();

    	table = new Y_BlockTable(TableBlockId).setHardness(0.5F).setName("minecessity:table");
    	mobAttractor = new Y_BlockMobAttract(MobAttractorId).setHardness(0.5F).setName("minecessity:mobAttractor");
    	tempTorch = (BlockTorch)(new Y_BlockTempTorch(TemporaryTorchId)).setLightValue(0.9375F).setName("tempTorch");
    	projDeflector = new Y_BlockProjectDeflector(DeflectorId).setHardness(0.85F).setName("minecessity:deflector");
    	particleBlock = new Y_BlockParticle(ParticleBlock).setHardness(0.4F).setName("minecessity:particleBlock");
    	chair = new Y_BlockChair(ChairBlockId).setHardness(0.5F).setName("minecessity:chair");
    	ceilLamp = new Y_BlockCeilLamp(CeilLampBlockId).setLightValue(1F).setHardness(0.5F).setName("minecessity:ceilLamp");
    	
    	tableItem = (new Y_ItemTable(TableItemId)).setName("tableItem");
    	cactusStick = (new Y_ItemCactusStick(CactusPickerId)).setName("minecessity:cactusPicker");
    	portableWorkBench = (new Y_ItemPrtbWorkBence(PortableWorkBenchId)).setName("minecessity:portableWorkBench");
    	particleGun = (new Y_ItemParticleGun(ParticleGunId)).setName("minecessity:particleGun");
    	chairItem = (new Y_ItemChair(ChairItemId)).setName("chairItem");
    	ceilLampItem = (new Y_ItemCeilLamp(CeilLampItemId)).setName("ceilLampItem");
		//RenderingRegistry.registerBlockHandler();
		/*rendererTable = ModLoader.getUniqueBlockModelID(this, false);
		rendererChair = ModLoader.getUniqueBlockModelID(this, false);
		rendererCeilLamp = ModLoader.getUniqueBlockModelID(this, false);*/
		
		RegisterBlocksItemsRecipes();
		
		EntityRegistry.addSpawn(Y_EntitySlime.class, 1, 1, 5, EnumCreatureType.monster,WorldType.base12Biomes);
		EntityRegistry.registerModEntity(Y_EntitySlime.class, "YSlime",1,this,0,0,false);
		
		GameRegistry.registerTileEntity(Y_TileEntityParticleBlock.class,"Particle Block Tile Entity");
	}
	
	public void AddRenderer(Map map)
    {
        map.put(Y_EntityLightBullet.class, new Y_RenderLightBullet());
    }
	
	public void RegisterBlocksItemsRecipes()
	{
		GameRegistry.registerBlock(table,"Table");
		LanguageRegistry.instance().addNameForObject(table,"en_US","Table");
		LanguageRegistry.instance().addNameForObject(tableItem,"en_US","Table");
		GameRegistry.addRecipe(new ItemStack(tableItem,1),new Object[]{
			"XLX"," Y "," X ", 'X',Block.planks , 'L',Block.lever , 'Y',Item.ingotIron
		});
		
		GameRegistry.registerBlock(mobAttractor,"Mob Attractor");
		LanguageRegistry.instance().addNameForObject(mobAttractor,"en_US","Mob Attractor");
		GameRegistry.addRecipe(new ItemStack(mobAttractor,1),new Object[]{
			"XYX","YBY","XYX", 'X',Block.cobblestone, 'Y',Block.sapling, 'B',Item.bone
		});
		
		GameRegistry.registerBlock(tempTorch,"Temporary Torch");
		LanguageRegistry.instance().addNameForObject(tempTorch,"en_US","Temporary Torch");
		GameRegistry.addRecipe(new ItemStack(tempTorch,1),new Object[]{
			"X",'X',Block.torchWood
		});
		GameRegistry.addRecipe(new ItemStack(Block.torchWood,1),new Object[]{
			"X",'X',tempTorch
		});
		
		GameRegistry.registerBlock(projDeflector,"Deflector");
		LanguageRegistry.instance().addNameForObject(projDeflector,"en_US","Deflector");
		GameRegistry.addRecipe(new ItemStack(projDeflector,1),new Object[]{
			"GLG","B B","GLG" , 'G',Item.ingotGold , 'L',Block.lever , 'B',Block.stoneButton
		});
		
		GameRegistry.registerBlock(particleBlock,"Particles Block");
		LanguageRegistry.instance().addNameForObject(particleBlock,"en_US","Particles Block");
		GameRegistry.addRecipe(new ItemStack(particleBlock,1),new Object[]{
			"CGC", "CDC", "CCC" , 'C',Block.cobblestone, 'G',Block.glass, 'D',particleGun
		});

		GameRegistry.registerBlock(chair,"Chair");
		LanguageRegistry.instance().addNameForObject(chair,"en_US","Chair");
		LanguageRegistry.instance().addNameForObject(chairItem,"en_US","Chair");
		GameRegistry.addRecipe(new ItemStack(chairItem,1),new Object[]{
			" W "," LI"," WW", 'W',Block.planks , 'L',Block.lever , 'I',Item.ingotIron
		});
		GameRegistry.registerBlock(ceilLamp,"Ceiling Lamp");
		LanguageRegistry.instance().addNameForObject(ceilLamp,"en_US","Ceiling Lamp");
		LanguageRegistry.instance().addNameForObject(ceilLampItem,"en_US","Ceiling Lamp");
		GameRegistry.addRecipe(new ItemStack(ceilLampItem,1),new Object[]{
			" W ", " L ", "GIG" , 'W',Block.planks, 'L',Block.lever, 'G',Block.glowStone, 'I',Item.ingotIron
		});
		
		
		
		LanguageRegistry.instance().addNameForObject(cactusStick,"en_US","Cactus Picker");
		GameRegistry.addRecipe(new ItemStack(cactusStick,1),new Object[]{
			"X","X","S" , 'X',Block.cactus , 'S',Item.stick
		});
		
		LanguageRegistry.instance().addNameForObject(portableWorkBench,"en_US","Portable Workbench");
		GameRegistry.addRecipe(new ItemStack(portableWorkBench,1),new Object[]{
			"ILI", "RWR", "IRI", 'W', Block.workbench , 'I' , Item.ingotIron , 'R' , Item.redstone , 'L' , Block.lever
		});
		
		LanguageRegistry.instance().addNameForObject(particleGun,"en_US","Particles Gun");
		GameRegistry.addRecipe(new ItemStack(particleGun,1),new Object[]{
			"123", " 45", " 6I" , 'I',Item.ingotIron, '1',new ItemStack(Item.dyePowder,1), '2',new ItemStack(Item.dyePowder,2)
			, '3',new ItemStack(Item.dyePowder,4), '4',new ItemStack(Item.dyePowder,7), '5',new ItemStack(Item.dyePowder,8)
			, '6',new ItemStack(Item.dyePowder,15)
		});
    }
	
	public boolean OnTickInGame(Minecraft minecraft)
    {
		EntityPlayer player = minecraft.thePlayer;
		
		int x = DeflectorEffectiveRange;
		for(int i = (int)Math.floor(player.posX)-x ; i <(int)Math.floor(player.posX)+x ; i++){
		for(int j = (int)Math.floor(player.boundingBox.minY)-x ; j <(int)Math.floor(player.boundingBox.maxY)+x ; j++){
		for(int k = (int)Math.floor(player.posZ)-x ; k <(int)Math.floor(player.posZ)+x ; k++){
		
			if(minecraft.theWorld.getBlockId(i,j,k)==projDeflector.blockID) 
				((Y_BlockProjectDeflector)projDeflector).deflectProjectiles(minecraft.theWorld,i,j,k);
			
		}}}
        return true;
    }
	
	public boolean RenderWorldBlock(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block, int l) 
	{
        if (l == rendererTable) 
		{
            new Y_NewRender().renderTable(renderblocks, iblockaccess, i, j, k, block);
            return true;
        }
		if (l == rendererChair) 
		{
            new Y_NewRender().renderChair(renderblocks, iblockaccess, i, j, k, block);
            return true;
        }
		if (l == rendererCeilLamp) 
		{
            new Y_NewRender().renderCeilLamp(renderblocks, iblockaccess, i, j, k, block);
            return true;
        }
        return false;
    }
	
	
}
