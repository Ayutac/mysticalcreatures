package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;

public class MyLanguageProvider extends LanguageProvider {
    public MyLanguageProvider(final PackOutput output) {
        super(output, Name.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + Name.MODID, "Mystical Creatures");

        addItem(MysticalCreatures.PHOENIX_SPAWN_EGG, "Phoenix Spawn Egg");
        addItem(MysticalCreatures.PHOENIX_FEATHER, "Phoenix Feather");
        addItem(MysticalCreatures.JACKALOPE_SPAWN_EGG, "Jackalope Spawn Egg");
        addItem(MysticalCreatures.JACKALOPE_ANTLERS, "Jackalope Antlers");
        addItem(MysticalCreatures.UNICORN_SPAWN_EGG, "Unicorn Spawn Egg");
        addItem(MysticalCreatures.UNICORN_HORN, "Unicorn Horn");
        addItem(MysticalCreatures.TROLL_SPAWN_EGG, "Troll Spawn Egg");
        addItem(MysticalCreatures.TROLL_HEART, "Troll Heart");

        addEntityType(MysticalCreatures.PHOENIX_ENTITY, "Phoenix");
        addEntityType(MysticalCreatures.JACKALOPE_ENTITY, "Jackalope");
        addEntityType(MysticalCreatures.UNICORN_ENTITY, "Unicorn");
        addEntityType(MysticalCreatures.TROLL_ENTITY, "Troll");
    }
}
