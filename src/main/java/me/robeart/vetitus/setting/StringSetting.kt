package me.robeart.vetitus.setting

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive

/**
 * @author Robeart 13/09/2020
 */
class StringSetting(name: String, defaultVal: String): Setting<String>(name, defaultVal) {

    override fun fromJson(jsonElement: JsonElement) {
        if(!jsonElement.isJsonPrimitive) return
        val primitive = jsonElement.asJsonPrimitive
        if(!primitive.isString) return
        setValue(primitive.asString)
    }

    override fun toJson(): JsonElement {
        return JsonPrimitive(value)
    }

}