package com.example.mvvmsample.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsample.data.repos.UserRepo
import com.example.mvvmsample.data.responses.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repo: UserRepo) : ViewModel() {
    val list = MutableLiveData<List<User>>()
    val logout = MutableLiveData<Boolean>()
    fun getAllUsers() = viewModelScope.launch {
        list.value = repo.getAllUsers() ?: emptyList()
    }

    fun logout() {
        logout.value = true
    }
}