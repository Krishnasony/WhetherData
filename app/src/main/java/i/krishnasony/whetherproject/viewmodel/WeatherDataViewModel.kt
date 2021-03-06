package i.krishnasony.whetherproject.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import i.krishnasony.whetherproject.api.ApiCallBack
import i.krishnasony.whetherproject.room.entities.WeatherModel
import i.krishnasony.whetherproject.room.repo.WeatherRepo
import timber.log.Timber


class WeatherDataViewModel:ViewModel() {
      var weatherData: MutableLiveData<WeatherModel>  = MutableLiveData()

    fun getWeatherData(repo: WeatherRepo){
        repo.getWeatherData(object :ApiCallBack<WeatherModel>{
            override fun onSuccess(t: WeatherModel) {
                Timber.e(t.toString())
                weatherData.postValue(t)
            }

            override fun onFailure(message: String) {
                Timber.e(message)
            }

        })
    }

}