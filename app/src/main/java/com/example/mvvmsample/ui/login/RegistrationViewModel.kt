package com.example.mvvmsample.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsample.data.repos.UserRepo
import com.example.mvvmsample.data.responses.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repo: UserRepo) : ViewModel() {
    var userName = MutableLiveData("")
    var age = MutableLiveData("")
    var mobileNumber = MutableLiveData("")
    var password = MutableLiveData("")
    var isValid = MutableLiveData<Boolean>()
    var isUserSaved = MutableLiveData<Boolean>()

    fun registration() {
        isValid.value = validation(
            userName.value.toString(),
            age.value.toString(),
            mobileNumber.value.toString(),
            password.value.toString()
        )
    }

    private fun validation(name: String, age: String, mobile: String, pwd: String): Boolean {
        return name.isNotEmpty()
                && age.isNotEmpty()
                && mobile.isNotEmpty()
                && mobile.length == 10
                && pwd.isNotEmpty()
                && pwd.length >= 6
    }

    fun saveUser() = viewModelScope.launch {
        repo.saveUser(
            User(
                mobileNumber.value.toString(),
                password.value.toString(),
                userName.value.toString(),
                age.value.toString().toInt()
            )
        )
        isUserSaved.value = true
    }
}