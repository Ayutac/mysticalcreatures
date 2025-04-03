package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

import java.util.concurrent.CompletableFuture;

public class MyBlockTagProvider extends BlockTagsProvider {
    public MyBlockTagProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MysticalCreatures.MODID);
    }

    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        // nothing to add
    }
}
