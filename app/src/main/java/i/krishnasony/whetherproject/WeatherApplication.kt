package i.krishnasony.whetherproject

import android.app.Application
import i.krishnasony.whetherproject.di.appModule
import i.krishnasony.whetherproject.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication :Application (){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(listOf(appModule, roomModule))
        }
    }
}