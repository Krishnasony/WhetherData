package i.krishnasony.whetherproject.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import i.krishnasony.whetherproject.room.dao.ForeCastDao
import i.krishnasony.whetherproject.room.dao.WeatherDao
import i.krishnasony.whetherproject.room.database.AppDataBase.Companion.VERSION
import i.krishnasony.whetherproject.room.entities.Main
import i.krishnasony.whetherproject.room.entities.WeatherEntity

@Database(
    entities = [
    WeatherEntity::class,
    Main::class
    ],
    version = VERSION,
    exportSchema = false
)
//if need then add typeconverter here
abstract class AppDataBase : RoomDatabase() {

    abstract val foreCastDao : ForeCastDao
    abstract val weatherDao  : WeatherDao
    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "weather.db"
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(
            context: Context
        ): AppDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DATABASE_NAME
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}