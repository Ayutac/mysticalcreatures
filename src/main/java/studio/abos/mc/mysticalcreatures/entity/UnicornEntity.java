package studio.abos.mc.mysticalcreatures.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

public class UnicornEntity extends Animal {
    public UnicornEntity(final EntityType<? extends UnicornEntity> entityType, final Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createUnicornAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 15d).add(Attributes.MOVEMENT_SPEED, 0.25d);
    }

    @Override
    public boolean isFood(final ItemStack itemStack) {
        return itemStack.is(MysticalCreatures.UNICORN_BREEDING_ITEMS);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(final ServerLevel serverLevel, final AgeableMob ageableMob) {
        final AgeableMob offspring = new UnicornEntity(MysticalCreatures.UNICORN_ENTITY.get(), serverLevel);
        offspring.setBaby(true);
        return offspring;
    }
}
