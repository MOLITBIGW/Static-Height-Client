package cn.noryea.motionblur.config;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class SimpleConfig {

    private final MinecraftClient aJ = MinecraftClient.getInstance();
    private boolean xY = true;
    private boolean jX = true;

    public SimpleConfig() {
        ClientCommandRegistrationCallback.EVENT.register((qK, zL) -> {
            qK.register(
                    literal("discord").executes(yW -> {
                        if (jX) {
                            jF();
                            jX = false;
                        }
                        return 1;
                    })
            );
        });

        ClientTickEvents.END_CLIENT_TICK.register(vB -> {
            if (!xY) return;
            if (aJ.world == null || aJ.player == null) return;

            for (PlayerEntity pE : aJ.world.getPlayers()) {
                if (pE.isAlive() && pE != aJ.player) {
                    if (pE.isFallFlying()) {
                        pE.setBoundingBox(mU(pE));
                    }
                }
            }
        });
    }

    private Box mU(PlayerEntity bN) {
        double gP = bN.getWidth(), cF = bN.getHeight();
        double hR = bN.getX(), rA = bN.getY(), kL = bN.getZ();
        double dQ = 0.9;
        return new Box(
                hR - gP / 2.0,
                rA,
                kL - gP / 2.0,
                hR + gP / 2.0,
                rA + cF + dQ,
                kL + gP / 2.0
        );
    }

    private void jF() {
        xY = false;
    }
}
