package studio.abos.mc.mysticalcreatures.client.model.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import studio.abos.mc.mysticalcreatures.client.render.state.UnicornRenderState;

@OnlyIn(Dist.CLIENT)
public class UnicornModel extends EntityModel<UnicornRenderState> {

    public UnicornModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        // The mesh initially contains no object other than the root, which is invisible (has a size of 0x0x0).
        PartDefinition root = mesh.getRoot();
        // We add a head part.
        PartDefinition head = root.addOrReplaceChild(
                // The name of the part.
                "head",
                // The CubeListBuilder we want to add.
                CubeListBuilder.create()
                        // The UV coordinates to use within the texture. Texture binding itself is explained below.
                        // In this example, we start at U=10, V=20.
                        .texOffs(10, 20)
                        // Add our cube. May be called multiple times to add multiple cubes.
                        // This is relative to the parent part. For the root part, it is relative to the entity's position.
                        // Be aware that the y axis is flipped, i.e. "up" is subtractive and "down" is additive.
                        .addBox(
                                // The top-left-back corner of the cube, relative to the parent object's position.
                                -5, -5, -5,
                                // The size of the cube.
                                10, 10, 10
                        )
                        // Call texOffs and addBox again to add another cube.
                        .texOffs(30, 40)
                        .addBox(-1, -1, -1, 1, 1, 1),
                // The initial positioning to apply to all elements of the CubeListBuilder. Besides PartPose#offset,
                // PartPose#offsetAndRotation is also available. This can be reused across multiple PartDefinitions.
                // This may not be used by all models. For example, making custom armor layers will use the associated
                // player (or other humanoid) renderer's PartPose instead to have the armor "snap" to the player model.
                PartPose.offset(0, 8, 0)
        );
        // We can now add children to any PartDefinition, thus creating a hierarchy.
//        PartDefinition part1 = root.addOrReplaceChild(...);
//        PartDefinition part2 = head.addOrReplaceChild(...);
//        PartDefinition part3 = part1.addOrReplaceChild(...);
        // At the end, we create a LayerDefinition from the MeshDefinition.
        // The two integers are the expected dimensions of the texture; 64x32 in our example.
        return LayerDefinition.create(mesh, 64, 32);
    }
}
