package me.robeart.vetitus.setting

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import me.robeart.vetitus.util.Key

/**
 * @author Robeart 13/09/2020
 */
class ListSetting(name: String, vararg options: String): Setting<String>(name, options[0]) {
    val options = listOf(*options)

    override fun fromJson(jsonElement: JsonElement) {
        if(!jsonElement.isJsonPrimitive) return
        val primitive = jsonElement.asJsonPrimitive
        if(!primitive.isString) return
        val configVal = primitive.asString
        if(options.contains(configVal)) setValue(configVal)
        else setValue(options[0])
    }

    override fun toJson(): JsonElement {
        return JsonPrimitive(value)
    }

}