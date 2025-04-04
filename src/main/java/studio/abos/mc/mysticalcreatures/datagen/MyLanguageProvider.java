package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;

public class MyLanguageProvider extends LanguageProvider {

    private static final String ITEM_MINECRAFT_POTION_EFFECT = "item.minecraft.potion.effect.";
    private static final String POTION_OF = "Potion of ";
    private static final String ITEM_MINECRAFT_SPLASH_POTION_EFFECT = "item.minecraft.splash_potion.effect.";
    private static final String SPLASH_POTION_OF = "Splash Potion of ";
    private static final String ITEM_MINECRAFT_LINGERING_POTION_EFFECT = "item.minecraft.lingering_potion.effect.";
    private static final String LINGERING_POTION_OF = "Lingering Potion of ";
    private static final String ITEM_MINECRAFT_TIPPED_ARROW_EFFECT = "item.minecraft.tipped_arrow.effect.";
    private static final String ARROW_OF = "Arrow of ";

    public MyLanguageProvider(final PackOutput output) {
        super(output, Name.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + Name.MODID, "Mystical Creatures");

        // regular items and spawn eggs
        addItem(MysticalCreatures.PHOENIX_SPAWN_EGG, "Phoenix Spawn Egg");
        addItem(MysticalCreatures.PHOENIX_FEATHER, "Phoenix Feather");
        addItem(MysticalCreatures.JACKALOPE_SPAWN_EGG, "Jackalope Spawn Egg");
        addItem(MysticalCreatures.JACKALOPE_ANTLERS, "Jackalope Antlers");
        addItem(MysticalCreatures.UNICORN_SPAWN_EGG, "Unicorn Spawn Egg");
        addItem(MysticalCreatures.UNICORN_HORN, "Unicorn Horn");
        addItem(MysticalCreatures.TROLL_SPAWN_EGG, "Troll Spawn Egg");
        addItem(MysticalCreatures.TROLL_HEART, "Troll Heart");

        // potions
        addPotions(Name.PHOENIX, "the Phoenix Master");
        addPotions(Name.JACKALOPE, "the Jackalope Master");
        // nothing for the unicorn because that's just a luck potion
        addPotions(Name.TROLL, "the Troll Master");

        // entities
        addEntityType(MysticalCreatures.PHOENIX_ENTITY, "Phoenix");
        addEntityType(MysticalCreatures.JACKALOPE_ENTITY, "Jackalope");
        addEntityType(MysticalCreatures.UNICORN_ENTITY, "Unicorn");
        addEntityType(MysticalCreatures.TROLL_ENTITY, "Troll");

        // advancements
        add(MyAdvancementProvider.title(Name.ADV_ROOT), "Mystical Creatures");
        add(MyAdvancementProvider.desc(Name.ADV_ROOT), "So mysterious!");
        add(MyAdvancementProvider.title(Name.ADV_BREEDER), "Breeder");
        add(MyAdvancementProvider.desc(Name.ADV_BREEDER), "You saw too much.");
        add(MyAdvancementProvider.title(Name.ADV_COLLECTOR), "Collector");
        add(MyAdvancementProvider.desc(Name.ADV_COLLECTOR), "These are valuable!");
        add(MyAdvancementProvider.title(Name.ADV_HUNTER), "Hunter");
        add(MyAdvancementProvider.desc(Name.ADV_HUNTER), "Gotta kill 'em all!");
    }
    
    protected void addPotions(final String name, final String title) {
        add(ITEM_MINECRAFT_POTION_EFFECT + name, POTION_OF + title);
        add(ITEM_MINECRAFT_SPLASH_POTION_EFFECT + name, SPLASH_POTION_OF + title);
        add(ITEM_MINECRAFT_LINGERING_POTION_EFFECT + name, LINGERING_POTION_OF + title);
        add(ITEM_MINECRAFT_TIPPED_ARROW_EFFECT + name, ARROW_OF + title);
        add(ITEM_MINECRAFT_POTION_EFFECT + name + MysticalCreatures._LONG, POTION_OF + title);
        add(ITEM_MINECRAFT_SPLASH_POTION_EFFECT + name + MysticalCreatures._LONG, SPLASH_POTION_OF + title);
        add(ITEM_MINECRAFT_LINGERING_POTION_EFFECT + name + MysticalCreatures._LONG, LINGERING_POTION_OF + title);
        add(ITEM_MINECRAFT_TIPPED_ARROW_EFFECT + name + MysticalCreatures._LONG, ARROW_OF + title);
        add(ITEM_MINECRAFT_POTION_EFFECT + name + MysticalCreatures._STRONG, POTION_OF + title);
        add(ITEM_MINECRAFT_SPLASH_POTION_EFFECT + name + MysticalCreatures._STRONG, SPLASH_POTION_OF + title);
        add(ITEM_MINECRAFT_LINGERING_POTION_EFFECT + name + MysticalCreatures._STRONG, LINGERING_POTION_OF + title);
        add(ITEM_MINECRAFT_TIPPED_ARROW_EFFECT + name + MysticalCreatures._STRONG, ARROW_OF + title);
    }

}
