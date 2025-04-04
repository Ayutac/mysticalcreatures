package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.BredAnimalsTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MyAdvancementProvider implements AdvancementSubProvider {
    @Override
    public void generate(final HolderLookup.Provider provider, final Consumer<AdvancementHolder> consumer) {
        // items triggers
        final var pickupPhoenixFeather = InventoryChangeTrigger.TriggerInstance.hasItems(MysticalCreatures.PHOENIX_FEATHER);
        final var pickupJackalopeAntlers = InventoryChangeTrigger.TriggerInstance.hasItems(MysticalCreatures.JACKALOPE_ANTLERS);
        final var pickupUnicornHorn = InventoryChangeTrigger.TriggerInstance.hasItems(MysticalCreatures.UNICORN_HORN);
        final var pickupTrollHeart = InventoryChangeTrigger.TriggerInstance.hasItems(MysticalCreatures.TROLL_HEART);
        // breeding triggers
        final var breedPhoenix = BredAnimalsTrigger.TriggerInstance.bredAnimals(Optional.empty(), Optional.empty(), Optional.of(entityType(MysticalCreatures.PHOENIX_ENTITY.get())));
        final var breedJackalope = BredAnimalsTrigger.TriggerInstance.bredAnimals(Optional.empty(), Optional.empty(), Optional.of(entityType(MysticalCreatures.JACKALOPE_ENTITY.get())));
        final var breedUnicorn = BredAnimalsTrigger.TriggerInstance.bredAnimals(Optional.empty(), Optional.empty(), Optional.of(entityType(MysticalCreatures.UNICORN_ENTITY.get())));
        final var breedTroll = BredAnimalsTrigger.TriggerInstance.bredAnimals(Optional.empty(), Optional.empty(), Optional.of(entityType(MysticalCreatures.TROLL_ENTITY.get())));
        // killing triggers
        final var killPhoenix = KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(entityType(MysticalCreatures.PHOENIX_ENTITY.get())));
        final var killJackalope = KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(entityType(MysticalCreatures.JACKALOPE_ENTITY.get())));
        final var killUnicorn = KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(entityType(MysticalCreatures.UNICORN_ENTITY.get())));
        final var killTroll = KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(entityType(MysticalCreatures.TROLL_ENTITY.get())));
        
        // advancements
        final AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        MysticalCreatures.PHOENIX_FEATHER.get(),
                        Component.translatable(title(Name.ADV_ROOT)),
                        Component.translatable(desc(Name.ADV_ROOT)),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/amethyst_block"),
                        AdvancementType.TASK,
                        true, false, false
                )
                .addCriterion(Name.PICKUP_PHOENIX_FEATHER, pickupPhoenixFeather)
                .addCriterion(Name.PICKUP_JACKALOPE_ANTLERS, pickupJackalopeAntlers)
                .addCriterion(Name.PICKUP_UNICORN_HORN, pickupUnicornHorn)
                .addCriterion(Name.PICKUP_TROLL_HEART, pickupTrollHeart)
                .addCriterion(Name.BREED_PHOENIX, breedPhoenix)
                .addCriterion(Name.BREED_JACKALOPE, breedJackalope)
                .addCriterion(Name.BREED_UNICORN, breedUnicorn)
                .addCriterion(Name.BREED_TROLL, breedTroll)
                .addCriterion(Name.KILL_PHOENIX, killPhoenix)
                .addCriterion(Name.KILL_JACKALOPE, killJackalope)
                .addCriterion(Name.KILL_UNICORN, killUnicorn)
                .addCriterion(Name.KILL_TROLL, killTroll)
                .requirements(AdvancementRequirements.anyOf(List.of(
                        Name.PICKUP_PHOENIX_FEATHER,
                        Name.PICKUP_JACKALOPE_ANTLERS,
                        Name.PICKUP_UNICORN_HORN,
                        Name.PICKUP_TROLL_HEART,
                        Name.BREED_PHOENIX,
                        Name.BREED_JACKALOPE,
                        Name.BREED_UNICORN,
                        Name.BREED_TROLL,
                        Name.KILL_PHOENIX,
                        Name.KILL_JACKALOPE,
                        Name.KILL_UNICORN,
                        Name.KILL_TROLL)))
                .save(consumer, MysticalCreatures.of(Name.ADV_ROOT));
        Advancement.Builder.advancement()
                .parent(root)
                .display(
                        MysticalCreatures.UNICORN_HORN.get(),
                        Component.translatable(title(Name.ADV_COLLECTOR)),
                        Component.translatable(desc(Name.ADV_COLLECTOR)),
                        null,
                        AdvancementType.GOAL,
                        true, false, false
                )
                .addCriterion(Name.PICKUP_PHOENIX_FEATHER, pickupPhoenixFeather)
                .addCriterion(Name.PICKUP_JACKALOPE_ANTLERS, pickupJackalopeAntlers)
                .addCriterion(Name.PICKUP_UNICORN_HORN, pickupUnicornHorn)
                .addCriterion(Name.PICKUP_TROLL_HEART, pickupTrollHeart)
                .requirements(AdvancementRequirements.allOf(List.of(
                        Name.PICKUP_PHOENIX_FEATHER,
                        Name.PICKUP_JACKALOPE_ANTLERS,
                        Name.PICKUP_UNICORN_HORN,
                        Name.PICKUP_TROLL_HEART)))
                .save(consumer, MysticalCreatures.of(Name.ADV_COLLECTOR));
        Advancement.Builder.advancement()
                .parent(root)
                .display(
                        MysticalCreatures.JACKALOPE_ANTLERS.get(),
                        Component.translatable(title(Name.ADV_BREEDER)),
                        Component.translatable(desc(Name.ADV_BREEDER)),
                        null,
                        AdvancementType.GOAL,
                        true, false, false
                )
                .addCriterion(Name.BREED_PHOENIX, breedPhoenix)
                .addCriterion(Name.BREED_JACKALOPE, breedJackalope)
                .addCriterion(Name.BREED_UNICORN, breedUnicorn)
                .addCriterion(Name.BREED_TROLL, breedTroll)
                .requirements(AdvancementRequirements.allOf(List.of(
                        Name.BREED_PHOENIX,
                        Name.BREED_JACKALOPE,
                        Name.BREED_UNICORN,
                        Name.BREED_TROLL)))
                .save(consumer, MysticalCreatures.of(Name.ADV_BREEDER));
        Advancement.Builder.advancement()
                .parent(root)
                .display(
                        MysticalCreatures.TROLL_HEART.get(),
                        Component.translatable(title(Name.ADV_HUNTER)),
                        Component.translatable(desc(Name.ADV_HUNTER)),
                        null,
                        AdvancementType.GOAL,
                        true, false, false
                )
                .addCriterion(Name.KILL_PHOENIX, killPhoenix)
                .addCriterion(Name.KILL_JACKALOPE, killJackalope)
                .addCriterion(Name.KILL_UNICORN, killUnicorn)
                .addCriterion(Name.KILL_TROLL, killTroll)
                .requirements(AdvancementRequirements.allOf(List.of(
                        Name.KILL_PHOENIX,
                        Name.KILL_JACKALOPE,
                        Name.KILL_UNICORN,
                        Name.KILL_TROLL)))
                .save(consumer, MysticalCreatures.of(Name.ADV_HUNTER));
    }

    public static String title(final String name) {
        return String.format("advancements.%s.%s.title", Name.MODID, name);
    }

    public static String desc(final String desc) {
        return String.format("advancements.%s.%s.description", Name.MODID, desc);
    }
    
    public static EntityPredicate entityType(final EntityType<?> type) {
        return EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(null, type)).build();
    }

}
