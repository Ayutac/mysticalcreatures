package studio.abos.mc.mysticalcreatures.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

public class PhoenixEntity extends Animal {
    public PhoenixEntity(final EntityType<? extends PhoenixEntity> entityType, final Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createPhoenixAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 15d).add(Attributes.MOVEMENT_SPEED, 0.25d);
    }

    @Override
    public boolean isFood(final ItemStack itemStack) {
        return itemStack.is(MysticalCreatures.PHOENIX_FOOD);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(final ServerLevel serverLevel, final AgeableMob ageableMob) {
        final AgeableMob offspring = new PhoenixEntity(MysticalCreatures.PHOENIX_ENTITY.get(), serverLevel);
        offspring.setBaby(true);
        return offspring;
    }

    @Override
    protected void tickDeath() {
        if (deathTime >= 19 && !level().isClientSide() && !isRemoved() && !isBaby()) {
            final PhoenixEntity baby = new PhoenixEntity(MysticalCreatures.PHOENIX_ENTITY.get(), level());
            baby.setBaby(true);
            baby.setPos(getPosition(0f));
            baby.setCustomName(getCustomName());
            level().addFreshEntity(baby);
            final BlockPos firePos = getOnPos().above();
            if (BaseFireBlock.canBePlacedAt(level(), firePos, Direction.UP)) {
                level().setBlock(firePos, BaseFireBlock.getState(level(), firePos), 11);
            }
        }
        super.tickDeath();
    }
}
