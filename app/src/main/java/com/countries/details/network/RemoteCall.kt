package com.countries.details.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.countries.details.model.Country

import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class RemoteCall private constructor() {
    private val mGson: Gson
    private var mUrl: URL? = null

    init {
        mGson = Gson()
        try {
            mUrl = URL(URL)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }

     fun getCountryList(result: Result) {
         val urlConnection = mUrl!!.openConnection() as HttpURLConnection
         val `in`: InputStream = BufferedInputStream(urlConnection.inputStream)
         val typeOfT = TypeToken.getParameterized(MutableList::class.java, Country::class.java).type
         result.onResponse(mGson.fromJson(readStream(`in`), typeOfT))
     }

    @Throws(IOException::class)
    private fun readStream(`is`: InputStream): String {
        val outputStream = ByteArrayOutputStream()
        var readBuffer = `is`.read()
        while (readBuffer != -1) {
            outputStream.write(readBuffer)
            readBuffer = `is`.read()
        }
        return outputStream.toString()
    }

    interface Result {
        fun onResponse(countryList: List<Country>)
    }

    companion object {
        private const val URL = "https://restcountries.com/v3.1/all"
        private var sRemoteCall: RemoteCall? = null

        @get:Synchronized
        val remoteCallInstance: RemoteCall
            get() {
                if (sRemoteCall == null) {
                    sRemoteCall = RemoteCall()
                }
                return sRemoteCall!!
            }
    }
}