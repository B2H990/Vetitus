package me.robeart.vetitus.configs

import java.io.File
import java.io.IOException


/**
 * @author Robeart 15/09/2020
 */
abstract class ConfigFile(val file: File) {
    @Throws(IOException::class)
    abstract fun loadConfig()

    @Throws(IOException::class)
    abstract fun saveConfig()

    fun hasConfig(): Boolean = file.exists()
}