package com.example.myapplication.activity

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.network.JsonPlaceDTO
import com.example.myapplication.network.RestService
import com.example.myapplication.network.RetroClient
import com.example.myapplication.util.MyLogger
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding.btnTest.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val service = RetroClient.getInstance().create(RestService::class.java)
                val call = service?.callGetPath("1")
                call?.enqueue(object : Callback<JsonPlaceDTO> {
                    override fun onResponse(call: Call<JsonPlaceDTO>, response: Response<JsonPlaceDTO>) {
                        if(response.isSuccessful) {
                            MyLogger.i("Rest Success, body is ${response.body()}")
                            response.body()?.let {
                                runOnUiThread {
                                    binding.tvRest.text = "${it.id}\r\n\r\n${it.title}\r\n\r\n${it.body}"
                                }
                            }
                        }
                        else {
                            runOnUiThread { binding.tvRest.text = "Rest not Success" }
                            MyLogger.e("Rest not success, code is ${response.code()} and request here ${response.raw().request()}")
                        }
                    }

                    override fun onFailure(call: Call<JsonPlaceDTO>, t: Throwable) {
                        runOnUiThread { binding.tvRest.text = "Rest failure" }
                        MyLogger.e("Rest failure >> ${t.message}")
                    }
                })
            }
        }
    }
}