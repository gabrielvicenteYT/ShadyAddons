package cheaters.get.banned.features;

import cheaters.get.banned.Shady;
import cheaters.get.banned.configuration.Config;
import cheaters.get.banned.utils.KeybindUtils;
import cheaters.get.banned.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class GhostBlocks {

    public GhostBlocks() {
        KeybindUtils.register("Create Ghost Block", Keyboard.KEY_G);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(Config.ghostBlockKeybind && KeybindUtils.get("Create Ghost Block").isKeyDown()) {
            BlockPos lookingAtPos = Shady.mc.thePlayer.rayTrace(Shady.mc.playerController.getBlockReachDistance(), 1).getBlockPos();
            if(lookingAtPos != null) {
                Block lookingAtblock = Shady.mc.theWorld.getBlockState(lookingAtPos).getBlock();
                if(!Utils.canInteract(lookingAtblock)) {
                    Shady.mc.theWorld.setBlockToAir(lookingAtPos);
                }
            }
        }
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        if(Utils.inSkyBlock && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && Config.stonkGhostBlock && !Utils.canInteract(Shady.mc.theWorld.getBlockState(event.pos).getBlock())) {
            String itemId = Utils.getSkyBlockID(Shady.mc.thePlayer.getHeldItem());
            if(itemId.equals("STONK_PICKAXE") || itemId.equals("GOLD_PICKAXE")) {
                Shady.mc.theWorld.setBlockToAir(event.pos);
                event.setCanceled(true);
            }
        }
    }

}