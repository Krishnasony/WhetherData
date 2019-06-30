package i.krishnasony.whetherproject.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import i.krishnasony.whetherproject.api.ApiCallBack
import i.krishnasony.whetherproject.room.entities.ForeCastModel
import i.krishnasony.whetherproject.room.repo.WeatherRepo
import timber.log.Timber

class ForeCastViewModel :ViewModel() {
    var forecastData:MutableLiveData<ForeCastModel> = MutableLiveData()
    fun getForeCastData(repo: WeatherRepo){
        repo.getForeCastData(object :ApiCallBack<ForeCastModel>{
            override fun onSuccess(t: ForeCastModel) {
                Timber.e(t.toString())
                forecastData.postValue(t)
            }

            override fun onFailure(message: String) {
                Timber.e(message)
            }

        })

    }
}