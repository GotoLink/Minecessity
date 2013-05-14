package mods.minecessity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
@Mod(modid = "minecessity",name="Minecessity",version="alpha")
@NetworkMod(clientSideRequired=true,serverSideRequired=false)
public class Minecessity 
{
	@Instance("minecessity")
	public static Minecessity instance;
	@SidedProxy(clientSide="mods.minecessity.ClientProxy",serverSide="mods.minecessity.CommonProxy")
	public static CommonProxy proxy;
	public static Block table,mobAttractor,projDeflector,particleBlock,chair,ceilLamp;
	public static BlockTorch tempTorch;
	public static Item tableItem,chairItem,ceilLampItem;
	public static Item cactusStick,portableWorkBench,particleGun;
	public static int tableBlockId,mobAttractorId,temporaryTorchId,deflectorId,particleBlockId,
	chairBlockId,ceilLampBlockId,tableItemId,cactusPickerId,portableWorkBenchId,particleGunId,
	chairItemId,ceilLampItemId,deflectorEffectiveRange,slimeSpawnLimit;
	private Configuration config;
	
	@PreInit
	public void prepareProps(FMLPreInitializationEvent event)
	{
		config =new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		tableBlockId =config.get("block","Table ID",410).getInt();
		mobAttractorId =config.get("block","Mob Attractor ID",411).getInt();
		temporaryTorchId =config.get("block","Temporary Torch ID",412).getInt();
		deflectorId =config.get("block","Deflector ID",413).getInt();
		particleBlockId =config.get("block","Particle block ID",414).getInt();
		chairBlockId =config.get("block","Chair ID",415).getInt();
		ceilLampBlockId =config.get("block","Ceiling Lamp ID",416).getInt();
		
		tableItemId =config.get("item","Table ID",26508).getInt();
		cactusPickerId =config.get("item","Cactus Picker ID",26509).getInt();
		portableWorkBenchId =config.get("item","Portable Workbench ID",26510).getInt();
		particleGunId =config.get("item","Particles Gun ID",26511).getInt();
		chairItemId =config.get("item","Chair ID",26512).getInt();
		ceilLampItemId =config.get("item","Ceiling Lamp ID",26513).getInt();
		
		deflectorEffectiveRange =config.get("general","Deflector Effective Range",8).getInt();
		slimeSpawnLimit=config.get("general","Slime spawn limit by chunk",3).getInt();
		if(config.hasChanged())
			config.save();
	}
	@Init
    public void load(FMLInitializationEvent event)
    {
    	table = new BlockTable(tableBlockId).setUnlocalizedName("wood");
    	mobAttractor = new BlockMobAttract(mobAttractorId).setHardness(0.5F).setUnlocalizedName("minecessity:mobAttractor");
    	tempTorch = (BlockTorch)(new BlockTempTorch(temporaryTorchId)).setLightValue(0.9375F).setUnlocalizedName("torch");
    	projDeflector = new BlockProjectDeflector(deflectorId).setHardness(0.85F).setUnlocalizedName("minecessity:deflector");
    	particleBlock = new BlockParticle(particleBlockId).setHardness(0.4F).setUnlocalizedName("minecessity:particleBlock");
    	chair = new BlockChair(chairBlockId).setUnlocalizedName("wood");
    	ceilLamp = new BlockCeilLamp(ceilLampBlockId).setUnlocalizedName("wood");
    	
    	tableItem = new MagicItem(tableItemId,tableBlockId).setUnlocalizedName("minecessity:table");
    	cactusStick = new ItemCactusStick(cactusPickerId).setUnlocalizedName("minecessity:cactusPicker");
    	portableWorkBench = new ItemPrtbWorkBence(portableWorkBenchId).setUnlocalizedName("minecessity:portableWorkBench");
    	particleGun = new ItemParticleGun(particleGunId).setUnlocalizedName("minecessity:particleGun");
    	chairItem = new MagicItem(chairItemId,chairBlockId).setUnlocalizedName("minecessity:chair");
    	ceilLampItem = new ItemCeilLamp(ceilLampItemId,ceilLampBlockId).setUnlocalizedName("minecessity:ceilLamp");
    	
    	registerBlocksItemsRecipes();
    	proxy.registerRenderer();
    	EntityRegistry.registerModEntity(Y_EntitySlime.class, "YSlime",1,this,40,1,true);
		EntityRegistry.addSpawn(Y_EntitySlime.class, 1, 1, 5, EnumCreatureType.monster,WorldType.base12Biomes);
		
		GameRegistry.registerTileEntity(TileEntityParticleBlock.class,"Particle Block Tile Entity");
	
	}
	
	public void registerBlocksItemsRecipes()
	{
		GameRegistry.registerItem(tableItem, "Table");
		//GameRegistry.registerBlock(table,"Table");
		LanguageRegistry.instance().addNameForObject(tableItem,"en_US","Table");
		GameRegistry.addRecipe(new ItemStack(tableItem,1),new Object[]{
			"XLX"," Y "," X ", 'X',Block.planks , 'L',Block.lever , 'Y',Item.ingotIron
		});
		
		GameRegistry.registerItem(chairItem, "Chair");
		//GameRegistry.registerBlock(chair,"Chair");
		LanguageRegistry.instance().addNameForObject(chairItem,"en_US","Chair");
		GameRegistry.addRecipe(new ItemStack(chairItem,1),new Object[]{
			" W "," LI"," WW", 'W',Block.planks , 'L',Block.lever , 'I',Item.ingotIron
		});
		
		GameRegistry.registerItem(ceilLampItem, "Ceiling Lamp");
		//GameRegistry.registerBlock(ceilLamp,"Ceiling Lamp");
		LanguageRegistry.instance().addNameForObject(ceilLampItem,"en_US","Ceiling Lamp");
		GameRegistry.addRecipe(new ItemStack(ceilLampItem,1),new Object[]{
			" W ", " L ", "GIG" , 'W',Block.planks, 'L',Block.lever, 'G',Block.glowStone, 'I',Item.ingotIron
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
		
		GameRegistry.registerItem(cactusStick,"Cactus Picker");
		LanguageRegistry.instance().addNameForObject(cactusStick,"en_US","Cactus Picker");
		GameRegistry.addRecipe(new ItemStack(cactusStick,1),new Object[]{
			"X","X","S" , 'X',Block.cactus , 'S',Item.stick
		});
		
		GameRegistry.registerItem(portableWorkBench,"Portable Workbench");
		LanguageRegistry.instance().addNameForObject(portableWorkBench,"en_US","Portable Workbench");
		GameRegistry.addRecipe(new ItemStack(portableWorkBench,1),new Object[]{
			"ILI", "RWR", "IRI", 'W', Block.workbench , 'I' , Item.ingotIron , 'R' , Item.redstone , 'L' , Block.lever
		});
		
		GameRegistry.registerItem(particleGun,"Particles Gun");
		LanguageRegistry.instance().addNameForObject(particleGun,"en_US","Particles Gun");
		GameRegistry.addRecipe(new ItemStack(particleGun,1),new Object[]{
			"123", " 45", " 6I" , 'I',Item.ingotIron, '1',new ItemStack(Item.dyePowder,1), '2',new ItemStack(Item.dyePowder,2)
			, '3',new ItemStack(Item.dyePowder,4), '4',new ItemStack(Item.dyePowder,7), '5',new ItemStack(Item.dyePowder,8)
			, '6',new ItemStack(Item.dyePowder,15)
		});
    }
	@Deprecated
	public boolean OnTickInGame(Minecraft minecraft)
    {
		EntityPlayer player = minecraft.thePlayer;
		
		int x = deflectorEffectiveRange;
		for(int i = (int)Math.floor(player.posX)-x ; i <(int)Math.floor(player.posX)+x ; i++){
		for(int j = (int)Math.floor(player.boundingBox.minY)-x ; j <(int)Math.floor(player.boundingBox.maxY)+x ; j++){
		for(int k = (int)Math.floor(player.posZ)-x ; k <(int)Math.floor(player.posZ)+x ; k++){
		
			if(minecraft.theWorld.getBlockId(i,j,k)==projDeflector.blockID) 
				((BlockProjectDeflector)projDeflector).deflectProjectiles(minecraft.theWorld,i,j,k);
			
		}}}
        return true;
    }
	
	
	
	
}
