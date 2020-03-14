package com.example.chatapp

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.util.Log
import android.widget.TextView
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class  MyWebSocketClient( val activity: Activity,uri: URI):WebSocketClient(uri){
    private val contentView: TextView by lazy {
        activity.findViewById<TextView>(R.id.messageList)
    }

    override fun onError(ex: Exception?) {
        Log.i(javaClass.simpleName, "エラーが発生しました。", ex)
        Log.i(javaClass.simpleName, "スレッド：「${Thread.currentThread().name}」で実行中")
    }


    override fun onOpen(handshakedata: ServerHandshake?) {
        Log.i(javaClass.simpleName, "WSサーバに接続しました。")
        Log.i(javaClass.simpleName, "スレッド：「${Thread.currentThread().name}」で実行中")

    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Log.i(javaClass.simpleName, "WSサーバから切断しました。reason:${reason}")
        Log.i(javaClass.simpleName, "スレッド：「${Thread.currentThread().name}」で実行中")
    }

    override fun onMessage(message: String?) {
        Log.i(javaClass.simpleName, "メッセージを受け取りました。")
        Log.i(javaClass.simpleName, "スレッド：「${Thread.currentThread().name}」で実行中")
        activity.runOnUiThread {
            contentView.append("$message \n")

            Log.i(javaClass.simpleName, "メッセージをTextViewに追記しました。")
            Log.i(javaClass.simpleName, "スレッド：「${Thread.currentThread().name}」で実行中")
        }
    }
}
