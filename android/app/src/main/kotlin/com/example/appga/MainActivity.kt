package com.example.appga

import android.os.Bundle
import com.google.android.gms.ads.identifier.AdvertisingIdClient

import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import kotlinx.coroutines.*

class MainActivity : FlutterActivity() {

  private fun startChannel() {
    MethodChannel(flutterView, "com.example.appga/ad")
      .setMethodCallHandler { call, result ->
        GlobalScope.launch {
          val info = withContext(Dispatchers.Default) {
            AdvertisingIdClient.getAdvertisingIdInfo(applicationContext)
          }
          result.success(info.id)
        }
      }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)

    this.startChannel()
  }
}
