package studio.abos.mc.mysticalcreatures.client.render.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UnicornRenderState extends LivingEntityRenderState {
    protected boolean animateTail;
    protected float eatAnimation;
    protected float standAnimation;
    protected int attackAnimationRemainingTicks;

    public boolean isAnimateTail() {
        return animateTail;
    }

    public void setAnimateTail(final boolean animateTail) {
        this.animateTail = animateTail;
    }

    public float getEatAnimation() {
        return eatAnimation;
    }

    public void setEatAnimation(final float eatAnimation) {
        this.eatAnimation = eatAnimation;
    }

    public float getStandAnimation() {
        return standAnimation;
    }

    public void setStandAnimation(final float standAnimation) {
        this.standAnimation = standAnimation;
    }

    public int getAttackAnimationRemainingTicks() {
        return attackAnimationRemainingTicks;
    }

    public void setAttackAnimationRemainingTicks(final int attackAnimationRemainingTicks) {
        this.attackAnimationRemainingTicks = attackAnimationRemainingTicks;
    }
}
