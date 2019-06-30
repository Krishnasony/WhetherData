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
    const val BASE_URL ="http://api.openweathermap.org/data/2.5/"


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
    fun getDayFromDate(date: String):String{
        val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dt1 = format1.parse(date)
        val format2 = SimpleDateFormat("EEEE")
        var finalDay = format2.format(dt1)
        when{
            finalDay =="Sunday"-> finalDay = "Sun"
            finalDay =="Monday"-> finalDay = "Mon"
            finalDay =="Tuesday"-> finalDay = "Tue"
            finalDay =="Wednesday"-> finalDay = "Wed"
            finalDay =="Thursday"-> finalDay = "Thu"
            finalDay =="Friday"-> finalDay = "Fri"
            finalDay =="Saturday"-> finalDay = "Sat"

        }
        return finalDay
    }

    @SuppressLint("SimpleDateFormat")
    fun getDateNow(): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm")
        val date = Date()
        return dateFormat.format(date)
    }
}