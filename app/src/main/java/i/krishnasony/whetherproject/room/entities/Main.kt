package i.krishnasony.whetherproject.room.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "main")
@Parcelize
data class Main(
    @PrimaryKey @ColumnInfo(name = "id")@SerializedName("id")
    var id: String,
    @ColumnInfo(name ="grnd_level" ) @SerializedName("grnd_level")
    var grndLevel: Double?,
    @ColumnInfo(name = "humidity")@SerializedName("humidity")
    var humidity: Int?,
    @ColumnInfo(name ="pressure" ) @SerializedName("pressure")
    var pressure: Double?,
    @ColumnInfo(name = "sea_level") @SerializedName("sea_level")
    var seaLevel: Double?,
    @ColumnInfo(name = "temp") @SerializedName("temp")
    var temp: Double?,
    @ColumnInfo(name = "temp_max" ) @SerializedName("temp_max")
    var tempMax: Double?,
    @ColumnInfo(name = "temp_min")@SerializedName("temp_min")
    var tempMin: Double?
) : Parcelable