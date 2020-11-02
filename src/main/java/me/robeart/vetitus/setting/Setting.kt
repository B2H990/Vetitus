package me.robeart.vetitus.setting

import com.google.gson.JsonElement
import kotlin.reflect.KProperty

/**
 * @author Robeart 13/09/2020
 */
abstract class Setting<T>(val name: String, defaultVal: T, var description: String? = null, var parent: Setting<Any>? = null) {
    var value = defaultVal
            private set

    fun getActualSetting(): Setting<T> = this;

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newVal: T) {
        setValue(newVal)
    }

    /**
     * called when the value of this value is set using the setValue function
     */
    private var callback: ((T, T) -> Unit)? = null

    fun addCallback(callback: ((T, T) -> Unit)?): Setting<T> =
            this.apply {
                this.callback = callback
            }

    fun setValue(value: T, bypassCallback: Boolean = false): T {
        val oldVal = this.value
        this.value = value
        if (!bypassCallback) callback?.invoke(oldVal, this.value)
        return value
    }

    fun castAndSetValue(value: Any): T {
        return this.setValue(value as T)
    }

    override fun toString() = "$name ${javaClass.name} $value"

    abstract fun toJson(): JsonElement

    abstract fun fromJson(jsonElement: JsonElement)
}