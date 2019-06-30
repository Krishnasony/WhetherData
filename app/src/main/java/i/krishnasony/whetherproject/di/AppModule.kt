package i.krishnasony.whetherproject.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import i.krishnasony.whetherproject.WeatherApplication
import i.krishnasony.whetherproject.api.WhetherApi
import i.krishnasony.whetherproject.room.repo.WeatherRepo
import i.krishnasony.whetherproject.utils.ApiUtils
import i.krishnasony.whetherproject.viewmodel.ForeCastViewModel
import i.krishnasony.whetherproject.viewmodel.WeatherDataViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module{

    viewModel { WeatherDataViewModel() }
    viewModel { ForeCastViewModel() }
    single {
        androidContext() as WeatherApplication
    }

    single {
        Retrofit.Builder()
            .baseUrl(ApiUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single {
        get<Retrofit>().create<WhetherApi>(WhetherApi::class.java)
    }

    single {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
        builder.networkInterceptors().add(httpLoggingInterceptor)
        builder.readTimeout(300, TimeUnit.SECONDS)
        builder.connectTimeout(300, TimeUnit.SECONDS)
        builder.build()
    }
}