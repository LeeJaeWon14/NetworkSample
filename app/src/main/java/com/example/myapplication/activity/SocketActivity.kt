package com.example.myapplication.activity

import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.myapplication.ReceiveSocket
import com.example.myapplication.SendThread
import com.example.myapplication.databinding.ActivitySocketBinding
import kotlinx.coroutines.delay
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ConnectException
import java.net.InetSocketAddress
import java.net.Socket

class SocketActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySocketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receiveThread = ReceiveSocket(binding.tvSocketMessage)

        binding.apply {
            btnSendToSocket.setOnClickListener {
                val sendThread = SendThread(this@SocketActivity, edtSocketInput.text.toString())
            }
        }


    }
}