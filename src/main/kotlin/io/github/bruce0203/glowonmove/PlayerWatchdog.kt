package io.github.bruce0203.glowonmove

import io.github.bruce0203.glowonmove.event.PlayerGlowStoppedEvent
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

fun playerWatchdog(plugin: Plugin, config: Config = DEFAULT_CONFIG) {

    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, {
        Bukkit.getOnlinePlayers().forEach { player ->
            if (player.isOp) return@forEach
            val extraPlayer = PlayerExtra[player]
            val delta = (System.currentTimeMillis() - extraPlayer.lastMovedMS)/1000.0*20
            val offset = 3
            if (delta >= offset) extraPlayer.isMovedMessageSent = false
            if (delta >= config.period && extraPlayer.isGlowing) {
                extraPlayer.isGlowing = false
                Bukkit.getPluginManager().callEvent(PlayerGlowStoppedEvent(player))
                player.sendTitle(config.onGlowCleared)
            } else {
                if (delta >= offset && extraPlayer.isMovedMessageSent.not() && extraPlayer.isStoppedMessageSent.not()) {
                    extraPlayer.isStoppedMessageSent = true
                    player.sendTitle(config.onStopped)
                }
            }
        }
    }, 1L, 1L)
}