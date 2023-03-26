package com.sportser.sportser_coach.utils

import android.content.Context
import com.sportser.sportser_coach.repository.RegistrationRepository
import com.sportser.sportser_coach.service.registration.ApiRegistration
import com.sportser.sportser_coach.service.registration.RegistrationRemoteDataSource
import com.sportser.sportser_coach.viewmodel.factory.MainPageViewModelFactory

object MainPageInjectorUtils {
    private var registrationRemoteDataSource: RegistrationRemoteDataSource? = null
    private var registrationRepository: RegistrationRepository? = null
    private var mainPageViewModelFactory: MainPageViewModelFactory? = null

    private fun createRegistrationRemoteDataSource(): RegistrationRemoteDataSource {
        val dataSource = RegistrationRemoteDataSource(ApiRegistration)
        registrationRemoteDataSource = dataSource
        return dataSource
    }

    private fun createRegistrationRepository(context: Context): RegistrationRepository {
        val repository =
            RegistrationRepository(provideDataSource(), context)
        registrationRepository = repository
        return repository
    }

    private fun createFactory(context: Context): MainPageViewModelFactory {
        val factory = MainPageViewModelFactory(providerRepository(context), context)
        mainPageViewModelFactory = factory
        return factory
    }

    private fun provideDataSource() = registrationRemoteDataSource ?: createRegistrationRemoteDataSource()

    private fun providerRepository(context: Context) =
        registrationRepository ?: createRegistrationRepository(context)

    fun provideMainPageViewModelFactory(context: Context) =
        mainPageViewModelFactory ?: createFactory(context)
}