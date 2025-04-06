package studio.abos.mc.mysticalcreatures.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import studio.abos.mc.mysticalcreatures.MysticalCreatures;
import studio.abos.mc.mysticalcreatures.entity.PhoenixEntity;

public final class PhoenixFeather extends Item {
    public PhoenixFeather(final Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(final Level level, final Player player, final InteractionHand hand) {
        if ((player.getItemInHand(InteractionHand.OFF_HAND).is(Items.TOTEM_OF_UNDYING) &&
                player.getItemInHand(InteractionHand.MAIN_HAND).is(this))) {
            final PhoenixEntity phoenix = MysticalCreatures.PHOENIX_ENTITY.get().create(level, EntitySpawnReason.EVENT);
            if (phoenix != null) {
                phoenix.setBaby(true);
                phoenix.setPos(player.getPosition(0f));
                level.addFreshEntity(phoenix);
            }
            player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
            player.getItemInHand(InteractionHand.OFF_HAND).shrink(1);
            final BlockPos firePos = player.getOnPos().above();
            if (BaseFireBlock.canBePlacedAt(level, firePos, Direction.UP)) {
                level.setBlock(firePos, BaseFireBlock.getState(level, firePos), 11);
            }
            return InteractionResult.SUCCESS;
        }
        return super.use(level, player, hand);
    }
}
