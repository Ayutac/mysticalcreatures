package studio.abos.mc.mysticalcreatures.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public abstract class AbstractMysticalCreature extends Animal implements NeutralMob {

    final protected TagKey<Item> food;

    protected int remainingPersistentAngerTime;
    @Nullable
    protected UUID persistentAngerTarget;

    protected AbstractMysticalCreature(final EntityType<? extends AbstractMysticalCreature> entityType, final Level level, final TagKey<Item> food) {
        super(entityType, level);
        this.food = Objects.requireNonNull(food);
    }

    @Override
    public boolean isFood(final ItemStack itemStack) {
        return itemStack.is(food);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(final @NotNull ServerLevel serverLevel, final @NotNull AgeableMob ageableMob) {
        final AgeableMob offspring = (AgeableMob)getType().create(serverLevel, EntitySpawnReason.BREEDING);
        if (offspring != null) {
            offspring.setBaby(true);
        }
        return offspring;
    }

    @Override
    public void readAdditionalSaveData(final @NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.readPersistentAngerSaveData(this.level(), compound);
    }

    @Override
    public void addAdditionalSaveData(final @NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        this.addPersistentAngerSaveData(compound);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return remainingPersistentAngerTime;
    }

    @Override
    public void setRemainingPersistentAngerTime(final int i) {
        remainingPersistentAngerTime = i;
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(final @Nullable UUID uuid) {
        persistentAngerTarget = uuid;
    }
}
