package com.example.mvvmsample.ui.login

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsample.data.repos.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: UserRepo) : ViewModel() {
    var mobileNumber = MutableLiveData("")
    var password = MutableLiveData("")
    var isValid = MutableLiveData<Boolean>()
    var isSignUpClicked = MutableLiveData<Boolean>()
    var isUserValid = MutableLiveData<Boolean>()

    fun login(mobile: String, pwd: String) {
        isValid.value = validation(mobile, pwd)
    }

    fun signUp() {
        isSignUpClicked.value = true
    }

    fun getUser() = viewModelScope.launch {
        val user = repo.getUser(mobileNumber.value.toString())
        isUserValid.value = user?.password == password.value.toString()
    }

    fun validation(mobile: String, pwd: String): Boolean {
        return mobile.isNotEmpty()
                && mobile.length == 10
                && pwd.isNotEmpty()
                && pwd.length >= 6
    }
}