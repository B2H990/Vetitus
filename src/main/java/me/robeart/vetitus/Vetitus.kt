package me.robeart.vetitus

import me.robeart.vetitus.managers.FileManager
import me.robeart.vetitus.managers.ModuleManager
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

/**
 * @author Robeart 12-09-20
 */
@Mod(modid = Vetitus.MOD_ID, name = Vetitus.MOD_NAME, version = Vetitus.VERSION)
object Vetitus {

    const val MOD_ID = "vetitus"
    const val MOD_NAME = "Vetitus"
    const val VERSION = "0.1"

    @JvmStatic
    val brand: String = String.format("%s %s", MOD_NAME, VERSION)
    
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        ModuleManager.initialize()
        FileManager.initialize()
        println("Raion initialized")
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
    }
}
