package studio.abos.mc.mysticalcreatures.client.render.entity;

import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.Name;
import studio.abos.mc.mysticalcreatures.client.model.entity.UnicornModel;
import studio.abos.mc.mysticalcreatures.client.render.state.UnicornRenderState;
import studio.abos.mc.mysticalcreatures.entity.UnicornEntity;

@OnlyIn(Dist.CLIENT)
public class UnicornRenderer extends AgeableMobRenderer<UnicornEntity, UnicornRenderState, UnicornModel> {

    public UnicornRenderer(EntityRendererProvider.Context context) {
        super(context, new UnicornModel(context.bakeLayer(MysticalCreatures.UNICORN_LAYER)), new UnicornModel(context.bakeLayer(MysticalCreatures.UNICORN_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(UnicornRenderState UnicornRenderState) {
        return MysticalCreatures.of(Name.UNICORN);
    }

    @Override
    public UnicornRenderState createRenderState() {
        return new UnicornRenderState();
    }
}
