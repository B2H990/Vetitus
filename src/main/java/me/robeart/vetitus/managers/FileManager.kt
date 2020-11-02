package me.robeart.vetitus.managers

import me.robeart.vetitus.Vetitus
import me.robeart.vetitus.configs.ModuleConfig
import me.robeart.vetitus.util.Globals
import me.robeart.vetitus.util.Initializable
import java.io.File
import java.lang.Exception

/**
 * @author Robeart 15/09/2020
 */
object FileManager: Initializable() {

    val mc = Globals.mc
    val dir = File(mc.gameDir, Vetitus.MOD_NAME)

    val moduleConfig = ModuleConfig(File(dir, "modules.json"))

    override fun initialize() {
        setupFolder()
        try {
            println("Loading Config")
            moduleConfig.loadConfig()
        } catch (e: Exception) {
            println("Saving Config")
            moduleConfig.saveConfig()
        }
    }

    private fun setupFolder() {
        if(!dir.exists()) dir.mkdir()
    }

}