package me.robeart.vetitus.setting

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive

/**
 * @author Robeart 13/09/2020
 */
class NumberSetting<T: Number>(
        name: String,
        defaultVal: T,
        val min: T,
        val max: T,
        val slowUpdate: Boolean
): Setting<T>(name, defaultVal) {
    constructor(
            name: String,
            defaultVal: T,
            min: T,
            max: T
    ): this(name, defaultVal, min, max, false)

    override fun toJson(): JsonElement {
        return JsonPrimitive(value)
    }

    override fun fromJson(jsonElement: JsonElement) {
        if(!jsonElement.isJsonPrimitive) return
        val primitive = jsonElement.asJsonPrimitive
        if(!primitive.isNumber) return
        setValue(primitive.asNumber as T)
    }


}