package com.example.mvvmauthretrofit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory(private val listener: LoginResultCallBacks, context: Context):
ViewModelProvider.NewInstanceFactory(){
    var context = context
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(listener, context) as T
    }
}