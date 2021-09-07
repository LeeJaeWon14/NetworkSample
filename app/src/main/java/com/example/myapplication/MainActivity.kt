package com.example.myapplication

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

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //get default network state now for app
        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        //app can use network references to query network information
        val currentNetwork = connectivityManager.activeNetwork

        binding.btnTest.setOnClickListener {
            val caps = connectivityManager.getNetworkCapabilities(currentNetwork)
            if(caps == null) {
                Toast.makeText(this, "network is not connected", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Toast.makeText(this, "network is wifi", Toast.LENGTH_SHORT).show()
            }
            else if(caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Toast.makeText(this, "network is cellular", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "else", Toast.LENGTH_SHORT).show()
            }
        }

        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
//                    super.onAvailable(network)
                MyLogger.e("onAvailable")
                Toast.makeText(this@MainActivity, "network available", Toast.LENGTH_SHORT).show()
            }

            override fun onLost(network: Network) {
                MyLogger.e("onLost")
                Toast.makeText(this@MainActivity, "network lost", Toast.LENGTH_SHORT).show()
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                MyLogger.e("onCapabilitiesChanged")
                if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Toast.makeText(this@MainActivity, "wifi", Toast.LENGTH_SHORT).show()
                }
                else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Toast.makeText(this@MainActivity, "cellular", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
                MyLogger.e("onLinkPropertiesChanged")
                super.onLinkPropertiesChanged(network, linkProperties)
            }
        })



//
//        //네트워크 전송 및 관련 기능 정보를 캡슐화
//        val caps = connectivityManager.getNetworkCapabilities(currentNetwork)
//        //경로, 링크 주소, 인터페이스 이름, 프록시 정보, DNS 서버 정보를 가지고 있음
//        val linkProperties = connectivityManager.getLinkProperties(currentNetwork)
    }
}