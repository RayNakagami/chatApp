package com.example.chatapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private val uri = URI("ws:///pipe")//ipアドレスを入れる

    private var client:MyWebSocketClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var arrayAdapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1)

        this.client = MyWebSocketClient(this,arrayAdapter, uri)

        client!!.connect()

        listView.adapter = arrayAdapter



    }

    override fun onDestroy() {
        super.onDestroy()
        client?.close()
    }

    override fun onResume() {
        super.onResume()

        btnSend.setOnClickListener{
            Log.i(javaClass.simpleName, "{\"type\":\"message\",\"text\":\""+sendMessage.text.toString()+"\",\"date\":\"1584526354741\"}")
            client?.send("{\"type\":\"message\",\"text\":\""+sendMessage.text.toString()+"\",\"date\":\"1584526354741\"}")

        }
    }
}
