package i.krishnasony.whetherproject.api

import i.krishnasony.whetherproject.room.entities.ForeCastModel
import i.krishnasony.whetherproject.room.entities.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WhetherApi {

    @GET("weather?q={city}")
    fun getWheaherData(@Path("city") city:String): Single<WeatherModel>

    @GET("forecast?q={city}")
    fun getForeCastData(@Path("city") city:String): Single<ForeCastModel>
}