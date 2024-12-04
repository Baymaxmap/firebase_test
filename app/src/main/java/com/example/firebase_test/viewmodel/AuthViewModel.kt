package com.example.firebase_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_test.model.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _signInResult = MutableLiveData<Result<FirebaseUser>?>()
    val signInResult: LiveData<Result<FirebaseUser>?> get() = _signInResult

    private val _signUpResult = MutableLiveData<Result<FirebaseUser>?>()
    val signUpResult: LiveData<Result<FirebaseUser>?> get() = _signUpResult

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _signInResult.value = null
            val result = authRepository.signIn(email, password)
            _signInResult.value = result
        }
    }

    fun signUp(email: String, password: String){
        viewModelScope.launch {
            _signUpResult.value = null
            val result = authRepository.signUp(email, password)
            _signUpResult.postValue(result)
        }
    }
}
