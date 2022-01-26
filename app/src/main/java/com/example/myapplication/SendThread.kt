package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.widget.Toast
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ConnectException
import java.net.Socket

class SendThread(private val context: Context, private val msg: String) : Thread() {
    override fun run() {
        try {
            var socket: Socket = Socket("localhost", 8888)
            val outputStream = ObjectOutputStream(socket.getOutputStream())
            outputStream.writeObject(msg)
            outputStream.flush()
        } catch(e: ConnectException) {
            (context as Activity).runOnUiThread { Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show() }
        }
    }
}