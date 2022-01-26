package com.example.myapplication

import android.app.Activity
import android.widget.TextView
import android.widget.Toast
import java.io.ObjectInputStream
import java.net.ConnectException
import java.net.Socket

class ReceiveSocket(private val view: TextView) : Thread() {
    override fun run() {
        try {
            while(true) {
                var socket: Socket = Socket("localhost", 8888)
                val inputStream = ObjectInputStream(socket.getInputStream())
                val input = inputStream.readObject().toString()

                (view.context as Activity).runOnUiThread {
                    view.run {
                        text = text.toString().plus("$input\r\n")
                    }
                }
                yield()
            }
        } catch (e: ConnectException) {
            (view.context as Activity).runOnUiThread { Toast.makeText(view.context, "서버와 연결이 실패했습니다.", Toast.LENGTH_SHORT).show() }
        }
    }
}