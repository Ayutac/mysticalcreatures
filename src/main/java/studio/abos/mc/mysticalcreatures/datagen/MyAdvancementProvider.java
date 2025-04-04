package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;

import java.util.List;
import java.util.function.Consumer;

public class MyAdvancementProvider implements AdvancementSubProvider {
    @Override
    public void generate(final HolderLookup.Provider provider, final Consumer<AdvancementHolder> consumer) {
        final AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        MysticalCreatures.PHOENIX_FEATHER.get(),
                        Component.translatable(title("root")),
                        Component.translatable(desc("root")),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/dirt"),
                        AdvancementType.TASK,
                        true, false, false
                )
                .addCriterion("pickup_phoenix_feather", InventoryChangeTrigger.TriggerInstance.hasItems(MysticalCreatures.PHOENIX_FEATHER))
                .addCriterion("pickup_jackalope_antlers", InventoryChangeTrigger.TriggerInstance.hasItems(MysticalCreatures.JACKALOPE_ANTLERS))
                .addCriterion("pickup_unicorn_horn", InventoryChangeTrigger.TriggerInstance.hasItems(MysticalCreatures.UNICORN_HORN))
                .addCriterion("pickup_troll_heart", InventoryChangeTrigger.TriggerInstance.hasItems(MysticalCreatures.TROLL_HEART))
                .requirements(AdvancementRequirements.anyOf(List.of(
                        "pickup_phoenix_feather",
                        "pickup_jackalope_antlers",
                        "pickup_unicorn_horn",
                        "pickup_troll_heart")))
                .save(consumer, MysticalCreatures.of("root"));
    }

    public static String title(final String name) {
        return String.format("advancements.%s.%s.title", Name.MODID, name);
    }

    public static String desc(final String desc) {
        return String.format("advancements.%s.%s.description", Name.MODID, desc);
    }

}
