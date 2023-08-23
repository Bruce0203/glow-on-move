package io.github.bruce0203.glowonmove

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

data class ExtraPlayer(
    var isGlowing: Boolean = false,
    var lastMovedMS: Long = 0L,
    var isStoppedMessageSent: Boolean = false,
    var isMovedMessageSent: Boolean = false
)

object PlayerExtra : Listener {
    private val map = hashMapOf<Player, ExtraPlayer>()

    operator fun get(player: Player) = map[player]?: ExtraPlayer().also { map[player] = it }

    operator fun set(player: Player, extraPlayer: ExtraPlayer) { map[player] = extraPlayer }

    @Suppress("UNUSED")
    @EventHandler
    fun onQuit(event: PlayerQuitEvent) { map.remove(event.player) }
}