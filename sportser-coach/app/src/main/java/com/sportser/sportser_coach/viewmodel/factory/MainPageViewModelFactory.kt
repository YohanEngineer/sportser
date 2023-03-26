package com.sportser.sportser_coach.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sportser.sportser_coach.repository.RegistrationRepository
import com.sportser.sportser_coach.viewmodel.MainPageViewModel

class MainPageViewModelFactory(
    private val registrationRepository: RegistrationRepository,
    private val context: Context
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainPageViewModel(registrationRepository, context) as T
    }
}