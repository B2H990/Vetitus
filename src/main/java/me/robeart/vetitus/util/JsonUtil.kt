package me.robeart.vetitus.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser

/**
 * @author Robeart 15/09/2020
 */
object JsonUtil {
    val gson = Gson()
    val prettyGson = GsonBuilder().setPrettyPrinting().create()
    val jsonParser = JsonParser()
}