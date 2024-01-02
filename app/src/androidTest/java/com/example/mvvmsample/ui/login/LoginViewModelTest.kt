package com.example.mvvmsample.ui.login

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mvvmsample.data.AppDataBase
import com.example.mvvmsample.data.repos.UserRepo
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
            .allowMainThreadQueries().build()
        val repo = UserRepo(db)
        viewModel = LoginViewModel(repo)
    }

    @Test
    fun validation() {
        val mobile = "8886948684"
        val pwd = "vamsi6767"
        val result = viewModel.validation(mobile, pwd)
        assertThat(result).isTrue()
    }
}