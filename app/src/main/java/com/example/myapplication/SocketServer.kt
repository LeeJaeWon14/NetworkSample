package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.example.myapplication.util.MyLogger
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket

class ServerService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        MyLogger.e("Service Start")
        Thread {
            try {
                val server = ServerSocket(8888)
                MyLogger.e("...")
                while(true) {
                    val socket = server.accept()
                    MyLogger.e("Server start")

                    val inputStream = ObjectInputStream(socket.getInputStream())
                    val input = inputStream.readObject()
                    MyLogger.e("Input : $input from ${socket.inetAddress.hostAddress}")

                    val outputStream = ObjectOutputStream(socket.getOutputStream())
                    outputStream.writeObject("$input from server")
                    outputStream.flush()

                    socket.close()
                }
            } catch(e: Exception) { e.printStackTrace() }
        }.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate() {
        super.onCreate()
    }
}