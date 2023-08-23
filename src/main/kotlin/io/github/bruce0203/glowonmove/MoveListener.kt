package io.github.bruce0203.glowonmove

import io.github.bruce0203.glowonmove.event.PlayerGlowEvent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class MoveListener(private val config: Config = DEFAULT_CONFIG) : Listener {

    @Suppress("UNUSED")
    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        if (event.to.isSamePosition(event.player.location)) return
        val player = event.player
        if (player.isOp) return
        val extraPlayer = PlayerExtra[player]
        extraPlayer.lastMovedMS = System.currentTimeMillis()
        if (extraPlayer.isMovedMessageSent.not()) {
            extraPlayer.isMovedMessageSent = true
            player.sendTitle(config.onMoved)
        }
        extraPlayer.isStoppedMessageSent = false
        if (!extraPlayer.isGlowing) {
            extraPlayer.isGlowing = true
            Bukkit.getPluginManager().callEvent(PlayerGlowEvent(player))
        }
    }

    fun Location.isSamePosition(t: Location) = world == t.world && x == t.x && y == t.y && z == t.z


}