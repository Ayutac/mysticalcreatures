package studio.abos.mc.mysticalcreatures.datagen.lootmodifier;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SimpleLootModifier extends LootModifier {

    protected final Item drop;

    public static final MapCodec<SimpleLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("drop").forGetter(e -> e.drop)).apply(inst, SimpleLootModifier::new));

    public SimpleLootModifier(final LootItemCondition[] conditions, final Item drop) {
        super(conditions);
        this.drop = Objects.requireNonNull(drop);
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(final @NotNull ObjectArrayList<ItemStack> generatedLoot, final @NotNull LootContext lootContext) {
        generatedLoot.add(drop.getDefaultInstance());
        return generatedLoot;
    }

    @NotNull
    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
