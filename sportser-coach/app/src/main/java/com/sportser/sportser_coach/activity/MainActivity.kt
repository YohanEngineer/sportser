package com.sportser.sportser_coach.activity

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.textfield.TextInputEditText
import com.sportser.sportser_coach.R
import com.sportser.sportser_coach.service.MQTTManager
import com.sportser.sportser_coach.utils.MainPageInjectorUtils
import com.sportser.sportser_coach.viewmodel.MainPageViewModel
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage

class MainActivity : AppCompatActivity() {

    private var TAG = "CONSOLE"
    private val context: Context = this@MainActivity
    private lateinit var emailText: TextInputEditText
    private lateinit var email:String

    //notifications
    private val channel_ID = "channel_id_example"
    private val channel_name = "channelName"
    private val notification_id = 0

    private val mqttClient by lazy {
        MQTTManager(this)
    }

    private val viewModel by viewModels<MainPageViewModel> {
        MainPageInjectorUtils.provideMainPageViewModelFactory(context)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        val notificationManager = NotificationManagerCompat.from(this)
        setMqttCallBack(notificationManager, context)
    }

    fun connexion(view: View){
        emailText = findViewById(R.id.email)
        email = emailText.text.toString()
        Log.w(TAG, "connexion " + email)
        if(email.isNotEmpty()){
            mqttClient.connect()
        }
    }

    fun subscribe(view: View){
        if(email.isNotEmpty()){
            val topicToSubscribe = email.split("@gmail.com")[0]
            Log.w(TAG, "subscribe topic "+ topicToSubscribe)
            mqttClient.subscribe(topicToSubscribe)

            viewModel.sendRegistration(email)
        }
    }

    fun out(view: View){
        if(email.isNotEmpty()){
            val topicToUnSubscribe = email.split("@gmail.com")[0]
            Log.w(TAG, "unsusbcribe " + topicToUnSubscribe)
            mqttClient.unsubscribe(topicToUnSubscribe)

            viewModel.deleteRegistration(email)
        }
    }

    private fun setMqttCallBack(notificationManager: NotificationManagerCompat, context: Context) {
        mqttClient.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(b: Boolean, s: String) {
            }

            override fun connectionLost(throwable: Throwable) {
            }

            @Throws(Exception::class)
            override fun messageArrived(topic: String, mqttMessage: MqttMessage) {
                val notification = NotificationCompat.Builder(context, channel_ID)
                    .setContentText(mqttMessage.toString())
                    .setContentTitle("title")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(mqttMessage.toString()))
                    .build()

                notificationManager.notify(notification_id, notification)
                Log.w("Debug", "Message received from host : $mqttMessage")
            }
            override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {
            }
        })
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channel_ID, channel_name,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                    lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}