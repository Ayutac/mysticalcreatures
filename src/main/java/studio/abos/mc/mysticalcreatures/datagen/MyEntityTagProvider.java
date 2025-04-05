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
        tag(MysticalCreatures.JACKALOPE_AVOIDS).add(EntityType.WOLF);
    }
}
