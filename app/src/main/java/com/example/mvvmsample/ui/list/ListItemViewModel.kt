package com.example.mvvmsample.ui.list

import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mvvmsample.data.responses.User

class ListItemViewModel(private val user: User) : ViewModel() {
    val userName = ObservableField("Name: ${user.name}")
    val mobile = ObservableField("Mobile: ${user.mobileNumber}")
    val age = ObservableField("Age: ${user.age}")

    fun onClick(view: View) {
        Toast.makeText(view.context, user.name, Toast.LENGTH_SHORT).show()
    }
}