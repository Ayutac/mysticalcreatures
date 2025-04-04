package studio.abos.mc.mysticalcreatures;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.Holder;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import studio.abos.mc.mysticalcreatures.client.model.entity.JackalopeModel;
import studio.abos.mc.mysticalcreatures.client.model.entity.PhoenixModel;
import studio.abos.mc.mysticalcreatures.client.model.entity.TrollModel;
import studio.abos.mc.mysticalcreatures.client.model.entity.UnicornModel;
import studio.abos.mc.mysticalcreatures.client.render.entity.JackalopeRenderer;
import studio.abos.mc.mysticalcreatures.client.render.entity.PhoenixRenderer;
import studio.abos.mc.mysticalcreatures.client.render.entity.TrollRenderer;
import studio.abos.mc.mysticalcreatures.client.render.entity.UnicornRenderer;
import studio.abos.mc.mysticalcreatures.datagen.MyAdvancementProvider;
import studio.abos.mc.mysticalcreatures.datagen.MyBiomeTagProvider;
import studio.abos.mc.mysticalcreatures.datagen.MyBlockTagProvider;
import studio.abos.mc.mysticalcreatures.datagen.MyItemTagProvider;
import studio.abos.mc.mysticalcreatures.datagen.MyLanguageProvider;
import studio.abos.mc.mysticalcreatures.datagen.MyModelProvider;
import studio.abos.mc.mysticalcreatures.entity.JackalopeEntity;
import studio.abos.mc.mysticalcreatures.entity.PhoenixEntity;
import studio.abos.mc.mysticalcreatures.entity.TrollEntity;
import studio.abos.mc.mysticalcreatures.entity.UnicornEntity;

import java.util.List;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Name.MODID)
public class MysticalCreatures
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Name.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Name.MODID);
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(Name.MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, Name.MODID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Name.MODID);

    private static final String _FOOD = "_food";
    public static final TagKey<Item> PHOENIX_FOOD = TagKey.create(Registries.ITEM, of(Name.PHOENIX + _FOOD));
    public static final TagKey<Item> JACKALOPE_FOOD = TagKey.create(Registries.ITEM, of(Name.JACKALOPE + _FOOD));
    public static final TagKey<Item> UNICORN_FOOD = TagKey.create(Registries.ITEM, of(Name.UNICORN + _FOOD));
    public static final TagKey<Item> TROLL_FOOD = TagKey.create(Registries.ITEM, of(Name.TROLL + _FOOD));

    private static final String _SPAWNS_ON = "_spawns_on";
    public static final TagKey<Biome> PHOENIX_SPAWNS_ON = TagKey.create(Registries.BIOME, of(Name.PHOENIX + _SPAWNS_ON));
    public static final TagKey<Biome> JACKALOPE_SPAWNS_ON = TagKey.create(Registries.BIOME, of(Name.JACKALOPE + _SPAWNS_ON));
    public static final TagKey<Biome> UNICORN_SPAWNS_ON = TagKey.create(Registries.BIOME, of(Name.UNICORN + _SPAWNS_ON));
    public static final TagKey<Biome> TROLL_SPAWNS_ON = TagKey.create(Registries.BIOME, of(Name.TROLL + _SPAWNS_ON));

    // Creates a new Block with the id "mysticalcreatures:example_block", combining the namespace and path
    // public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "mysticalcreatures:example_block", combining the namespace and path
    // public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    public static final DeferredItem<Item> PHOENIX_FEATHER = ITEMS.registerSimpleItem(Name.PHOENIX_FEATHER, new Item.Properties().fireResistant());
    public static final DeferredItem<Item> JACKALOPE_ANTLERS = ITEMS.registerSimpleItem(Name.JACKALOPE_ANTLERS, new Item.Properties());
    public static final DeferredItem<Item> UNICORN_HORN = ITEMS.registerSimpleItem(Name.UNICORN_HORN, new Item.Properties());
    public static final DeferredItem<Item> TROLL_HEART = ITEMS.registerSimpleItem(Name.TROLL_HEART, new Item.Properties());

    public static final String _LONG = "_long";
    public static final String _STRONG = "_strong";
    public static final Holder<Potion> PHOENIX_POTION = POTIONS.register(Name.PHOENIX, name -> new Potion(name.getPath(),
            new MobEffectInstance(MobEffects.REGENERATION, 22 * 20 + 10),
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2 * 60 * 20)));
    public static final Holder<Potion> PHOENIX_POTION_LONG = POTIONS.register(Name.PHOENIX + _LONG, name -> new Potion(name.getPath(),
            new MobEffectInstance(MobEffects.REGENERATION, 45 * 20),
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 4 * 60 * 20)));
    public static final Holder<Potion> PHOENIX_POTION_STRONG = POTIONS.register(Name.PHOENIX + _STRONG, name -> new Potion(name.getPath(),
            new MobEffectInstance(MobEffects.REGENERATION, 11 * 20 + 5, 1),
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2 * 60 * 20)));

    public static final Supplier<EntityType<PhoenixEntity>> PHOENIX_ENTITY = ENTITY_TYPES.register(Name.PHOENIX,
            () -> EntityType.Builder.of(PhoenixEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .fireImmune()
                    .clientTrackingRange(8)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, of(Name.PHOENIX))));
    public static final Supplier<EntityType<JackalopeEntity>> JACKALOPE_ENTITY = ENTITY_TYPES.register(Name.JACKALOPE,
            () -> EntityType.Builder.of(JackalopeEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .clientTrackingRange(8)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, of(Name.JACKALOPE))));
    public static final Supplier<EntityType<UnicornEntity>> UNICORN_ENTITY = ENTITY_TYPES.register(Name.UNICORN,
            () -> EntityType.Builder.of(UnicornEntity::new, MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .clientTrackingRange(8)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, of(Name.UNICORN))));
    public static final Supplier<EntityType<TrollEntity>> TROLL_ENTITY = ENTITY_TYPES.register(Name.TROLL,
            () -> EntityType.Builder.of(TrollEntity::new, MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .clientTrackingRange(8)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, of(Name.TROLL))));

    private static final String _SPAWN_EGG = "_spawn_egg";
    public static final DeferredItem<SpawnEggItem> PHOENIX_SPAWN_EGG = ITEMS.registerItem(Name.PHOENIX + _SPAWN_EGG,
            properties -> new SpawnEggItem(PHOENIX_ENTITY.get(), properties));
    public static final DeferredItem<SpawnEggItem> JACKALOPE_SPAWN_EGG = ITEMS.registerItem(Name.JACKALOPE + _SPAWN_EGG,
            properties -> new SpawnEggItem(JACKALOPE_ENTITY.get(), properties));
    public static final DeferredItem<SpawnEggItem> UNICORN_SPAWN_EGG = ITEMS.registerItem(Name.UNICORN + _SPAWN_EGG,
            properties -> new SpawnEggItem(UNICORN_ENTITY.get(), properties));
    public static final DeferredItem<SpawnEggItem> TROLL_SPAWN_EGG = ITEMS.registerItem(Name.TROLL + _SPAWN_EGG,
            properties -> new SpawnEggItem(TROLL_ENTITY.get(), properties));

    public static final ModelLayerLocation PHOENIX_LAYER = new ModelLayerLocation(of(Name.PHOENIX), "head");
    public static final ModelLayerLocation JACKALOPE_LAYER = new ModelLayerLocation(of(Name.JACKALOPE), "head");
    public static final ModelLayerLocation UNICORN_LAYER = new ModelLayerLocation(of(Name.UNICORN), "head");
    public static final ModelLayerLocation TROLL_LAYER = new ModelLayerLocation(of(Name.TROLL), "head");

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(Name.MODID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + Name.MODID)) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> UNICORN_HORN.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(PHOENIX_SPAWN_EGG);
                output.accept(PHOENIX_FEATHER);
                output.accept(JACKALOPE_SPAWN_EGG);
                output.accept(JACKALOPE_ANTLERS);
                output.accept(UNICORN_SPAWN_EGG);
                output.accept(UNICORN_HORN);
                output.accept(TROLL_SPAWN_EGG);
                output.accept(TROLL_HEART);
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public MysticalCreatures(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        POTIONS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (MysticalCreatures) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::onAttributeCreation);
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation of(final String name) {
        return ResourceLocation.fromNamespaceAndPath(Name.MODID, name);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
//        LOGGER.info("HELLO FROM COMMON SETUP");
//
//        if (Config.logDirtBlock)
//            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
//
//        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
//
//        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    @SubscribeEvent
    public void registerBrewingRecipes(final RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        // Will add brewing recipes for all container potions (e.g. potion, splash potion, lingering potion)
        builder.addMix(Potions.AWKWARD, PHOENIX_FEATHER.get(), PHOENIX_POTION);
        builder.addMix(PHOENIX_POTION, Items.REDSTONE, PHOENIX_POTION_LONG);
        builder.addMix(PHOENIX_POTION, Items.GLOWSTONE, PHOENIX_POTION_STRONG);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.insertAfter(Items.PHANTOM_MEMBRANE.getDefaultInstance(), PHOENIX_FEATHER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(PHOENIX_FEATHER.get().getDefaultInstance(), JACKALOPE_ANTLERS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(JACKALOPE_ANTLERS.get().getDefaultInstance(), UNICORN_HORN.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(UNICORN_HORN.get().getDefaultInstance(), TROLL_HEART.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        else if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(PHOENIX_SPAWN_EGG);
            event.accept(JACKALOPE_SPAWN_EGG);
            event.accept(UNICORN_SPAWN_EGG);
            event.accept(TROLL_SPAWN_EGG);
        }
    }

    private void onAttributeCreation(final EntityAttributeCreationEvent event) {
        event.put(PHOENIX_ENTITY.get(), PhoenixEntity.createPhoenixAttributes().build());
        event.put(JACKALOPE_ENTITY.get(), JackalopeEntity.createJackalopeAttributes().build());
        event.put(UNICORN_ENTITY.get(), UnicornEntity.createUnicornAttributes().build());
        event.put(TROLL_ENTITY.get(), TrollEntity.createTrollAttributes().build());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
//        // Do something when the server starts
//        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = Name.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
//            // Some client setup code
//            LOGGER.info("HELLO FROM CLIENT SETUP");
//            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(PHOENIX_LAYER, PhoenixModel::createBodyLayer);
            event.registerLayerDefinition(JACKALOPE_LAYER, JackalopeModel::createBodyLayer);
            event.registerLayerDefinition(UNICORN_LAYER, UnicornModel::createBodyLayer);
            event.registerLayerDefinition(TROLL_LAYER, TrollModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(PHOENIX_ENTITY.get(), PhoenixRenderer::new);
            event.registerEntityRenderer(JACKALOPE_ENTITY.get(), JackalopeRenderer::new);
            event.registerEntityRenderer(UNICORN_ENTITY.get(), UnicornRenderer::new);
            event.registerEntityRenderer(TROLL_ENTITY.get(), TrollRenderer::new);
        }
    }

    // datagen
    @EventBusSubscriber(modid = Name.MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class DataHandler {
        @SubscribeEvent
        public static void gatherData(GatherDataEvent.Client event) {
            // tags first
            event.createBlockAndItemTags(MyBlockTagProvider::new, MyItemTagProvider::new);
            event.createProvider(MyBiomeTagProvider::new);
            // misc
            event.createProvider(MyLanguageProvider::new);
            event.createProvider(MyModelProvider::new);
            event.createProvider((output, lookupProvider) ->
                    new AdvancementProvider(output, lookupProvider, List.of(new MyAdvancementProvider())));
        }
    }
}
