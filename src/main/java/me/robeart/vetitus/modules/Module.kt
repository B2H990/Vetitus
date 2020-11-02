package me.robeart.vetitus.modules

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import cookiedragon.eventsystem.EventDispatcher
import me.robeart.vetitus.setting.BooleanSetting
import me.robeart.vetitus.setting.KeySetting
import me.robeart.vetitus.setting.Setting

/**
 * @author Robeart 14/09/2020
 */
open class Module(val name: String,
             val description: String,
             val category: Category,
             val key: KeySetting = KeySetting("Bind"),
             val vis: BooleanSetting = BooleanSetting("Visible", true),
             val canEnable: Boolean = true) {

    inline fun <reified T> register(noinline lambda: (T) -> Unit) = EventDispatcher.register(T::class.java, ModuleListener(lambda, this))

    val settings: ArrayList<Setting<*>> = ArrayList()

    val enabled by BooleanSetting("Enabled", true)

    init {
        (enabled as BooleanSetting).getActualSetting().addCallback { oldValue: Boolean?, newValue: Boolean ->
            if (newValue) onEnable()
            else onDisable()
        }
        for (field in this::class.java.declaredFields) {
            if (Setting::class.java.isAssignableFrom(field.type)) {
                field.isAccessible = true;
                val setting = field.get(this) as Setting<*>
                settings.add(setting)
            }
        }
    }

    fun onEnable() {}

    fun onDisable() {}

    fun onUpdate() {}

    fun getHudInfo(): String? = null

    fun fromJson(settingMap: Set<Map.Entry<String, JsonElement>>) {
        for(setting in settingMap) {
            for(s in settings) {
                if(s.name == setting.key) s.fromJson(setting.value)
            }
        }
    }

    fun toJson(jsonObject: JsonObject): JsonObject {
        val jsonModule = JsonObject()
        for(setting in settings) {
            jsonModule.add(if(setting.parent == null) setting.name else (setting.parent!!.name + "." + setting.name), setting.toJson())
        }
        jsonObject.add(name, jsonModule)
        jsonObject.entrySet()
        return jsonObject
    }

    class ModuleListener<T>(lambda: (T) -> Unit, val module: Module) : EventDispatcher.Listener<T>(lambda, true) {
        override var active: Boolean
            get() = module.enabled
            set(value) { /* dont do anything */
            }
    }

}
enum class Category(var categoryName: String, var hidden: Boolean) {
    EXPLOIT("Exploit", false),
    MOVEMENT("Movement", false),
    RENDER("Render", false),
    COMBAT("Combat", false),
    PLAYER("Player", false),
    MISC("Misc", false),
    HIDDEN("Hidden", true);
}