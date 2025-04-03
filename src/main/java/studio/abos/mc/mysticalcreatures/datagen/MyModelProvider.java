package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

public class MyModelProvider extends ModelProvider {
    public MyModelProvider(PackOutput output) {
        super(output, MysticalCreatures.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(MysticalCreatures.PHOENIX_FEATHER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MysticalCreatures.JACKALOPE_ANTLERS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MysticalCreatures.UNICORN_HORN.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MysticalCreatures.TROLL_HEART.get(), ModelTemplates.FLAT_ITEM);
    }
}
