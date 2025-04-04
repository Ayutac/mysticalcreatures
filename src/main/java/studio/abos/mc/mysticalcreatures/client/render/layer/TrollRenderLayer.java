package studio.abos.mc.mysticalcreatures.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.client.model.entity.TrollModel;
import studio.abos.mc.mysticalcreatures.client.render.entity.TrollRenderer;
import studio.abos.mc.mysticalcreatures.client.render.state.TrollRenderState;

@OnlyIn(Dist.CLIENT)
public class TrollRenderLayer extends RenderLayer<TrollRenderState, TrollModel> {
    private final TrollModel model;

    // Create the render layer. The renderer parameter is required for passing to super.
    // Other parameters can be added as needed. For example, we need the EntityModelSet for model baking.
    public TrollRenderLayer(TrollRenderer renderer, EntityModelSet entityModelSet) {
        super(renderer);
        // Bake and store our layer definition, using the ModelLayerLocation from back when we registered the layer definition.
        // If applicable, you can also store multiple models this way and use them below.
        this.model = new TrollModel(entityModelSet.bakeLayer(MysticalCreatures.TROLL_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, TrollRenderState renderState, float yRot, float xRot) {
        // Render the layer here. We have stored the entity model in a field, you probably want to use it in some way.
    }
}