package i.krishnasony.whetherproject.api

import i.krishnasony.whetherproject.room.entities.ForeCastModel
import i.krishnasony.whetherproject.room.entities.WeatherModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WhetherApi {

    @GET("weather?")
    fun getWheaherData(@Query("q") city:String,@Query("APPID") appId:String): Observable<WeatherModel>

    @GET("forecast?")
    fun getForeCastData(@Query("city") city:String,@Query("APPID") appId:String): Single<ForeCastModel>
}