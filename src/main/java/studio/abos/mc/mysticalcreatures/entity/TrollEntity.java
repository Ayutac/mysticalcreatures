package studio.abos.mc.mysticalcreatures.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

public class TrollEntity extends Animal {
    public TrollEntity(final EntityType<? extends TrollEntity> entityType, final Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createTrollAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 15d).add(Attributes.MOVEMENT_SPEED, 0.25d);
    }

    @Override
    public boolean isFood(final ItemStack itemStack) {
        return itemStack.is(MysticalCreatures.TROLL_FOOD);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(final @NotNull ServerLevel serverLevel, final @NotNull AgeableMob ageableMob) {
        final AgeableMob offspring = MysticalCreatures.TROLL_ENTITY.get().create(serverLevel, EntitySpawnReason.BREEDING);
        if (offspring != null) {
            offspring.setBaby(true);
        }
        return offspring;
    }
}
