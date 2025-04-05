package studio.abos.mc.mysticalcreatures.client.render.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;
import studio.abos.mc.mysticalcreatures.client.model.entity.PhoenixModel;
import studio.abos.mc.mysticalcreatures.client.render.state.PhoenixRenderState;
import studio.abos.mc.mysticalcreatures.entity.PhoenixEntity;

@OnlyIn(Dist.CLIENT)
public class PhoenixRenderer extends AgeableMobRenderer<PhoenixEntity, PhoenixRenderState, PhoenixModel> {

    public PhoenixRenderer(EntityRendererProvider.Context context) {
        super(context, new PhoenixModel(context.bakeLayer(MysticalCreatures.PHOENIX_LAYER)), new PhoenixModel(context.bakeLayer(MysticalCreatures.PHOENIX_BABY_LAYER)), 0.3f);

    }

    @Override
    public ResourceLocation getTextureLocation(PhoenixRenderState phoenixRenderState) {
        return MysticalCreatures.of("textures/entity/" + Name.PHOENIX + ".png");
    }

    @NotNull
    @Override
    public PhoenixRenderState createRenderState() {
        return new PhoenixRenderState();
    }

    @Override
    public void extractRenderState(final @NotNull PhoenixEntity phoenix, final @NotNull PhoenixRenderState state, final float p_364120_) {
        super.extractRenderState(phoenix, state, p_364120_);
        state.setFlap(Mth.lerp(p_364120_, phoenix.getoFlap(), phoenix.getFlap()));
        state.setFlapSpeed(Mth.lerp(p_364120_, phoenix.getoFlapSpeed(), phoenix.getFlapSpeed()));
    }
}
