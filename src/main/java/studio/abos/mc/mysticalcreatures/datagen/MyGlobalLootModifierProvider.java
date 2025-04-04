package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;
import studio.abos.mc.mysticalcreatures.datagen.lootmodifier.SimpleLootModifier;

import java.util.concurrent.CompletableFuture;

public class MyGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public MyGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Name.MODID);
    }

    @Override
    protected void start() {
        add(Name.LM_WITCH, new SimpleLootModifier(new LootItemCondition[] {
                LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "entities/witch")).build(),
                LootItemKilledByPlayerCondition.killedByPlayer().build(),
                // same chance as unicorn horn drop by unicorn
                new LootItemRandomChanceWithEnchantedBonusCondition(0.09375f, LevelBasedValue.perLevel(0.125f, 0.03125f),
                        registries.holderOrThrow(Enchantments.LOOTING))},
                MysticalCreatures.PHOENIX_FEATHER.get()));
        add(Name.LM_BASTION, new SimpleLootModifier(new LootItemCondition[] {
                LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()},
                MysticalCreatures.PHOENIX_FEATHER.get()));
        add(Name.LM_OUTPOST, new SimpleLootModifier(new LootItemCondition[] {
                LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.333f).build()},
                MysticalCreatures.JACKALOPE_ANTLERS.get()));
        add(Name.LM_MANSION, new SimpleLootModifier(new LootItemCondition[] {
                LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()},
                MysticalCreatures.UNICORN_HORN.get()));
        add(Name.LM_ANCIENT_CITY, new SimpleLootModifier(new LootItemCondition[] {
                LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ancient_city_ice_box")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()},
                MysticalCreatures.TROLL_HEART.get()));
    }
}
