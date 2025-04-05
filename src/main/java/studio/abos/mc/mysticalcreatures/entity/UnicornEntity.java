package studio.abos.mc.mysticalcreatures.entity;

import net.minecraft.util.TimeUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

public class UnicornEntity extends AbstractMysticalCreature {
    public UnicornEntity(final EntityType<? extends UnicornEntity> entityType, final Level level) {
        super(entityType, level, MysticalCreatures.UNICORN_FOOD);
    }

    public static AttributeSupplier.Builder createUnicornAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 15d).add(Attributes.MOVEMENT_SPEED, 0.25d);
    }

    @Override
    public void startPersistentAngerTimer() {
        remainingPersistentAngerTime = TimeUtil.rangeOfSeconds(20, 39).sample(random);
    }
}
