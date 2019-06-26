package i.krishnasony.whetherproject.room.entities


import com.google.gson.annotations.SerializedName

data class ForeCastModel(
    @SerializedName("city")
    var city: City?,
    @SerializedName("cnt")
    var cnt: Int?,
    @SerializedName("cod")
    var cod: String?,
    @SerializedName("list")
    var list: List<X?>?,
    @SerializedName("message")
    var message: Double?
) {
    data class City(
        @SerializedName("coord")
        var coord: Coord?,
        @SerializedName("country")
        var country: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("population")
        var population: Int?,
        @SerializedName("timezone")
        var timezone: Int?
    ) {
        data class Coord(
            @SerializedName("lat")
            var lat: Double?,
            @SerializedName("lon")
            var lon: Double?
        )
    }

    data class X(
        @SerializedName("clouds")
        var clouds: Clouds?,
        @SerializedName("dt")
        var dt: Int?,
        @SerializedName("dt_txt")
        var dtTxt: String?,
        @SerializedName("main")
        var main: Main?,
        @SerializedName("rain")
        var rain: Rain?,
        @SerializedName("sys")
        var sys: Sys?,
        @SerializedName("weather")
        var weather: List<Weather?>?,
        @SerializedName("wind")
        var wind: Wind?
    ) {
        data class Weather(
            @SerializedName("description")
            var description: String?,
            @SerializedName("icon")
            var icon: String?,
            @SerializedName("id")
            var id: Int?,
            @SerializedName("main")
            var main: String?
        )

        data class Clouds(
            @SerializedName("all")
            var all: Int?
        )

        data class Sys(
            @SerializedName("pod")
            var pod: String?
        )

        data class Wind(
            @SerializedName("deg")
            var deg: Double?,
            @SerializedName("speed")
            var speed: Double?
        )

        data class Rain(
            @SerializedName("3h")
            var h: Double?
        )

        data class Main(
            @SerializedName("grnd_level")
            var grndLevel: Double?,
            @SerializedName("humidity")
            var humidity: Int?,
            @SerializedName("pressure")
            var pressure: Double?,
            @SerializedName("sea_level")
            var seaLevel: Double?,
            @SerializedName("temp")
            var temp: Double?,
            @SerializedName("temp_kf")
            var tempKf: Double?,
            @SerializedName("temp_max")
            var tempMax: Double?,
            @SerializedName("temp_min")
            var tempMin: Double?
        )
    }
}