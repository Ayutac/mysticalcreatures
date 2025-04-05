package studio.abos.mc.mysticalcreatures.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.phys.Vec3;
import studio.abos.mc.mysticalcreatures.entity.PhoenixEntity;

import java.util.EnumSet;

// mostly copy-pasted from BlazeAttackGoal
public class PhoenixAttackGoal extends Goal {
    private final PhoenixEntity phoenix;
    private int attackStep;
    private int attackTime;
    private int lastSeen;

    public PhoenixAttackGoal(PhoenixEntity phoenix) {
        this.phoenix = phoenix;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.phoenix.getTarget();
        return livingentity != null && livingentity.isAlive() && this.phoenix.canAttack(livingentity);
    }

    public void start() {
        this.attackStep = 0;
    }

    public void stop() {
        this.phoenix.setCharged(false);
        this.lastSeen = 0;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        --this.attackTime;
        LivingEntity livingentity = this.phoenix.getTarget();
        if (livingentity != null) {
            boolean lineOfSight = this.phoenix.getSensing().hasLineOfSight(livingentity);
            if (lineOfSight) {
                this.lastSeen = 0;
            } else {
                ++this.lastSeen;
            }

            double d0 = this.phoenix.distanceToSqr(livingentity);
            if (d0 < (double)4.0F) {
                if (!lineOfSight) {
                    return;
                }

                if (this.attackTime <= 0) {
                    this.attackTime = 10;
                    this.phoenix.doHurtTarget(getServerLevel(this.phoenix), livingentity);
                }

                this.phoenix.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
            } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && lineOfSight) {
                double d1 = livingentity.getX() - this.phoenix.getX();
                double d2 = livingentity.getY(0.5F) - this.phoenix.getY(0.5F);
                double d3 = livingentity.getZ() - this.phoenix.getZ();
                if (this.attackTime <= 0) {
                    ++this.attackStep;
                    if (this.attackStep == 1) {
                        this.attackTime = 30;
                        this.phoenix.setCharged(true);
                    } else if (this.attackStep <= 4) {
                        this.attackTime = 6;
                    } else {
                        this.attackTime = 50;
                        this.attackStep = 0;
                        this.phoenix.setCharged(false);
                    }

                    if (this.attackStep > 3) { // only one fireball
                        if (!this.phoenix.isSilent()) {
                            this.phoenix.level().levelEvent(null, 1018, this.phoenix.blockPosition(), 0);
                        }
                        Vec3 vec3 = new Vec3(d1, d2, d3);
                        SmallFireball smallfireball = new SmallFireball(this.phoenix.level(), this.phoenix, vec3.normalize());
                        smallfireball.setPos(smallfireball.getX(), this.phoenix.getY(0.5) + 0.5, smallfireball.getZ());
                        this.phoenix.level().addFreshEntity(smallfireball);
                    }
                }

                this.phoenix.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
            } else if (this.lastSeen < 5) {
                this.phoenix.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
            }

            super.tick();
        }

    }

    private double getFollowDistance() {
        return this.phoenix.getAttributeValue(Attributes.FOLLOW_RANGE);
    }
}
