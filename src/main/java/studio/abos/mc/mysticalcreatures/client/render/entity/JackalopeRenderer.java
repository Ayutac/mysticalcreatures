package studio.abos.mc.mysticalcreatures.client.render.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;
import studio.abos.mc.mysticalcreatures.client.model.entity.JackalopeModel;
import studio.abos.mc.mysticalcreatures.client.render.state.JackalopeRenderState;
import studio.abos.mc.mysticalcreatures.entity.JackalopeEntity;

@OnlyIn(Dist.CLIENT)
public class JackalopeRenderer extends AgeableMobRenderer<JackalopeEntity, JackalopeRenderState, JackalopeModel> {

    public JackalopeRenderer(EntityRendererProvider.Context context) {
        super(context, new JackalopeModel(context.bakeLayer(MysticalCreatures.JACKALOPE_LAYER)), new JackalopeModel(context.bakeLayer(MysticalCreatures.JACKALOPE_BABY_LAYER)), 0.5f);
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(final @NotNull JackalopeRenderState JackalopeRenderState) {
        return MysticalCreatures.of("textures/entity/" + Name.JACKALOPE + ".png");
    }

    @NotNull
    @Override
    public JackalopeRenderState createRenderState() {
        return new JackalopeRenderState();
    }
}
