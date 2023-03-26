package com.sportser.sportser_coach.service

interface OperationCallback<T> {
    fun onSuccess(data: T)
    fun onError(error: String?)
}