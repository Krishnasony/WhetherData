package i.krishnasony.whetherproject.room.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "weather")

@Parcelize
data class WeatherEntity(
   @ColumnInfo(name = "desc") @SerializedName("description")
    var description: String?,
   @ColumnInfo(name = "icon") @SerializedName("icon")
    var icon: String?,
   @PrimaryKey @ColumnInfo(name = "id")@SerializedName("id")
    var id: Int?,
   @ColumnInfo(name = "main")@SerializedName("main")
    var main: String?
):Parcelable
