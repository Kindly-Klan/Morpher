package draylar.identity;

import dev.architectury.event.events.client.ClientPlayerEvent;
import draylar.identity.ability.AbilityOverlayRenderer;
import draylar.identity.api.ApplicablePacket;
import draylar.identity.api.model.EntityArms;
import draylar.identity.api.model.EntityUpdaters;
import draylar.identity.impl.join.ClientPlayerJoinHandler;
import draylar.identity.network.ClientNetworking;

import java.util.HashSet;
import java.util.Set;

public class IdentityClient {

    private static final Set<ApplicablePacket> SYNC_PACKET_QUEUE = new HashSet<>();

    public void initialize() {
        // Register client-side event handlers
        EntityUpdaters.init();
        AbilityOverlayRenderer.register();
        EntityArms.init();

        // Register event handlers
        ClientNetworking.registerPacketHandlers();
        ClientPlayerEvent.CLIENT_PLAYER_JOIN.register(new ClientPlayerJoinHandler());
    }

    // We do this because the Architectury "player log in" network event runs before MinecraftClient#player exists.
    public static Set<ApplicablePacket> getSyncPacketQueue() {
        return SYNC_PACKET_QUEUE;
    }
}
