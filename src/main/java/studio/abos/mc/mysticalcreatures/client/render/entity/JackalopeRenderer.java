package studio.abos.mc.mysticalcreatures.client.render.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.client.model.entity.JackalopeModel;
import studio.abos.mc.mysticalcreatures.client.render.state.JackalopeRenderState;
import studio.abos.mc.mysticalcreatures.entity.JackalopeEntity;

@OnlyIn(Dist.CLIENT)
public class JackalopeRenderer extends AgeableMobRenderer<JackalopeEntity, JackalopeRenderState, JackalopeModel> {

    public JackalopeRenderer(EntityRendererProvider.Context context) {
        super(context, new JackalopeModel(context.bakeLayer(MysticalCreatures.JACKALOPE_LAYER)), new JackalopeModel(context.bakeLayer(MysticalCreatures.JACKALOPE_LAYER)), 0.5f);

    }

    @Override
    public ResourceLocation getTextureLocation(JackalopeRenderState JackalopeRenderState) {
        return MysticalCreatures.of("jackalope_entity");
    }

    @Override
    public JackalopeRenderState createRenderState() {
        return new JackalopeRenderState();
    }
}
