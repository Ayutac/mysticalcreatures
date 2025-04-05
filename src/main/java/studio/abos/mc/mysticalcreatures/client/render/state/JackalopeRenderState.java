package studio.abos.mc.mysticalcreatures.client.render.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JackalopeRenderState extends LivingEntityRenderState {
    protected float jumpCompletion;

    public float getJumpCompletion() {
        return jumpCompletion;
    }

    public void setJumpCompletion(float jumpCompletion) {
        this.jumpCompletion = jumpCompletion;
    }
}
