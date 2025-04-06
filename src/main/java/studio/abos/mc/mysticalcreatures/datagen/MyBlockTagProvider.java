package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;

import java.util.concurrent.CompletableFuture;

public class MyBlockTagProvider extends BlockTagsProvider {
    public MyBlockTagProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Name.MODID);
    }

    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        tag(MysticalCreatures.PHOENIX_SPAWNABLE_ON).add(Blocks.BASALT, Blocks.BLACKSTONE);
    }
}
