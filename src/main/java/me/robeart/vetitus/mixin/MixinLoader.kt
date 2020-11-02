package me.robeart.vetitus.mixin

import net.minecraft.launchwrapper.LogWrapper
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import org.apache.logging.log4j.LogManager
import org.spongepowered.asm.launch.MixinBootstrap
import org.spongepowered.asm.mixin.Mixins

/**
 * @author Robeart 12-09-20
 */
class MixinLoader: IFMLLoadingPlugin {
    init {
        LogWrapper.retarget(LogManager.getLogger("Vetitus"))
        try {
            MixinBootstrap.init()
            Mixins.addConfiguration("mixins.vetitus.json")
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    override fun getASMTransformerClass(): Array<String?>? = arrayOfNulls(0)
    override fun getModContainerClass(): String? = null
    override fun getSetupClass(): String? = null
    override fun injectData(data: Map<String?, Any?>?) = Unit
    override fun getAccessTransformerClass(): String? = null
}
