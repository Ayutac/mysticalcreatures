package studio.abos.mc.mysticalcreatures.client.render.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PhoenixRenderState extends LivingEntityRenderState {
    protected float flap;
    protected float flapSpeed;

    public float getFlap() {
        return flap;
    }

    public void setFlap(float flap) {
        this.flap = flap;
    }

    public float getFlapSpeed() {
        return flapSpeed;
    }

    public void setFlapSpeed(float flapSpeed) {
        this.flapSpeed = flapSpeed;
    }
}
