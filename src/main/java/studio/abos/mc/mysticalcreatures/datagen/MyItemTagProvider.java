package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

import java.util.concurrent.CompletableFuture;

public class MyItemTagProvider extends ItemTagsProvider {

    public MyItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(MysticalCreatures.PHOENIX_BREEDING_ITEMS).add(Items.MAGMA_CREAM);
        tag(MysticalCreatures.JACKALOPE_BREEDING_ITEMS).add(Items.RABBIT);
        tag(MysticalCreatures.UNICORN_BREEDING_ITEMS).add(Items.LAPIS_LAZULI);
        tag(MysticalCreatures.TROLL_BREEDING_ITEMS).add(Items.BEEF);
    }
}
