package me.robeart.vetitus.configs

import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import me.robeart.vetitus.managers.ModuleManager
import me.robeart.vetitus.modules.Module
import me.robeart.vetitus.util.JsonUtil
import java.io.*
import java.nio.file.Files

/**
 * @author Robeart 15/09/2020
 */
class ModuleConfig(file: File, val module: Module? = null): ConfigFile(file) {

    override fun loadConfig() {
        val jsonElement = JsonUtil.jsonParser.parse(BufferedReader(FileReader(file)))
        if(jsonElement is JsonNull) return
        for(map in jsonElement.asJsonObject.entrySet()) {
            val module = ModuleManager.getModule(map.key)
            val jsonModule = map.value.asJsonObject.entrySet()
            module.fromJson(jsonModule)
        }
    }

    override fun saveConfig() {
        var json = JsonObject()
        if(module != null) {
            json = module.toJson(json)
        } else {
            for(module in ModuleManager.modules) {
                json = module.toJson(json)
            }
        }
        val writer = PrintWriter(FileWriter(file))
        writer.println(JsonUtil.prettyGson.toJson(json))
    }

}