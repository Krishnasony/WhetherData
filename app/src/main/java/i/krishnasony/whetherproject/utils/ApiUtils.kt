package i.krishnasony.whetherproject.utils

import android.annotation.SuppressLint
import java.lang.StringBuilder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


object ApiUtils {
    const val API_KEY = "d6546f41581b8bef4086668438c0788d"
    const val BASE_URL ="http://api.openweathermap.org/data/2.5/weather"

    fun apiRequestbylatlang(lat:String,lng:String):String {
        val stringBuilder = StringBuilder(BASE_URL)
        stringBuilder.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric",lat,lng,API_KEY))
        return stringBuilder.toString()
    }


    fun apiRequestByCity(city:String):String {
        val stringBuilder = StringBuilder(BASE_URL)
        stringBuilder.append(String.format("?q=%s&APPID=%s",city,API_KEY))
        return stringBuilder.toString()
    }


    @SuppressLint("SimpleDateFormat")
    fun unixTimeStampToDateTime(unixTimeStamp: Double): String {
        val dateFormat:DateFormat = SimpleDateFormat("HH:mm") as DateFormat
        val date = Date()
        date.setTime(unixTimeStamp.toLong() * 1000)
        return dateFormat.format(date)
    }

    fun getImage(icon: String): String {
        return String.format("http://openweathermap.org/img/w/%s.png", icon)
    }

    fun getBitmapFromURL(src: String): Bitmap? {
        try {
            val url = URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input = connection.getInputStream()
            val myBitmap = BitmapFactory.decodeStream(input)
            return myBitmap
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun getDateNow(): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm")
        val date = Date()
        return dateFormat.format(date)
    }
}