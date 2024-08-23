
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("weather")

    fun fetchWeather(@Query("q")city:String , @Query("appid") appKey : String): Call<WeatherResponse>
}