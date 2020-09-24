package cccandroidtest.ca.util

import android.content.Context
import android.view.View
import android.view.animation.AlphaAnimation
import java.io.IOException

class Utils {
    companion object {
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun getAnimationConfig(view: View, duration: Long) {
            val fadeIn = AlphaAnimation(0.0f, 1.0f)
            fadeIn.duration = duration
            view.startAnimation(fadeIn)
        }
    }
}