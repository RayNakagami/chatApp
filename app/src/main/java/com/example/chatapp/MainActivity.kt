package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : AppCompatActivity() {

    private val uri = URI("ws://192.168.0.3:8080/pipe")
    private val client = MyWebSocketClient(this, uri)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        client.connect()
    }

    override fun onDestroy() {
        super.onDestroy()
        client.close()
    }

    override fun onResume() {
        super.onResume()
        btnSend.setOnClickListener{
            client.send(sendMessage.text.toString())
        }
    }
}
