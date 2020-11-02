package me.robeart.vetitus.managers

import me.robeart.vetitus.modules.Module
import me.robeart.vetitus.util.Initializable
import java.util.*

/**
 * @author Robeart 14/09/2020
 */
object ModuleManager: Initializable() {

    val modules = TreeSet<Module> {module1, module2 -> module1.name.compareTo(module2.name)}

    override fun initialize() {
        registerModules()
    }

    fun registerModule(module: Module) {
        modules += module
    }

    @JvmStatic
    fun <T : Module> getModule(moduleClazz: Class<T>): T {
        for (module in modules) {
            if (moduleClazz.isAssignableFrom(module.javaClass)) return moduleClazz.cast(module)
        }
        throw RuntimeException("Module $moduleClazz was not initialised!")
    }

    @JvmStatic
    fun getModule(moduleName: String): Module {
        for (module in modules) {
            if (module.name == moduleName) return module
        }
        throw RuntimeException("Module $moduleName was not initialised!")
    }

    @SafeVarargs
    fun registerModules(vararg modules: Module) {
        modules.forEach(this::registerModule)
    }

}