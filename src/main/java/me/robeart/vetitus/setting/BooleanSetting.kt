package me.robeart.vetitus.setting

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import me.robeart.vetitus.util.Key

/**
 * @author Robeart 13/09/2020
 */
class BooleanSetting(name: String, defaultVal: Boolean, description: String? = null, parent: Setting<Any>? = null): Setting<Boolean>(name, defaultVal, description, parent) {

    override fun fromJson(jsonElement: JsonElement) {
        if(!jsonElement.isJsonPrimitive) return
        val primitive = jsonElement.asJsonPrimitive
        if(!primitive.isBoolean) return
        setValue(primitive.asBoolean)
    }

    override fun toJson(): JsonElement {
        return JsonPrimitive(value)
    }

}