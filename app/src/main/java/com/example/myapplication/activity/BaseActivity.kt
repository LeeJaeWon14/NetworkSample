package com.example.myapplication.activity

import android.content.Intent
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ServerService
import com.example.myapplication.util.MyLogger

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //get default network state now for app
        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        //app can use network references to query network information
        val currentNetwork = connectivityManager.activeNetwork

//        binding.btnTest.setOnClickListener {
//            val caps = connectivityManager.getNetworkCapabilities(currentNetwork)
//            if(caps == null) {
//                Toast.makeText(this, "network is not connected", android.widget.Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if(caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                Toast.makeText(this, "network is wifi", Toast.LENGTH_SHORT).show()
//            }
//            else if(caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                Toast.makeText(this, "network is cellular", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                Toast.makeText(this, "else", Toast.LENGTH_SHORT).show()
//            }
//        }

        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
//                    super.onAvailable(network)
                MyLogger.e("onAvailable")
                Toast.makeText(this@BaseActivity, "network available", Toast.LENGTH_SHORT).show()
            }

            override fun onLost(network: Network) {
                MyLogger.e("onLost")
                Toast.makeText(this@BaseActivity, "network lost", Toast.LENGTH_SHORT).show()
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                MyLogger.e("onCapabilitiesChanged")
                if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Toast.makeText(this@BaseActivity, "wifi", Toast.LENGTH_SHORT).show()
                }
                else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Toast.makeText(this@BaseActivity, "cellular", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
                MyLogger.e("onLinkPropertiesChanged")
                super.onLinkPropertiesChanged(network, linkProperties)
            }
        })



//
//        //???????????? ?????? ??? ?????? ?????? ????????? ?????????
//        val caps = connectivityManager.getNetworkCapabilities(currentNetwork)
//        //??????, ?????? ??????, ??????????????? ??????, ????????? ??????, DNS ?????? ????????? ????????? ??????
//        val linkProperties = connectivityManager.getLinkProperties(currentNetwork)
    }
}