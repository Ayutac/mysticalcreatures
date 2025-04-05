package studio.abos.mc.mysticalcreatures.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;

import java.util.concurrent.CompletableFuture;

public class MyEntityTagProvider extends EntityTypeTagsProvider {
    public MyEntityTagProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Name.MODID);
    }

    @Override
    protected void addTags(final @NotNull HolderLookup.Provider lookupProvider) {
        tag(MysticalCreatures.PHOENIX_AVOIDS).add(EntityType.BLAZE);
        tag(MysticalCreatures.JACKALOPE_AVOIDS).add(EntityType.WOLF);
        tag(MysticalCreatures.UNICORN_AVOIDS).add(MysticalCreatures.TROLL_ENTITY.get());
        tag(MysticalCreatures.TROLL_AVOIDS);
        tag(MysticalCreatures.PHOENIX_HUNTS).add(EntityType.CHICKEN);
        tag(MysticalCreatures.JACKALOPE_HUNTS).add(EntityType.RABBIT);
        tag(MysticalCreatures.UNICORN_HUNTS);
        tag(MysticalCreatures.TROLL_HUNTS).add(EntityType.COW);
    }
}
