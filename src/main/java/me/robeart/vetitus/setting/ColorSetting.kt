package me.robeart.vetitus.setting

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.awt.Color

/**
 * @author Robeart 13/09/2020
 */
class ColorSetting(name: String, defaultVal: Color, description: String? = null, parent: Setting<Any>? = null): Setting<Color>(name, defaultVal, description, parent) {

    fun setValue(red: Int, green: Int, blue: Int): Color {
        return setValue(Color(red, green, blue))
    }

    fun setValue(red: Int, green: Int, blue: Int, alpha: Int): Color {
        return setValue(Color(red, green, blue, alpha))
    }

    fun setValue(rgb: Int): Color {
        return setValue(Color(rgb))
    }

    override fun fromJson(jsonElement: JsonElement) {
        if(!jsonElement.isJsonPrimitive) return
        val primitive = jsonElement.asJsonPrimitive
        if(!primitive.isNumber) return
        setValue(primitive.asInt)
    }

    override fun toJson(): JsonElement {
        return JsonPrimitive(value.rgb)
    }

}