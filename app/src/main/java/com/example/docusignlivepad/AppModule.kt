package com.example.docusignlivepad

import com.example.docusignlivepad.data.DocuSignRepository
import com.example.docusignlivepad.data.DocuSignRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDocuSignRepository(): DocuSignRepository {
        return DocuSignRepositoryImpl()
    }

}