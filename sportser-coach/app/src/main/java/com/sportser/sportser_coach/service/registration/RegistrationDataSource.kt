package com.sportser.sportser_coach.service.registration

import com.sportser.sportser_coach.service.OperationCallback

interface RegistrationDataSource {
    fun sendRegistration(callback: OperationCallback<String>, email: String)
    fun deleteRegistration(callback: OperationCallback<String>, email: String)
}