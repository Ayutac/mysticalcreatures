package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;

import java.util.stream.Stream;

public class MyEntityLootTableProvider extends EntityLootSubProvider {

    public MyEntityLootTableProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @NotNull
    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return MysticalCreatures.ENTITY_TYPES.getEntries().stream().map(DeferredHolder::value);
    }

    @Override
    public void generate() {
        // phoenix drop
        add(MysticalCreatures.PHOENIX_ENTITY.get(), LootTable.lootTable()
                .setRandomSequence(MysticalCreatures.of(Name.PHOENIX))
                .withPool(LootPool.lootPool()
                        .name(Name.PHOENIX_FEATHER)
                        .setRolls(ConstantValue.exactly(1))
                        .setBonusRolls(ConstantValue.exactly(0))
                        .add(LootItem.lootTableItem(MysticalCreatures.PHOENIX_FEATHER)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0,1)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0,1)).setLimit(2)))));
        // jackalope drop
        add(MysticalCreatures.JACKALOPE_ENTITY.get(), LootTable.lootTable()
                .setRandomSequence(MysticalCreatures.of(Name.JACKALOPE))
                .withPool(LootPool.lootPool()
                        .name("rabbit_hide")
                        .setRolls(ConstantValue.exactly(1))
                        .setBonusRolls(ConstantValue.exactly(0))
                        .add(LootItem.lootTableItem(Items.RABBIT_HIDE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0,1)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0,1)))))
                .withPool(LootPool.lootPool()
                        .name(Name.JACKALOPE_ANTLERS)
                        .setRolls(ConstantValue.exactly(1))
                        .setBonusRolls(ConstantValue.exactly(0))
                        .add(LootItem.lootTableItem(MysticalCreatures.JACKALOPE_ANTLERS)
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(() -> new LootItemRandomChanceWithEnchantedBonusCondition(
                                        0.1f, LevelBasedValue.perLevel(0.13f, 0.03f), registries.holderOrThrow(Enchantments.LOOTING))))));
        // unicorn drop
        add(MysticalCreatures.UNICORN_ENTITY.get(), LootTable.lootTable()
                .setRandomSequence(MysticalCreatures.of(Name.UNICORN))
                .withPool(LootPool.lootPool()
                        .name("leather")
                        .setRolls(ConstantValue.exactly(2))
                        .setBonusRolls(ConstantValue.exactly(0))
                        .add(LootItem.lootTableItem(Items.LEATHER)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0,1)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0,1)))))
                .withPool(LootPool.lootPool()
                        .name(Name.UNICORN_HORN)
                        .setRolls(ConstantValue.exactly(1))
                        .setBonusRolls(ConstantValue.exactly(0))
                        .add(LootItem.lootTableItem(MysticalCreatures.UNICORN_HORN)
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(() -> new LootItemRandomChanceWithEnchantedBonusCondition(
                                        0.09375f, LevelBasedValue.perLevel(0.125f, 0.03125f), registries.holderOrThrow(Enchantments.LOOTING))))));
        // unicorn drop
        add(MysticalCreatures.TROLL_ENTITY.get(), LootTable.lootTable()
                .setRandomSequence(MysticalCreatures.of(Name.TROLL))
                .withPool(LootPool.lootPool()
                        .name(Name.TROLL_HEART)
                        .setRolls(ConstantValue.exactly(1))
                        .setBonusRolls(ConstantValue.exactly(0))
                        .add(LootItem.lootTableItem(MysticalCreatures.TROLL_HEART)
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(() -> new LootItemRandomChanceWithEnchantedBonusCondition(
                                        0.125f, LevelBasedValue.perLevel(0.25f, 0.125f), registries.holderOrThrow(Enchantments.LOOTING))))));
    }
}
