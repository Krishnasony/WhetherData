package i.krishnasony.whetherproject.di

import i.krishnasony.whetherproject.WeatherApplication
import i.krishnasony.whetherproject.api.WhetherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module{

    single {
        androidContext() as WeatherApplication
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://baheekhata.com/admin17/")
            .addConverterFactory(GsonConverterFactory.create())
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