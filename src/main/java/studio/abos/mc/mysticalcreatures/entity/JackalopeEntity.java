package studio.abos.mc.mysticalcreatures.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

public class JackalopeEntity extends Animal {
    public JackalopeEntity(final EntityType<? extends JackalopeEntity> entityType, final Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createJackalopeAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 15d).add(Attributes.MOVEMENT_SPEED, 0.25d);
    }

    @Override
    public boolean isFood(final ItemStack itemStack) {
        return itemStack.is(MysticalCreatures.JACKALOPE_FOOD);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.8));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Animal.class, 10.0F, 2.2, 2.2,
                livingEntity -> EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingEntity) && livingEntity.getType().is(MysticalCreatures.JACKALOPE_AVOIDS)));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 10.0F));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(final @NotNull ServerLevel serverLevel, final @NotNull AgeableMob ageableMob) {
        final AgeableMob offspring = MysticalCreatures.JACKALOPE_ENTITY.get().create(serverLevel, EntitySpawnReason.BREEDING);
        if (offspring != null) {
            offspring.setBaby(true);
        }
        return offspring;
    }
}
