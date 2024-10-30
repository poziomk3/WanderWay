package com.pwr.wanderway.di

import com.pwr.wanderway.navigation.DefaultNavigator
import com.pwr.wanderway.navigation.Destination
import com.pwr.wanderway.navigation.Navigator
import com.pwr.wanderway.presentation.entryScreens.activateAccount.ActivateAccountViewModel
import com.pwr.wanderway.presentation.entryScreens.login.LoginViewModel
import com.pwr.wanderway.presentation.entryScreens.register.RegisterViewModel
import com.pwr.wanderway.presentation.entryScreens.welcome.WelcomeViewModel
import com.pwr.wanderway.presentation.navbar.AuthenticatedWrapperViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    single<Navigator> {
        DefaultNavigator(startDestination = Destination.UnauthorizedGroup)
    }

    viewModelOf(::AuthenticatedWrapperViewModel)
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::ActivateAccountViewModel)
}