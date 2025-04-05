package studio.abos.mc.mysticalcreatures.client.model.entity;

import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import studio.abos.mc.mysticalcreatures.client.render.state.PhoenixRenderState;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class PhoenixModel extends EntityModel<PhoenixRenderState> {
    public static final String RED_THING = "red_thing";
    public static final float Y_OFFSET = 16.0f;
    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(false, 5.0f, 2.0f, 2.0f, 1.99f, 24.0f, Set.of("head", "beak", "red_thing"));
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public PhoenixModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
        this.rightWing = root.getChild("right_wing");
        this.leftWing = root.getChild("left_wing");
    }

    public static LayerDefinition createBodyLayer(boolean baby) {
        MeshDefinition meshdefinition = createBasePhoenixModel();
        if (baby) {
            return LayerDefinition.create(meshdefinition, 64, 32).apply(BABY_TRANSFORMER);
        }
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    protected static MeshDefinition createBasePhoenixModel() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild(
                "head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, -6.0f, -2.0f, 4.0f, 6.0f, 3.0f), PartPose.offset(0.0f, 15.0f, -4.0f)
        );
        partdefinition1.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(14, 0).addBox(-2.0f, -4.0f, -4.0f, 4.0f, 2.0f, 2.0f), PartPose.ZERO);
        partdefinition1.addOrReplaceChild("red_thing", CubeListBuilder.create().texOffs(14, 4).addBox(-1.0f, -2.0f, -3.0f, 2.0f, 2.0f, 2.0f), PartPose.ZERO);
        partdefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 9).addBox(-3.0f, -4.0f, -3.0f, 6.0f, 8.0f, 6.0f),
                PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, (float) (Math.PI / 2), 0.0f, 0.0f)
        );
        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(26, 0).addBox(-1.0f, 0.0f, -3.0f, 3.0f, 5.0f, 3.0f);
        partdefinition.addOrReplaceChild("right_leg", cubelistbuilder, PartPose.offset(-2.0f, 19.0f, 1.0f));
        partdefinition.addOrReplaceChild("left_leg", cubelistbuilder, PartPose.offset(1.0f, 19.0f, 1.0f));
        partdefinition.addOrReplaceChild(
                "right_wing", CubeListBuilder.create().texOffs(24, 13).addBox(0.0f, 0.0f, -3.0f, 1.0f, 4.0f, 6.0f), PartPose.offset(-4.0f, 13.0f, 0.0f)
        );
        partdefinition.addOrReplaceChild(
                "left_wing", CubeListBuilder.create().texOffs(24, 13).addBox(-1.0f, 0.0f, -3.0f, 1.0f, 4.0f, 6.0f), PartPose.offset(4.0f, 13.0f, 0.0f)
        );
        return meshdefinition;
    }

    public void setupAnim(PhoenixRenderState renderState) {
        super.setupAnim(renderState);
        float f = (Mth.sin(renderState.getFlap()) + 1f) * renderState.getFlapSpeed();
        this.head.xRot = renderState.xRot * (float) (Math.PI / 180.0);
        this.head.yRot = renderState.yRot * (float) (Math.PI / 180.0);
        float f1 = renderState.walkAnimationSpeed;
        float f2 = renderState.walkAnimationPos;
        this.rightLeg.xRot = Mth.cos(f2 * 0.6662f) * 1.4f * f1;
        this.leftLeg.xRot = Mth.cos(f2 * 0.6662f + (float) Math.PI) * 1.4f * f1;
        this.rightWing.zRot = f;
        this.leftWing.zRot = -f;
    }
}
