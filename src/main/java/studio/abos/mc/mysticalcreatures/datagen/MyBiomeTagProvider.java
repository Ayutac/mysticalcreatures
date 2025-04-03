package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

import java.util.concurrent.CompletableFuture;

public class MyBiomeTagProvider extends BiomeTagsProvider {

    public MyBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, MysticalCreatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(MysticalCreatures.PHOENIX_SPAWNS_ON).add(Biomes.BASALT_DELTAS);
        tag(MysticalCreatures.JACKALOPE_SPAWNS_ON) // same as brown rabbits
                .add(Biomes.FLOWER_FOREST)
                .add(Biomes.TAIGA)
                .add(Biomes.MEADOW)
                .add(Biomes.OLD_GROWTH_PINE_TAIGA)
                .add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
                .add(Biomes.CHERRY_GROVE);
        tag(MysticalCreatures.UNICORN_SPAWNS_ON).addTag(BiomeTags.IS_FOREST);
        tag(MysticalCreatures.TROLL_SPAWNS_ON)
                .add(Biomes.RIVER)
                .add(Biomes.FROZEN_RIVER);
    }
}
