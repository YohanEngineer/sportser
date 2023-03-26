package com.sportser.sportser_coach.repository

import android.content.Context
import com.sportser.sportser_coach.service.OperationCallback
import com.sportser.sportser_coach.service.registration.RegistrationRemoteDataSource

class RegistrationRepository(
    private val registrationRemoteDataSource: RegistrationRemoteDataSource,
    private val context: Context
) {
    private val tag = "CONSOLE"

    fun sendRegistration(callback: OperationCallback<String>, email: String) {
        registrationRemoteDataSource.sendRegistration(callback, email)
    }

    fun deleteRegistration(callback: OperationCallback<String>, email: String) {
        registrationRemoteDataSource.deleteRegistration(callback, email)
    }
}