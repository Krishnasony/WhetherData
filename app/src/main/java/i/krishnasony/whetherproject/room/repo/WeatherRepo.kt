package i.krishnasony.whetherproject.room.repo

import i.krishnasony.whetherproject.api.ApiCallBack
import i.krishnasony.whetherproject.api.WhetherApi
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

//    private lateinit var weatherModel: WeatherModel
//
//     fun getWeatherDataFromApi(weatherApi: WhetherApi,cityName:String?) :WeatherModel{
//        weatherApi.getWheaherData(cityName!!,appId = ApiUtils.API_KEY).enqueue(object :
//            Callback<WeatherModel> {
//            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
//                Timber.e(t)
//            }
//
//            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
//                if (response.isSuccessful){
//                    response.body()?.let {
//                        data ->
//                        weatherModel = data
//                    }
//                }else{
//                    Timber.e(response.errorBody().toString())
//                }
//
//            }
//        })
//        return weatherModel
//    }

}