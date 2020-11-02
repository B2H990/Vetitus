package me.robeart.vetitus.setting

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import me.robeart.vetitus.util.Key

/**
 * @author Robeart 13/09/2020
 */
class KeySetting(name: String, defaultVal: Key, description: String? = null, parent: Setting<Any>? = null): Setting<Key>(name, defaultVal, description, parent) {
    constructor(name: String): this(name, Key.KEY_NONE)
    constructor(name: String, defaultVal: String): this(name, Key.fromName(defaultVal))
    constructor(name: String, defaultVal: Int): this(name, Key.fromCode(defaultVal))

    fun isKeyDown() = this.value.isKeyDown
    fun hasChangedState() = this.value.hasChangedState
    fun hasBeenPressed() = this.value.hasBeenPressed

    override fun fromJson(jsonElement: JsonElement) {
        if(!jsonElement.isJsonPrimitive) return
        val primitive = jsonElement.asJsonPrimitive
        if(!primitive.isString) return
        setValue(Key.fromName(primitive.asString))
    }

    override fun toJson(): JsonElement {
        return JsonPrimitive(value.toString())
    }

}