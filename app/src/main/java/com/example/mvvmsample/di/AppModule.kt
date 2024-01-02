package com.example.mvvmsample.di

import android.content.Context
import com.example.mvvmsample.data.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRoomDB(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.invoke(context)
    }

}