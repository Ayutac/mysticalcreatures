package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.BredAnimalsTrigger;
import net.minecraft.advancements.critereon.BrewedPotionTrigger;
import net.minecraft.advancements.critereon.DamagePredicate;
import net.minecraft.advancements.critereon.EntityHurtPlayerTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MyAdvancementProvider implements AdvancementSubProvider {
    @Override
    public void generate(final HolderLookup.Provider provider, final Consumer<AdvancementHolder> consumer) {
        // items triggers
        final var pickupPhoenixFeather = pickup(MysticalCreatures.PHOENIX_FEATHER);
        final var pickupJackalopeAntlers = pickup(MysticalCreatures.JACKALOPE_ANTLERS);
        final var pickupUnicornHorn = pickup(MysticalCreatures.UNICORN_HORN);
        final var pickupTrollHeart = pickup(MysticalCreatures.TROLL_HEART);
        // breeding triggers
        final var breedPhoenix = bredAnimals(MysticalCreatures.PHOENIX_ENTITY.get());
        final var breedJackalope = bredAnimals(MysticalCreatures.JACKALOPE_ENTITY.get());
        final var breedUnicorn = bredAnimals(MysticalCreatures.UNICORN_ENTITY.get());
        final var breedTroll = bredAnimals(MysticalCreatures.TROLL_ENTITY.get());
        // killing triggers
        final var killPhoenix = killed(MysticalCreatures.PHOENIX_ENTITY.get());
        final var killJackalope = killed(MysticalCreatures.JACKALOPE_ENTITY.get());
        final var killUnicorn = killed(MysticalCreatures.UNICORN_ENTITY.get());
        final var killTroll = killed(MysticalCreatures.TROLL_ENTITY.get());
        // brewing triggers
        final var brewedPhoenix = brewedPotion(MysticalCreatures.PHOENIX_POTION);
        final var brewedPhoenixLong = brewedPotion(MysticalCreatures.PHOENIX_POTION_LONG);
        final var brewedPhoenixStrong = brewedPotion(MysticalCreatures.PHOENIX_POTION_STRONG);
        final var brewedJackalope = brewedPotion(MysticalCreatures.JACKALOPE_POTION);
        final var brewedJackalopeLong = brewedPotion(MysticalCreatures.JACKALOPE_POTION_LONG);
        final var brewedJackalopeStrong = brewedPotion(MysticalCreatures.JACKALOPE_POTION_STRONG);
        final var brewedUnicorn = brewedPotion(Potions.LUCK);
        final var brewedTroll = brewedPotion(MysticalCreatures.TROLL_POTION);
        final var brewedTrollLong = brewedPotion(MysticalCreatures.TROLL_POTION_LONG);
        final var brewedTrollStrong = brewedPotion(MysticalCreatures.TROLL_POTION_STRONG);
        // getting hurt triggers
        final var hurtByUnicorn = hurtBy(MysticalCreatures.UNICORN_ENTITY.get());
        final var hurtByTroll = hurtBy(MysticalCreatures.TROLL_ENTITY.get());

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
                .addCriterion(Name.BREWED_PHOENIX, brewedPhoenix)
                .addCriterion(Name.BREWED_PHOENIX_LONG, brewedPhoenixLong)
                .addCriterion(Name.BREWED_PHOENIX_STRONG, brewedPhoenixStrong)
                .addCriterion(Name.BREWED_JACKALOPE, brewedJackalope)
                .addCriterion(Name.BREWED_JACKALOPE_LONG, brewedJackalopeLong)
                .addCriterion(Name.BREWED_JACKALOPE_STRONG, brewedJackalopeStrong)
                .addCriterion(Name.BREWED_UNICORN, brewedUnicorn)
                .addCriterion(Name.BREWED_TROLL, brewedTroll)
                .addCriterion(Name.BREWED_TROLL_LONG, brewedTrollLong)
                .addCriterion(Name.BREWED_TROLL_STRONG, brewedTrollStrong)
                .addCriterion(Name.HURT_BY_UNICORN, hurtByUnicorn)
                .addCriterion(Name.HURT_BY_TROLL, hurtByTroll)
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
                        Name.KILL_TROLL,
                        Name.BREWED_PHOENIX,
                        Name.BREWED_PHOENIX_LONG,
                        Name.BREWED_PHOENIX_STRONG,
                        Name.BREWED_JACKALOPE,
                        Name.BREWED_JACKALOPE_LONG,
                        Name.BREWED_JACKALOPE_STRONG,
                        Name.BREWED_UNICORN,
                        Name.BREWED_TROLL,
                        Name.BREWED_TROLL_LONG,
                        Name.BREWED_TROLL_STRONG,
                        Name.HURT_BY_UNICORN,
                        Name.HURT_BY_TROLL)))
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
        Advancement.Builder.advancement()
                .parent(root)
                .display(
                        PotionContents.createItemStack(Items.POTION, Potions.LUCK),
                        Component.translatable(title(Name.ADV_ALCHEMIST)),
                        Component.translatable(desc(Name.ADV_ALCHEMIST)),
                        null,
                        AdvancementType.GOAL,
                        true, false, false
                )
                .addCriterion(Name.BREWED_PHOENIX, brewedPhoenix)
                .addCriterion(Name.BREWED_PHOENIX_LONG, brewedPhoenixLong)
                .addCriterion(Name.BREWED_PHOENIX_STRONG, brewedPhoenixStrong)
                .addCriterion(Name.BREWED_JACKALOPE, brewedJackalope)
                .addCriterion(Name.BREWED_JACKALOPE_LONG, brewedJackalopeLong)
                .addCriterion(Name.BREWED_JACKALOPE_STRONG, brewedJackalopeStrong)
                .addCriterion(Name.BREWED_UNICORN, brewedUnicorn)
                .addCriterion(Name.BREWED_TROLL, brewedTroll)
                .addCriterion(Name.BREWED_TROLL_LONG, brewedTrollLong)
                .addCriterion(Name.BREWED_TROLL_STRONG, brewedTrollStrong)
                .requirements(new AdvancementRequirements(List.of(
                        List.of(Name.BREWED_PHOENIX, Name.BREWED_PHOENIX_LONG, Name.BREWED_PHOENIX_STRONG),
                        List.of(Name.BREWED_JACKALOPE, Name.BREWED_JACKALOPE_LONG, Name.BREWED_JACKALOPE_STRONG),
                        List.of(Name.BREWED_UNICORN),
                        List.of(Name.BREWED_TROLL, Name.BREWED_TROLL_LONG, Name.BREWED_TROLL_STRONG))))
                .save(consumer, MysticalCreatures.of(Name.ADV_ALCHEMIST));
    }

    public static String title(final String name) {
        return String.format("advancements.%s.%s.title", Name.MODID, name);
    }

    public static String desc(final String desc) {
        return String.format("advancements.%s.%s.description", Name.MODID, desc);
    }

    protected static Criterion<InventoryChangeTrigger.TriggerInstance> pickup(final ItemLike item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }
    
    protected static EntityPredicate entityType(final EntityType<?> type) {
        return EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(null, type)).build();
    }

    protected static Criterion<BredAnimalsTrigger.TriggerInstance> bredAnimals(final EntityType<?> child) {
        return BredAnimalsTrigger.TriggerInstance.bredAnimals(Optional.empty(), Optional.empty(), Optional.of(entityType(child)));
    }

    protected static Criterion<KilledTrigger.TriggerInstance> killed(final EntityType<?> type) {
        return KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(entityType(type)));
    }

    protected static Criterion<BrewedPotionTrigger.TriggerInstance> brewedPotion(final Holder<Potion> potion) {
        return CriteriaTriggers.BREWED_POTION.createCriterion(new BrewedPotionTrigger.TriggerInstance(Optional.empty(), Optional.of(potion)));
    }

    protected static Criterion<EntityHurtPlayerTrigger.TriggerInstance> hurtBy(final EntityType<?> type) {
        return EntityHurtPlayerTrigger.TriggerInstance.entityHurtPlayer(DamagePredicate.Builder.damageInstance().sourceEntity(entityType(type)));
    }

}
