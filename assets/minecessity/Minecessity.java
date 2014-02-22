package assets.minecessity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import assets.minecessity.blocks.BlockMobAttract;
import assets.minecessity.blocks.BlockParticle;
import assets.minecessity.blocks.BlockProjectDeflector;
import assets.minecessity.blocks.BlockTempTorch;
import assets.minecessity.blocks.MagicBlock;
import assets.minecessity.items.ItemCactusStick;
import assets.minecessity.items.ItemCeilLamp;
import assets.minecessity.items.ItemParticleGun;
import assets.minecessity.items.ItemPrtbWorkBence;
import assets.minecessity.items.MagicItem;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "minecessity", name = "Minecessity", version = "0.2")
public class Minecessity {
	@Instance("minecessity")
	public static Minecessity instance;
	@SidedProxy(clientSide = "assets.minecessity.ClientProxy", serverSide = "assets.minecessity.CommonProxy")
	public static CommonProxy proxy;
	public static Block table, mobAttractor, projDeflector, particleBlock, chair, ceilLamp, tempTorch;
	public static Item cactusStick, portableWorkBench, particleGun;
	public static int deflectorEffectiveRange, slimeSpawnLimit;

	@EventHandler
	public void load(FMLInitializationEvent event) {

		proxy.registerRenderer();
		EntityRegistry.registerModEntity(LavaEntitySlime.class, "YSlime", 1, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityLightBullet.class, "Bullet", 2, this, 40, 1, true);
        BiomeGenBase[] biomes = new BiomeGenBase[BiomeGenBase.explorationBiomesList.size()];
        BiomeGenBase.explorationBiomesList.toArray(biomes);
		EntityRegistry.addSpawn(LavaEntitySlime.class, 1, 1, 5, EnumCreatureType.monster, biomes);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
	}

	@EventHandler
	public void prepareProps(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		deflectorEffectiveRange = config.get("general", "Deflector Effective Range", 8).getInt();
		slimeSpawnLimit = config.get("general", "Slime spawn limit by chunk", 3).getInt();
		if (config.hasChanged())
			config.save();
        mobAttractor = new BlockMobAttract().setHardness(0.5F).setBlockName("minecessity:mobAttractor").setBlockTextureName("minecessity:mobAttractor");
        tempTorch = new BlockTempTorch().setLightLevel(0.9375F).setBlockName("minecessity:temptorch");
        projDeflector = new BlockProjectDeflector().setHardness(0.85F).setBlockName("minecessity:deflector").setBlockTextureName("minecessity:deflector");
        particleBlock = new BlockParticle().setHardness(0.4F).setBlockName("minecessity:particleBlock").setBlockTextureName("minecessity:particleBlock");
        table = new MagicBlock(CommonProxy.rendererTable).setBlockName("minecessity:table").setBlockTextureName("minecessity:table");
        chair = new MagicBlock(CommonProxy.rendererChair).setBounds(0.12F, 0F, 0.12F, 0.87F, 0.55F, 0.87F).setBlockName("minecessity:chair").setBlockTextureName("minecessity:chair");
        ceilLamp = new MagicBlock(CommonProxy.rendererCeilLamp).setLightLevel(1F).setBlockName("minecessity:ceilLamp").setBlockTextureName("minecessity:ceilLamp");
        cactusStick = new ItemCactusStick().setUnlocalizedName("minecessity:cactusPicker").setTextureName("minecessity:cactusPicker");
        portableWorkBench = new ItemPrtbWorkBence().setUnlocalizedName("minecessity:portableWorkBench").setTextureName("minecessity:portableWorkBench");
        particleGun = new ItemParticleGun().setUnlocalizedName("minecessity:particleGun").setTextureName("minecessity:particleGun");
        registerBlocksItemsRecipes();
	}

	public void registerBlocksItemsRecipes() {
		GameRegistry.registerBlock(table, MagicItem.class, "Table");
		GameRegistry.addRecipe(new ItemStack(table, 1), "XLX", " Y ", " X ", 'X', Blocks.planks, 'L', Blocks.lever, 'Y', Items.iron_ingot);
		GameRegistry.registerBlock(chair, MagicItem.class, "Chair");
		GameRegistry.addRecipe(new ItemStack(chair, 1), " W ", " LI", " WW", 'W', Blocks.planks, 'L', Blocks.lever, 'I', Items.iron_ingot);
		GameRegistry.registerBlock(ceilLamp, ItemCeilLamp.class, "Ceiling Lamp");
		GameRegistry.addRecipe(new ItemStack(ceilLamp, 1), " W ", " L ", "GIG", 'W', Blocks.planks, 'L', Blocks.lever, 'G', Blocks.glowstone, 'I', Items.iron_ingot);
		GameRegistry.registerBlock(mobAttractor, "Mob Attractor");
		GameRegistry.addRecipe(new ItemStack(mobAttractor, 1), "XYX", "YBY", "XYX", 'X', Blocks.cobblestone, 'Y', Blocks.sapling, 'B', Items.bone);
		GameRegistry.registerBlock(tempTorch, "Temporary Torch");
		GameRegistry.addRecipe(new ItemStack(tempTorch, 1), "X", 'X', Blocks.torch);
		GameRegistry.addRecipe(new ItemStack(Blocks.torch, 1), "X", 'X', tempTorch );
		GameRegistry.registerBlock(projDeflector, "Deflector");
		GameRegistry.addRecipe(new ItemStack(projDeflector, 1), "GLG", "B B", "GLG", 'G', Items.gold_ingot, 'L', Blocks.lever, 'B', Blocks.stone_button);
		GameRegistry.registerBlock(particleBlock, "Particles Block");
		GameRegistry.addRecipe(new ItemStack(particleBlock, 1), "CGC", "CDC", "CCC", 'C', Blocks.cobblestone, 'G', Blocks.glass, 'D', particleGun);
		GameRegistry.registerItem(cactusStick, "Cactus Picker");
		GameRegistry.addRecipe(new ItemStack(cactusStick, 1), "X", "X", "S", 'X', Blocks.cactus, 'S', Items.stick);
		GameRegistry.registerItem(portableWorkBench, "Portable Workbench");
		GameRegistry.addRecipe(new ItemStack(portableWorkBench, 1), "ILI", "RWR", "IRI", 'W', Blocks.crafting_table, 'I', Items.iron_ingot, 'R', Items.redstone, 'L', Blocks.lever);
		GameRegistry.registerItem(particleGun, "Particles Gun");
		GameRegistry.addRecipe(new ItemStack(particleGun, 1), "123", " 45", " 6I", 'I', Items.iron_ingot, '1', new ItemStack(Items.dye, 1), '2', new ItemStack(Items.dye, 2),
				'3', new ItemStack(Items.dye, 4), '4', new ItemStack(Items.dye, 7), '5', new ItemStack(Items.dye, 8), '6', new ItemStack(Items.dye, 15));
	}
	/*
	 * @Deprecated public boolean OnTickInGame(Minecraft minecraft) {
	 * EntityPlayer player = minecraft.thePlayer; int x =
	 * deflectorEffectiveRange; for(int i = (int)Math.floor(player.posX)-x ; i
	 * <(int)Math.floor(player.posX)+x ; i++){ for(int j =
	 * (int)Math.floor(player.boundingBox.minY)-x ; j
	 * <(int)Math.floor(player.boundingBox.maxY)+x ; j++){ for(int k =
	 * (int)Math.floor(player.posZ)-x ; k <(int)Math.floor(player.posZ)+x ;
	 * k++){ if(minecraft.theWorld.getBlockId(i,j,k)==projDeflector.blockID)
	 * ((BlockProjectDeflector
	 * )projDeflector).deflectProjectiles(minecraft.theWorld,i,j,k); }}} return
	 * true; }
	 */
}
