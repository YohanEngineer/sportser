package com.sportser.sportser_coach.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sportser.sportser_coach.repository.RegistrationRepository
import com.sportser.sportser_coach.service.OperationCallback

class MainPageViewModel(
    private val repository: RegistrationRepository,
    private val context: Context
) :
    ViewModel() {

    private val tag = "CONSOLE"

    fun sendRegistration(email: String) {
        if (checkInternet(context)) {
            Log.v(tag, "access network")
            repository.sendRegistration(object : OperationCallback<String> {
                override fun onSuccess(data: String) {
                    Log.v(tag, data)
                }

                override fun onError(error: String?) {
                    TODO("Not yet implemented")
                }

            }, email)
        } else {
            Log.v(tag, "not access network")
        }
    }

    fun deleteRegistration(email: String) {
        if (checkInternet(context)) {
            Log.v(tag, "access network")
            repository.deleteRegistration(object : OperationCallback<String> {
                override fun onSuccess(data: String) {
                    Log.v(tag, data)
                }

                override fun onError(error: String?) {
                    TODO("Not yet implemented")
                }

            }, email)
        } else {
            Log.v(tag, "not access network")
        }
    }

    private fun checkInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            else -> false
        }
    }
}