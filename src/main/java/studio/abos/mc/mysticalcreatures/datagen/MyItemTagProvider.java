package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

import java.util.concurrent.CompletableFuture;

public class MyItemTagProvider extends ItemTagsProvider {

    public MyItemTagProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider, final CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        tag(MysticalCreatures.PHOENIX_FOOD).add(Items.MAGMA_CREAM);
        tag(MysticalCreatures.JACKALOPE_FOOD).add(Items.RABBIT);
        tag(MysticalCreatures.UNICORN_FOOD).add(Items.LAPIS_LAZULI);
        tag(MysticalCreatures.TROLL_FOOD).add(Items.BEEF);
    }
}
