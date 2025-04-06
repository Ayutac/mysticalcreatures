package studio.abos.mc.mysticalcreatures.client.model.entity;

import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import studio.abos.mc.mysticalcreatures.client.render.state.UnicornRenderState;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class UnicornModel extends EntityModel<UnicornRenderState> {
    private static final float DEG_125 = 2.1816616F;
    private static final float DEG_60 = ((float)Math.PI / 3F);
    private static final float DEG_45 = ((float)Math.PI / 4F);
    private static final float DEG_30 = ((float)Math.PI / 6F);
    private static final float DEG_15 = 0.2617994F;
    protected static final String HEAD_PARTS = "head_parts";
    protected static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(true, 16.2F, 1.36F, 2.7272F, 2.0F, 20.0F, Set.of("head_parts"));
    protected final ModelPart body;
    protected final ModelPart headParts;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart tail;

    public UnicornModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.headParts = root.getChild("head_parts");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer(boolean baby) {
        if (baby) {
            return LayerDefinition.create(createBabyMesh(CubeDeformation.NONE), 64, 64).apply(BABY_TRANSFORMER);
        }
        return LayerDefinition.create(createBodyMesh(CubeDeformation.NONE), 64, 64);
    }

    protected static MeshDefinition createBodyMesh(final CubeDeformation cubeDeformation) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 11.0F, 5.0F));
        PartDefinition partdefinition2 = partdefinition.addOrReplaceChild("head_parts", CubeListBuilder.create().texOffs(0, 35).addBox(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F), PartPose.offsetAndRotation(0.0F, 4.0F, -12.0F, ((float)Math.PI / 6F), 0.0F, 0.0F));
        PartDefinition partdefinition3 = partdefinition2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, cubeDeformation), PartPose.ZERO);
        partdefinition2.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(56, 36).addBox(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, cubeDeformation), PartPose.ZERO);
        partdefinition2.addOrReplaceChild("upper_mouth", CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, -11.0F, -7.0F, 4.0F, 5.0F, 5.0F, cubeDeformation), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-3.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, cubeDeformation), PartPose.offset(4.0F, 14.0F, 7.0F));
        partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-1.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, cubeDeformation), PartPose.offset(-4.0F, 14.0F, 7.0F));
        partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, cubeDeformation), PartPose.offset(4.0F, 14.0F, -10.0F));
        partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, cubeDeformation), PartPose.offset(-4.0F, 14.0F, -10.0F));
        partdefinition1.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(42, 36).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 14.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -5.0F, 2.0F, ((float)Math.PI / 6F), 0.0F, 0.0F));
        partdefinition3.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(19, 16).addBox(0.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.ZERO);
        partdefinition3.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(19, 16).addBox(-2.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.ZERO);
        return meshdefinition;
    }

    protected static MeshDefinition createBabyMesh(final CubeDeformation cubeDeformation) {
        return BABY_TRANSFORMER.apply(createFullScaleBabyMesh(cubeDeformation));
    }

    protected static MeshDefinition createFullScaleBabyMesh(final CubeDeformation cubeDeformation) {
        MeshDefinition meshdefinition = createBodyMesh(cubeDeformation);
        PartDefinition partdefinition = meshdefinition.getRoot();
        CubeDeformation cubedeformation = cubeDeformation.extend(0.0F, 5.5F, 0.0F);
        partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-3.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, cubedeformation), PartPose.offset(4.0F, 14.0F, 7.0F));
        partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-1.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, cubedeformation), PartPose.offset(-4.0F, 14.0F, 7.0F));
        partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, cubedeformation), PartPose.offset(4.0F, 14.0F, -10.0F));
        partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, cubedeformation), PartPose.offset(-4.0F, 14.0F, -10.0F));
        return meshdefinition;
    }

    public void setupAnim(final @NotNull UnicornRenderState state) {
        super.setupAnim(state);
        float f = Mth.clamp(state.yRot, -20.0F, 20.0F);
        float f1 = state.xRot * ((float)Math.PI / 180F);
        float f2 = state.walkAnimationSpeed;
        float f3 = state.walkAnimationPos;
        if (f2 > 0.2F) {
            f1 += Mth.cos(f3 * 0.8F) * 0.15F * f2;
        }
        float f4 = state.getEatAnimation();

        float f5 = 1.0F - (float)Mth.abs(10 - 2 * state.getAttackAnimationRemainingTicks()) / 10.0F;
//        this.head.xRot = Mth.lerp(f5, 0.87266463F, -0.34906584F);
//        if (state.isBaby) {
//            ModelPart var10000 = this.head;
//            var10000.y += f5 * 2.5F;
//        }

        boolean flag = state.isAnimateTail();
        this.headParts.xRot = ((float)Math.PI / 6F) + f1;
        this.headParts.yRot = f * ((float)Math.PI / 180F);
        float f8 = state.isInWater ? 0.2F : 1.0F;
        float f9 = Mth.cos(f8 * f3 * 0.6662F + (float)Math.PI);
        float f10 = f9 * 0.8F * f2;
        float f11 = (1.0F - f4) * (((float)Math.PI / 6F) + f1);
        this.headParts.xRot = Mth.lerp(f5, 0.87266463F, -0.34906584F) + f4 * (2.1816616F + Mth.sin(state.ageInTicks) * 0.05F) + f11;
        this.headParts.yRot = (1.0F - f4) * this.headParts.yRot;
        float f12 = state.ageScale;
        this.headParts.y += Mth.lerp(f4, Mth.lerp(0, 0.0F, -8.0F * f12), 7.0F * f12);
        this.headParts.z = Mth.lerp(0, this.headParts.z, -4.0F * f12);
        this.body.xRot = 0;
        float f14 = Mth.cos(state.ageInTicks * 0.6F + (float)Math.PI);
        ModelPart var10000;
        this.rightFrontLeg.y = this.leftFrontLeg.y;
        this.rightFrontLeg.z = this.leftFrontLeg.z;
        float f15 = f10;
        float f16 = f10;
        this.leftHindLeg.xRot = f9 * 0.5F * f2;
        this.rightHindLeg.xRot = f9 * 0.5F * f2;
        this.leftFrontLeg.xRot = f15;
        this.rightFrontLeg.xRot = f16;
        this.tail.xRot = ((float)Math.PI / 6F) + f2 * 0.75F;
        var10000 = this.tail;
        var10000.y += f2 * f12;
        var10000.z += f2 * 2.0F * f12;
        if (flag) {
            this.tail.yRot = Mth.cos(state.ageInTicks * 0.7F);
        } else {
            this.tail.yRot = 0.0F;
        }

    }
}
