package io.github.bruce0203.glowonmove

import fr.skytasul.glowingentities.GlowingEntities
import io.github.bruce0203.glowonmove.event.PlayerGlowEvent
import io.github.bruce0203.glowonmove.event.PlayerGlowStoppedEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class GlowListener(private val glowingAPI: GlowingEntities) : Listener {

    @Suppress("UNUSED")
    @EventHandler
    fun onGlow(event: PlayerGlowEvent) {
        Bukkit.getOnlinePlayers().forEach { eachPlayer ->
            glowingAPI.setGlowing(event.player, eachPlayer, ChatColor.GREEN)
        }
    }

    @Suppress("UNUSED")
    @EventHandler
    fun onGlow(event: PlayerGlowStoppedEvent) {
        Bukkit.getOnlinePlayers().forEach { eachPlayer ->
            glowingAPI.unsetGlowing(event.player, eachPlayer)
        }
    }


}