package com.example.myappnote.di

import com.example.myappnote.core.usecase.SendNotesService
import com.example.myappnote.core.usecase.impl.SendNotesServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideSendNotesService(impl: SendNotesServiceImpl): SendNotesService
}