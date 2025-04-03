package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

public class MyLanguageProvider extends LanguageProvider {
    public MyLanguageProvider(PackOutput output) {
        super(output, MysticalCreatures.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.mysticalcreatures", "Mystical Creatures");
        addItem(MysticalCreatures.PHOENIX_FEATHER, "Phoenix Feather");
        addItem(MysticalCreatures.JACKALOPE_ANTLERS, "Jackalope Antlers");
        addItem(MysticalCreatures.UNICORN_HORN, "Unicorn Horn");
        addItem(MysticalCreatures.TROLL_HEART, "Troll Heart");
    }
}
