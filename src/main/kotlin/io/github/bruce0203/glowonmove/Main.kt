package io.github.bruce0203.glowonmove

import com.charleskorn.kaml.Yaml.Companion
import fr.skytasul.glowingentities.GlowingEntities
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

lateinit var DEFAULT_CONFIG: Config

@Suppress("UNUSED")
class Main : JavaPlugin() {
    override fun onEnable() {
        val configFile = File(dataFolder, "config.yml")
        if (configFile.exists()) {
            DEFAULT_CONFIG = com.charleskorn.kaml.Yaml.default.decodeFromString(configFile.readText())
        } else {
            val value = Config(
                20L,
                TitleInfo(subtitle = "움직임!"),
                TitleInfo(subtitle = "멈춤!"),
                TitleInfo(title = "발광 꺼짐!")
            )
            val body = com.charleskorn.kaml.Yaml.default.encodeToString(value)
            configFile.parentFile.mkdirs()
            configFile.createNewFile()
            configFile.writeText(body)
            DEFAULT_CONFIG = value
        }
        Bukkit.getPluginManager().registerEvents(MoveListener(), this)
        Bukkit.getPluginManager().registerEvents(GlowListener(GlowingEntities(this)), this)
        playerWatchdog(this)
    }

}