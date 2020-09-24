package cccandroidtest.ca.network

import cccandroidtest.ca.model.Response
import cccandroidtest.ca.util.Constants
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object RetrieveHttp {

    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    fun getFromAPI() : Response?{
        val request = Request.Builder()
            .url(String.format(Constants.URL_API))
            .build()

        try{
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, Response::class.java)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}