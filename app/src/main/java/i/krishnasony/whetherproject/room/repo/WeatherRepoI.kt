package i.krishnasony.whetherproject.room.repo

import i.krishnasony.whetherproject.api.ApiCallBack
import i.krishnasony.whetherproject.room.entities.ForeCastModel
import i.krishnasony.whetherproject.room.entities.WeatherModel

interface WeatherRepoI {

    fun getWeatherData(apiCallBack: ApiCallBack<WeatherModel>)
    fun getForeCastData(apiCallBack: ApiCallBack<ForeCastModel>)
}