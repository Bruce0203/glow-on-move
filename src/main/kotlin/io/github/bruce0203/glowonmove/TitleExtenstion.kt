package io.github.bruce0203.glowonmove

import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import net.kyori.adventure.title.TitlePart
import org.bukkit.entity.Player
import java.time.Duration

fun Player.sendTitle(titleInfo: TitleInfo): Unit = titleInfo.run {
    sendTitlePart(TitlePart.TITLE, Component.text(title?: ""))
    sendTitlePart(TitlePart.SUBTITLE, Component.text(subtitle?: ""))
    sendTitlePart(TitlePart.TIMES, Title.Times.times(
        Duration.ofSeconds((fadeIn?: 0)/20L),
        Duration.ofSeconds((stay?: 20)/20L),
        Duration.ofSeconds((fadeOut?: 0)/20L)))
}