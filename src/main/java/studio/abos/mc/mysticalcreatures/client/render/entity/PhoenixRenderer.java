package studio.abos.mc.mysticalcreatures.client.render.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;
import studio.abos.mc.mysticalcreatures.client.model.entity.PhoenixModel;
import studio.abos.mc.mysticalcreatures.client.render.state.PhoenixRenderState;
import studio.abos.mc.mysticalcreatures.entity.PhoenixEntity;

@OnlyIn(Dist.CLIENT)
public class PhoenixRenderer extends AgeableMobRenderer<PhoenixEntity, PhoenixRenderState, PhoenixModel> {

    public PhoenixRenderer(EntityRendererProvider.Context context) {
        super(context, new PhoenixModel(context.bakeLayer(MysticalCreatures.PHOENIX_LAYER)), new PhoenixModel(context.bakeLayer(MysticalCreatures.PHOENIX_LAYER)), 0.5f);

    }

    @Override
    public ResourceLocation getTextureLocation(PhoenixRenderState phoenixRenderState) {
        return MysticalCreatures.of(Name.PHOENIX);
    }

    @Override
    public PhoenixRenderState createRenderState() {
        return new PhoenixRenderState();
    }
}
