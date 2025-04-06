package studio.abos.mc.mysticalcreatures.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.entity.ai.goal.PhoenixAttackGoal;

public class PhoenixEntity extends AbstractMysticalCreature {
    protected float flap;
    protected float flapSpeed;
    protected float oFlapSpeed;
    protected float oFlap;
    public float flapping = 1.0F;
    private float nextFlap = 1.0F;

    public PhoenixEntity(final EntityType<? extends PhoenixEntity> entityType, final Level level) {
        super(entityType, level, MysticalCreatures.PHOENIX_FOOD);
    }

    public static AttributeSupplier.Builder createPhoenixAttributes() {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 15d)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.ATTACK_DAMAGE, 6d)
                .add(Attributes.FOLLOW_RANGE, 24d);
    }

    // rebirth the phoenix
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
            if (BaseFireBlock.canBePlacedAt(level(), firePos.below(), Direction.UP)) {
                level().setBlock(firePos, BaseFireBlock.getState(level(), firePos.below()), 11);
            }
        }
        super.tickDeath();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new PhoenixAttackGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0, stack -> stack.is(MysticalCreatures.PHOENIX_FOOD), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Animal.class, 10.0f, 1.4, 1.4,
                livingEntity -> EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingEntity) && livingEntity.getType().is(MysticalCreatures.PHOENIX_AVOIDS)));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Animal.class, 10, true, false,
                (livingEntity, serverLevel) -> livingEntity.getType().is(MysticalCreatures.PHOENIX_HUNTS)));
        this.targetSelector.addGoal(5, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    @Override
    public void startPersistentAngerTimer() {
        remainingPersistentAngerTime = TimeUtil.rangeOfSeconds(20, 39).sample(random);
    }

    public static boolean checkPhoenixSpawnRules(EntityType<? extends PhoenixEntity> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(MysticalCreatures.PHOENIX_SPAWNABLE_ON);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MysticalCreatures.PHOENIX_AMBIENT_SOUND.value();
    }

    @Override
    protected void playAttackSound() {
        playSound(MysticalCreatures.PHOENIX_ATTACK_SOUND.value(), 1f, 1f);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MysticalCreatures.PHOENIX_HURT_SOUND.value();
    }

    @Override
    protected SoundEvent getHurtSound(final @NotNull DamageSource damageSource) {
        return MysticalCreatures.PHOENIX_HURT_SOUND.value();
    }

    @Override
    protected void playStepSound(final @NotNull BlockPos pos, final @NotNull BlockState block) {
        this.playSound(MysticalCreatures.PHOENIX_STEP_SOUND.value(), 0.15f, 1.0f);
    }

    public float getoFlap() {
        return oFlap;
    }

    public float getFlap() {
        return flap;
    }

    public float getoFlapSpeed() {
        return oFlapSpeed;
    }

    public float getFlapSpeed() {
        return flapSpeed;
    }

    // blaze chicken hybrid code

    @Override
    public void aiStep() {
        super.aiStep();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = this.flapSpeed + (this.onGround() ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround() && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }
        if (!this.onGround() && this.getDeltaMovement().y < (double)0.0F) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0F, 0.6, 1.0F));
        }

        this.flapping *= 0.9F;
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0) {
            this.setDeltaMovement(vec3.multiply(1.0, 0.6, 1.0));
        }

        if (level().isClientSide()) {
            level().addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), 0d, 0d, 0d);
        }

        this.flap = this.flap + this.flapping * 2.0F;
    }

    // mostly copy-pasted from Chicken class

    @Override
    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    @Override
    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    // mostly copy-pasted from Blaze class

    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;

    protected void defineSynchedData(final SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte)0);
    }

    public boolean isOnFire() {
        return this.isCharged();
    }

    private boolean isCharged() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setCharged(boolean charged) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (charged) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    static {
        DATA_FLAGS_ID = SynchedEntityData.defineId(PhoenixEntity.class, EntityDataSerializers.BYTE);
    }
}
