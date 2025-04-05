package studio.abos.mc.mysticalcreatures.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;

public class JackalopeEntity extends AbstractMysticalCreature {
    protected int jumpTicks;
    protected int jumpDuration;
    protected boolean wasOnGround;
    protected int jumpDelayTicks;

    public JackalopeEntity(final EntityType<? extends JackalopeEntity> entityType, final Level level) {
        super(entityType, level, MysticalCreatures.JACKALOPE_FOOD);
        this.jumpControl = new JackalopeJumpControl(this);
        this.moveControl = new JackalopeMoveControl(this);
    }

    public static AttributeSupplier.Builder createJackalopeAttributes() {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 15d)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.FOLLOW_RANGE, 20d)
                .add(Attributes.ATTACK_DAMAGE, 3d);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5f, true));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.8));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Animal.class, 10.0F, 2.2, 2.2,
                livingEntity -> EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingEntity) && livingEntity.getType().is(MysticalCreatures.JACKALOPE_AVOIDS)));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Animal.class, 10, true, false,
                (livingEntity, serverLevel) -> livingEntity.getType().is(MysticalCreatures.JACKALOPE_HUNTS)));
        this.targetSelector.addGoal(5, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    @Override
    public void startPersistentAngerTimer() {
        remainingPersistentAngerTime = TimeUtil.rangeOfSeconds(20, 39).sample(random);
    }

    protected SoundEvent getJumpSound() {
        return MysticalCreatures.JACKALOPE_JUMP_SOUND.value();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MysticalCreatures.JACKALOPE_AMBIENT_SOUND.value();
    }

    @Override
    protected SoundEvent getHurtSound(final @NotNull DamageSource damageSource) {
        return MysticalCreatures.JACKALOPE_HURT_SOUND.value();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MysticalCreatures.JACKALOPE_DEATH_SOUND.value();
    }

    @Override
    public void playAttackSound() {
        playSound(MysticalCreatures.JACKALOPE_ATTACK_SOUND.value(), 1f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1f);
    }

    @NotNull
    @Override
    public SoundSource getSoundSource() {
        return SoundSource.NEUTRAL;
    }

    @NotNull
    @Override
    public Vec3 getLeashOffset() {
        return new Vec3(0d, 0.6 * this.getEyeHeight(), this.getBbWidth() * 0.4);
    }

    @Override
    protected float getJumpPower() {
        float f = 0.3f;
        if (this.moveControl.getSpeedModifier() <= 0.6) {
            f = 0.2f;
        }

        Path path = this.navigation.getPath();
        if (path != null && !path.isDone()) {
            Vec3 vec3 = path.getNextEntityPos(this);
            if (vec3.y > this.getY() + 0.5) {
                f = 0.5f;
            }
        }

        if (this.horizontalCollision || this.jumping && this.moveControl.getWantedY() > this.getY() + 0.5) {
            f = 0.5f;
        }

        return super.getJumpPower(f / 0.42f * 1.5f); // 1.5 is the jackalope factor
    }

    // rest is mostly copy-paste from Rabbit class

    @Override
    public void jumpFromGround() {
        super.jumpFromGround();
        double d0 = this.moveControl.getSpeedModifier();
        if (d0 > 0d) {
            double d1 = this.getDeltaMovement().horizontalDistanceSqr();
            if (d1 < 0.01) {
                this.moveRelative(0.1f, new Vec3(0d, 0d, 1d));
            }
        }

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)1);
        }
    }

    public float getJumpCompletion(final float partialTick) {
        return this.jumpDuration == 0 ? 0f : ((float)this.jumpTicks + partialTick) / (float)this.jumpDuration;
    }

    public void setSpeedModifier(final double speedModifier) {
        this.getNavigation().setSpeedModifier(speedModifier);
        this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), speedModifier);
    }

    @Override
    public void setJumping(boolean jumping) {
        super.setJumping(jumping);
        if (jumping) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f) * 0.8f);
        }

    }

    public void startJumping() {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    private void facePoint(double x, double z) {
        this.setYRot((float)(Mth.atan2(z - this.getZ(), x - this.getX()) * 180d / (double)(float)Math.PI) - 90f);
    }

    private void enableJumpControl() {
        ((JackalopeJumpControl)this.jumpControl).setCanJump(true);
    }

    private void disableJumpControl() {
        ((JackalopeJumpControl)this.jumpControl).setCanJump(false);
    }

    private void setLandingDelay() {
        if (this.moveControl.getSpeedModifier() < 2.2) {
            this.jumpDelayTicks = 2;
        } else {
            this.jumpDelayTicks = 1;
        }
    }

    private void checkLandingDelay() {
        this.setLandingDelay();
        this.disableJumpControl();
    }

    @Override
    public void customServerAiStep(final @NotNull ServerLevel level) {
        if (this.jumpDelayTicks > 0) {
            --this.jumpDelayTicks;
        }

        if (this.onGround()) {
            if (!this.wasOnGround) {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            if (this.jumpDelayTicks == 0) {
                LivingEntity livingentity = this.getTarget();
                if (livingentity != null && this.distanceToSqr(livingentity) < 16d) {
                    this.facePoint(livingentity.getX(), livingentity.getZ());
                    this.moveControl.setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), this.moveControl.getSpeedModifier());
                    this.startJumping();
                    this.wasOnGround = true;
                }
            }

            JackalopeJumpControl jackalope$jackalopejumpcontrol = (JackalopeJumpControl)this.jumpControl;
            if (!jackalope$jackalopejumpcontrol.wantJump()) {
                if (this.moveControl.hasWanted() && this.jumpDelayTicks == 0) {
                    Path path = this.navigation.getPath();
                    Vec3 vec3 = new Vec3(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
                    if (path != null && !path.isDone()) {
                        vec3 = path.getNextEntityPos(this);
                    }

                    this.facePoint(vec3.x, vec3.z);
                    this.startJumping();
                }
            } else if (!jackalope$jackalopejumpcontrol.canJump()) {
                this.enableJumpControl();
            }
        }

        this.wasOnGround = this.onGround();
    }

    private static class JackalopeJumpControl extends JumpControl {
        private final JackalopeEntity jackalope;
        private boolean canJump;

        public JackalopeJumpControl(JackalopeEntity jackalope) {
            super(jackalope);
            this.jackalope = jackalope;
        }

        public boolean wantJump() {
            return this.jump;
        }

        public boolean canJump() {
            return this.canJump;
        }

        public void setCanJump(boolean canJump) {
            this.canJump = canJump;
        }

        @Override
        public void tick() {
            if (this.jump) {
                this.jackalope.startJumping();
                this.jump = false;
            }
        }
    }

    private static class JackalopeMoveControl extends MoveControl {
        private final JackalopeEntity jackalope;
        private double nextJumpSpeed;

        public JackalopeMoveControl(JackalopeEntity jackalope) {
            super(jackalope);
            this.jackalope = jackalope;
        }

        @Override
        public void tick() {
            if (this.jackalope.onGround() && !this.jackalope.jumping && !((JackalopeJumpControl)this.jackalope.jumpControl).wantJump()) {
                this.jackalope.setSpeedModifier(0d);
            } else if (this.hasWanted() || this.operation == net.minecraft.world.entity.ai.control.MoveControl.Operation.JUMPING) {
                this.jackalope.setSpeedModifier(this.nextJumpSpeed);
            }

            super.tick();
        }

        @Override
        public double getSpeedModifier() {
            return super.getSpeedModifier() * 1.5; // the Jackalope extra
        }

        @Override
        public void setWantedPosition(double x, double y, double z, double speed) {
            if (this.jackalope.isInWater()) {
                speed = 1.5d;
            }

            super.setWantedPosition(x, y, z, speed);
            if (speed > 0d) {
                this.nextJumpSpeed = speed;
            }

        }
    }
}
