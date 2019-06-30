package i.krishnasony.whetherproject.room.repo

import i.krishnasony.whetherproject.api.ApiCallBack
import i.krishnasony.whetherproject.api.WhetherApi
import i.krishnasony.whetherproject.room.entities.ForeCastModel
import i.krishnasony.whetherproject.room.entities.WeatherModel
import i.krishnasony.whetherproject.utils.ApiUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WeatherRepo(private var weatherApi: WhetherApi,private var cityName: String?): WeatherRepoI {



    override fun getWeatherData(apiCallBack: ApiCallBack<WeatherModel>){
        weatherApi.getWheaherData(city = cityName!!,appId = ApiUtils.API_KEY).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object :Observer<WeatherModel>{
                override fun onComplete() {
                    Timber.e("Data Response completed SuccessFully")
                }

                override fun onSubscribe(d: Disposable) {
                    //dispose data
                    Timber.e("Dispose method")

                }

                override fun onNext(t: WeatherModel) {
                    apiCallBack.onSuccess(t)
                    Timber.e(t.toString())
                }

                override fun onError(e: Throwable) {
                    apiCallBack.onFailure(e.localizedMessage)
                    Timber.e(e.localizedMessage)
                }

            })
    }

    override fun getForeCastData(apiCallBack: ApiCallBack<ForeCastModel>) {
        weatherApi.getForeCastData(city = cityName!!,appId = ApiUtils.API_KEY).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<ForeCastModel> {
            override fun onComplete() {
                Timber.e("Data Response completed SuccessFully")

            }

            override fun onSubscribe(d: Disposable) {
                Timber.e("Dispose method")
            }

            override fun onNext(t: ForeCastModel) {
                apiCallBack.onSuccess(t)
                Timber.e(t.toString())
            }

            override fun onError(e: Throwable) {
                apiCallBack.onFailure(e.localizedMessage)
                Timber.e(e.localizedMessage)
            }
        })
    }



}