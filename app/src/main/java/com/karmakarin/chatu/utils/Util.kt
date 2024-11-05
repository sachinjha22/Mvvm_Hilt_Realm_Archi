package com.karmakarin.chatu.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream

class Util {

    companion object {
        fun loadJSONFromAsset(context: Context, fileName: String): String {
            val json = try {
                val `is`: InputStream = context.assets.open(fileName)
                val size: Int = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, Charsets.UTF_8)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return ""
            }
            return json
        }
    }
}