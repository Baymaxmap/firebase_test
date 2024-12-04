package com.example.firebase_test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebase_test.model.AuthRepository

class AuthFactory(private val _repository: AuthRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> (AuthViewModel(_repository) as T)
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}