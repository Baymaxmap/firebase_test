package com.example.firebase_test.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository {
    //FirebaseAuth is object that support for authentication through email, phone,... (signIn, signUp,...)
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    //FirebaseUser is object that includes inf of an user: email, name, uid,...
    suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
        return try {
            //auth.signInWithEmailAndPassword return Task<AuthResult>
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUp(email:String, password: String): Result<FirebaseUser>{
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(result.user!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
