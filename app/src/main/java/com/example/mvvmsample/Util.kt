package com.example.mvvmsample

import android.content.Context
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Context.activity(): AppCompatActivity {
    return this as AppCompatActivity
}

fun Context.fragment(container: Int, fragment: Fragment, tag: String?) {
    this.activity()
        .supportFragmentManager
        .beginTransaction()
        .replace(container, fragment).also {
            if (tag != null) it.addToBackStack(tag)
            it.commit()
        }
}

fun EditText.emptyValidation(value: String) {
    if (text.trim().isEmpty())
        error = value
}

fun EditText.mobileValidation() {
    if (text.trim().isEmpty())
        error = "Please enter mobile number"
    if (text.trim().isNotEmpty()
        && text.trim().length != 10)
            error = "Please enter proper mobile number"
}

fun EditText.passwordValidation() {
   if (text.trim().isEmpty())
       error = "Please enter password"
    if (text.trim().isNotEmpty()
        && text.trim().length < 6)
            error = "Password must have at least 6 characters and max 10 characters"
}