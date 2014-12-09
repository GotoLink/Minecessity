package assets.minecessity;

import assets.minecessity.blocks.*;
import assets.minecessity.items.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = "minecessity", name = "Minecessity", useMetadata = true)
public final class Minecessity {
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
        if(event.getSourceFile().getName().endsWith(".jar")){
            proxy.trySendUpdate();
        }
	}

	private void registerBlocksItemsRecipes() {
		GameRegistry.registerBlock(table, MagicItem.class, "Table");
		GameRegistry.addRecipe(new ItemStack(table), "XLX", " Y ", " X ", 'X', Blocks.planks, 'L', Blocks.lever, 'Y', Items.iron_ingot);
		GameRegistry.registerBlock(chair, MagicItem.class, "Chair");
		GameRegistry.addRecipe(new ItemStack(chair), " W ", " LI", " WW", 'W', Blocks.planks, 'L', Blocks.lever, 'I', Items.iron_ingot);
		GameRegistry.registerBlock(ceilLamp, ItemCeilLamp.class, "CeilingLamp");
		GameRegistry.addRecipe(new ItemStack(ceilLamp), " W ", " L ", "GIG", 'W', Blocks.planks, 'L', Blocks.lever, 'G', Blocks.glowstone, 'I', Items.iron_ingot);
		GameRegistry.registerBlock(mobAttractor, "MobAttractor");
		GameRegistry.addRecipe(new ItemStack(mobAttractor), "XYX", "YBY", "XYX", 'X', Blocks.cobblestone, 'Y', Blocks.sapling, 'B', Items.bone);
		GameRegistry.registerBlock(tempTorch, "TemporaryTorch");
		GameRegistry.addRecipe(new ItemStack(tempTorch), "X", 'X', Blocks.torch);
		GameRegistry.addRecipe(new ItemStack(Blocks.torch), "X", 'X', tempTorch );
		GameRegistry.registerBlock(projDeflector, "Deflector");
		GameRegistry.addRecipe(new ItemStack(projDeflector), "GLG", "B B", "GLG", 'G', Items.gold_ingot, 'L', Blocks.lever, 'B', Blocks.stone_button);
		GameRegistry.registerBlock(particleBlock, "ParticlesBlock");
		GameRegistry.addRecipe(new ItemStack(particleBlock), "CGC", "CDC", "CCC", 'C', Blocks.cobblestone, 'G', Blocks.glass, 'D', particleGun);
		GameRegistry.registerItem(cactusStick, "CactusPicker");
		GameRegistry.addRecipe(new ItemStack(cactusStick), "X", "X", "S", 'X', Blocks.cactus, 'S', Items.stick);
		GameRegistry.registerItem(portableWorkBench, "PortableWorkbench");
		GameRegistry.addRecipe(new ItemStack(portableWorkBench), "ILI", "RWR", "IRI", 'W', Blocks.crafting_table, 'I', Items.iron_ingot, 'R', Items.redstone, 'L', Blocks.lever);
		GameRegistry.registerItem(particleGun, "ParticlesGun");
		GameRegistry.addRecipe(new ItemStack(particleGun), "123", " 45", " 6I", 'I', Items.iron_ingot, '1', new ItemStack(Items.dye, 1), '2', new ItemStack(Items.dye, 2),
				'3', new ItemStack(Items.dye, 4), '4', new ItemStack(Items.dye, 7), '5', new ItemStack(Items.dye, 8), '6', new ItemStack(Items.dye, 15));
	}

    @EventHandler
    public void onRemap(FMLMissingMappingsEvent event){
        for(FMLMissingMappingsEvent.MissingMapping missingMapping : event.get()){
            switch(missingMapping.type) {
                case ITEM:
                    missingMapping.remap(GameData.getItemRegistry().getObject(missingMapping.name.replace(" ", "")));
                    break;
                case BLOCK:
                    missingMapping.remap(GameData.getBlockRegistry().getObject(missingMapping.name.replace(" ", "")));
                    break;
            }
        }
    }
}
