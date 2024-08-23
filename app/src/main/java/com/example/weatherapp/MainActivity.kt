package com.example.weatherapp

import WeatherResponse
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.databinding.ActivityMainBinding

import com.example.weatherapp.network.Constants.apiKey
import com.example.weatherapp.network.Constants.imageExtension
import com.example.weatherapp.network.Constants.imageUrl
import com.example.weatherapp.network.RetrofitClient
import com.example.weatherapp.util.extensions.showToast
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
   lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val call = RetrofitClient.getServices().fetchWeather("Cairo", appKey = apiKey)
        call.enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(p0: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                response.body()?.let {
                    viewResponse(it)
                }

            }

            override fun onFailure(p0: Call<WeatherResponse>, error: Throwable) {
               error.message?.let {
                   showToast(it)
               }
            }

        })
    }

    private fun viewResponse(response: WeatherResponse) {
        binding.weathertv.text=response.weather[0].description

        Picasso.get().load("$imageUrl${response.weather[0].icon}$imageExtension").into(binding.iv)

    }
}