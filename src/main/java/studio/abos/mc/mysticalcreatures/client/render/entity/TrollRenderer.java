package studio.abos.mc.mysticalcreatures.client.render.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;
import studio.abos.mc.mysticalcreatures.client.model.entity.TrollModel;
import studio.abos.mc.mysticalcreatures.client.render.state.TrollRenderState;
import studio.abos.mc.mysticalcreatures.entity.TrollEntity;

@OnlyIn(Dist.CLIENT)
public class TrollRenderer extends AgeableMobRenderer<TrollEntity, TrollRenderState, TrollModel> {

    public TrollRenderer(EntityRendererProvider.Context context) {
        super(context, new TrollModel(context.bakeLayer(MysticalCreatures.TROLL_LAYER)), new TrollModel(context.bakeLayer(MysticalCreatures.TROLL_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(TrollRenderState TrollRenderState) {
        return MysticalCreatures.of(Name.TROLL);
    }

    @Override
    public TrollRenderState createRenderState() {
        return new TrollRenderState();
    }
}
