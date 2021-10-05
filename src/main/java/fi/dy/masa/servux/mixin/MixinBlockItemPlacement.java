package fi.dy.masa.servux.mixin;

import fi.dy.masa.servux.util.AccurateBlockPlacement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockItem.class)
public class MixinBlockItemPlacement
{
    //carpet extra mixin moved
    @Redirect(method = "getPlacementState", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/Block;getPlacementState(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/block/BlockState;"
    ))
    private BlockState getAlternatePlacement(Block block, ItemPlacementContext itemPlacementContext)
    {
        BlockState tryAlternative = AccurateBlockPlacement.getPlacementState(block, itemPlacementContext);
        if (tryAlternative != null)
            return tryAlternative;
        return block.getPlacementState(itemPlacementContext);
    }

}