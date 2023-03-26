package com.sportser.sportser_coach.service

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import java.util.*

class MQTTManager(val context: Context) {


    private val _message = MutableLiveData<String>()
    var message : LiveData<String> = _message
    private var host = "tcp://172.31.253.175:1883"
    private var clientId = "kotlin-client"

    private var client =
        MqttAndroidClient(context, host, clientId + id(context))
    private var uniqueID: String? = null
    private val PREF_UNIQUE_ID = "PREF_UNIQUE_ID"

    fun setCallback(callback: MqttCallbackExtended?){
        client.setCallback(callback)
    }

    init {
        client.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(b: Boolean, s: String) {
                Log.w("mqtt", "Connexion : " + s)
            }

            override fun connectionLost(throwable: Throwable) {
            }

            override fun messageArrived(topic: String, mqttMessage: MqttMessage) {
                Log.w("Mqtt message ", "$mqttMessage")
            }

            override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {
                Log.w("Mqtt ", "DeliveryComplete")
            }
        })
    }

    fun connect() {
        val mqttConnectOptions = MqttConnectOptions()
        mqttConnectOptions.isAutomaticReconnect = false
        mqttConnectOptions.isCleanSession = true
        try {
            client.connect(mqttConnectOptions, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                }

                override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                    Log.w("Mqtt", "Failed to connect to: " + host + exception.toString())
                }
            })
        } catch (ex: MqttException) {
            ex.printStackTrace()
        }
    }

    fun disconnect() {
        try {
            client.disconnect(null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.v("Mqtt", "disconnect")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.v("Mqtt", " failed disconnect")
                }

            })
        } catch (ex: MqttException) {
            System.err.println("Exception disconnect")
            ex.printStackTrace()
        }
    }

    // Subscribe to topic
    fun subscribe(topic: String) {
        try {
            client.subscribe(topic, 0, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    Log.w("Mqtt", "Subscription : " + topic)
                }

                override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                    Log.w("Mqtt", "Subscription fail!")
                }
            })
        } catch (ex: MqttException) {
            System.err.println("Exception subscribing")
        }
    }

    // Unsubscribe the topic
    fun unsubscribe(topic: String) {
        try {
            client.unsubscribe(topic, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.v("Mqtt", "unsubscribe")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.v("Mqtt", " failed unsubscribe")
                }

            })
        } catch (ex: MqttException) {
            System.err.println("Exception unsubscribe")
            ex.printStackTrace()
        }

    }

    fun publish(message: String, topic: String) {
        try {
            client.publish(
                topic,
                message.toByteArray(),
                0,
                false,
                null,
                object : IMqttActionListener {
                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                        Log.w("Mqtt", "Publish Success!")
                    }

                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        Log.w("Mqtt", "Publish Failed!")
                    }

                })
        } catch (ex: MqttException) {
            System.err.println("Exception publishing")
        }
    }

    @Synchronized
    fun id(context: Context): String {
        if (uniqueID == null) {
            val sharedPrefs = context.getSharedPreferences(
                PREF_UNIQUE_ID, Context.MODE_PRIVATE
            )
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null)
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString()
                val editor = sharedPrefs.edit()
                editor.putString(PREF_UNIQUE_ID, uniqueID)
                editor.commit()
            }
        }
        return uniqueID!!
    }
}

data class MQTTConnectionParams(
    val clientId: String,
    val host: String,
    val topic: String,
    val username: String,
    val password: String
) {

}