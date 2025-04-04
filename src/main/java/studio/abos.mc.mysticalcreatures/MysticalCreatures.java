package studio.abos.mc.mysticalcreatures;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
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
import studio.abos.mc.mysticalcreatures.datagen.MyBiomeTagProvider;
import studio.abos.mc.mysticalcreatures.datagen.MyBlockTagProvider;
import studio.abos.mc.mysticalcreatures.datagen.MyItemTagProvider;
import studio.abos.mc.mysticalcreatures.datagen.MyLanguageProvider;
import studio.abos.mc.mysticalcreatures.datagen.MyModelProvider;
import studio.abos.mc.mysticalcreatures.entity.JackalopeEntity;
import studio.abos.mc.mysticalcreatures.entity.PhoenixEntity;
import studio.abos.mc.mysticalcreatures.entity.TrollEntity;
import studio.abos.mc.mysticalcreatures.entity.UnicornEntity;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MysticalCreatures.MODID)
public class MysticalCreatures
{
    public static final String MODID = "mysticalcreatures";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(MODID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final TagKey<Item> PHOENIX_BREEDING_ITEMS = TagKey.create(Registries.ITEM, MysticalCreatures.of("phoenix_breeding_items"));
    public static final TagKey<Item> JACKALOPE_BREEDING_ITEMS = TagKey.create(Registries.ITEM, MysticalCreatures.of("jackalope_breeding_items"));
    public static final TagKey<Item> UNICORN_BREEDING_ITEMS = TagKey.create(Registries.ITEM, MysticalCreatures.of("unicorn_breeding_items"));
    public static final TagKey<Item> TROLL_BREEDING_ITEMS = TagKey.create(Registries.ITEM, MysticalCreatures.of("troll_breeding_items"));

    public static final TagKey<Biome> PHOENIX_SPAWNS_ON = TagKey.create(Registries.BIOME, MysticalCreatures.of("phoenix_spawns_on"));
    public static final TagKey<Biome> JACKALOPE_SPAWNS_ON = TagKey.create(Registries.BIOME, MysticalCreatures.of("jackalope_spawns_on"));
    public static final TagKey<Biome> UNICORN_SPAWNS_ON = TagKey.create(Registries.BIOME, MysticalCreatures.of("unicorn_spawns_on"));
    public static final TagKey<Biome> TROLL_SPAWNS_ON = TagKey.create(Registries.BIOME, MysticalCreatures.of("troll_spawns_on"));

    // Creates a new Block with the id "mysticalcreatures:example_block", combining the namespace and path
    // public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "mysticalcreatures:example_block", combining the namespace and path
    // public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    public static final DeferredItem<Item> PHOENIX_FEATHER = ITEMS.registerSimpleItem("phoenix_feather", new Item.Properties().rarity(Rarity.RARE).fireResistant());
    public static final DeferredItem<Item> JACKALOPE_ANTLERS = ITEMS.registerSimpleItem("jackalope_antlers", new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> UNICORN_HORN = ITEMS.registerSimpleItem("unicorn_horn", new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> TROLL_HEART = ITEMS.registerSimpleItem("troll_heart", new Item.Properties().rarity(Rarity.RARE));

    public static final Supplier<EntityType<PhoenixEntity>> PHOENIX_ENTITY = ENTITY_TYPES.register("phoenix",
            () -> EntityType.Builder.of(PhoenixEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .fireImmune()
                    .clientTrackingRange(8)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, of("phoenix"))));
    public static final Supplier<EntityType<JackalopeEntity>> JACKALOPE_ENTITY = ENTITY_TYPES.register("jackalope",
            () -> EntityType.Builder.of(JackalopeEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .clientTrackingRange(8)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, of("jackalope"))));
    public static final Supplier<EntityType<UnicornEntity>> UNICORN_ENTITY = ENTITY_TYPES.register("unicorn",
            () -> EntityType.Builder.of(UnicornEntity::new, MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .clientTrackingRange(8)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, of("unicorn"))));
    public static final Supplier<EntityType<TrollEntity>> TROLL_ENTITY = ENTITY_TYPES.register("troll",
            () -> EntityType.Builder.of(TrollEntity::new, MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .clientTrackingRange(8)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, of("troll"))));

    public static final DeferredItem<SpawnEggItem> PHOENIX_SPAWN_EGG = ITEMS.registerItem("phoenix_spawn_egg",
            properties -> new SpawnEggItem(PHOENIX_ENTITY.get(), properties));
    public static final DeferredItem<SpawnEggItem> JACKALOPE_SPAWN_EGG = ITEMS.registerItem("jackalope_spawn_egg",
            properties -> new SpawnEggItem(JACKALOPE_ENTITY.get(), properties));
    public static final DeferredItem<SpawnEggItem> UNICORN_SPAWN_EGG = ITEMS.registerItem("unicorn_spawn_egg",
            properties -> new SpawnEggItem(UNICORN_ENTITY.get(), properties));
    public static final DeferredItem<SpawnEggItem> TROLL_SPAWN_EGG = ITEMS.registerItem("troll_spawn_egg",
            properties -> new SpawnEggItem(TROLL_ENTITY.get(), properties));

    public static final ModelLayerLocation PHOENIX_LAYER = new ModelLayerLocation(of("phoenix"), "head");
    public static final ModelLayerLocation JACKALOPE_LAYER = new ModelLayerLocation(of("jackalope"), "head");
    public static final ModelLayerLocation UNICORN_LAYER = new ModelLayerLocation(of("unicorn"), "head");
    public static final ModelLayerLocation TROLL_LAYER = new ModelLayerLocation(of("troll"), "head");

    // Creates a creative tab with the id "mysticalcreatures:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("mysticalcreatures", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.mysticalcreatures")) //The language key for the title of your CreativeModeTab
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

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so entity types get registered
        ENTITY_TYPES.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (MysticalCreatures) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::onAttributeCreation);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation of(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name);
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

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
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

    private void onAttributeCreation(EntityAttributeCreationEvent event) {
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
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class DataHandler {
        @SubscribeEvent
        public static void gatherData(GatherDataEvent.Client event) {
            // tags first
            event.createBlockAndItemTags(MyBlockTagProvider::new, MyItemTagProvider::new);
            event.createProvider(MyBiomeTagProvider::new);
            // misc
            event.createProvider(MyLanguageProvider::new);
            event.createProvider(MyModelProvider::new);
        }
    }
}
