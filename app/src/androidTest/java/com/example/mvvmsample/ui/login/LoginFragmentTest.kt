package com.example.mvvmsample.ui.login

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mvvmsample.R
import com.example.mvvmsample.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class LoginFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
        launchFragmentInHiltContainer<LoginFragment> {}
    }

    @Test
    fun login() {
        val mobile = "8886948684"
        val password = "vamsi6767"
        onView(withId(R.id.et_login_mobile_number)).perform(typeText(mobile))
        onView(withId(R.id.et_login_password)).perform(typeText(password))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_login)).perform(click())
    }

}