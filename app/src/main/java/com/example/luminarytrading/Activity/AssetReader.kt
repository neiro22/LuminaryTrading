package com.example.luminarytrading.Activity

import android.content.Context
import java.io.IOException

object AssetReader {
    fun readTextFile(context: Context, fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            "Ошибка загрузки контента"
        }
    }
}