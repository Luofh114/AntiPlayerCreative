package com.example.antiplayercreative;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server-only safeguard against players using creative mode.
 *
 * <p>The end-of-tick check deliberately runs every tick. This catches commands,
 * plugins, and vanilla game-mode changes regardless of which code initiated them,
 * while the join callback prevents a creative mode from persisting after login.</p>
 */
public final class AntiPlayerCreative implements DedicatedServerModInitializer {
    public static final String MOD_ID = "anti_player_creative";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeServer() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
                forceSurvival(handler.getPlayer()));

        ServerTickEvents.END_SERVER_TICK.register(server ->
                server.getPlayerManager().getPlayerList().forEach(AntiPlayerCreative::forceSurvival));

        LOGGER.info("Anti Player Creative enabled; all players will be kept in survival mode.");
    }

    private static void forceSurvival(ServerPlayerEntity player) {
        if (player.interactionManager.getGameMode() == GameMode.CREATIVE) {
            player.changeGameMode(GameMode.SURVIVAL);
            LOGGER.info("Forced {} into survival mode.", player.getName().getString());
        }
    }
}
