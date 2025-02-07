package com.oneasad.socialapp.util

import androidx.room.Room
import com.oneasad.socialapp.data.local.AppDatabase
import com.oneasad.socialapp.data.KtorApiService
import com.oneasad.socialapp.data.KtorClientProvider
import com.oneasad.socialapp.domain.KtorRepository
import com.oneasad.socialapp.presentation.PostViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { KtorClientProvider().createClient() }
    single { KtorApiService(get()) }
    single { KtorRepository(get()) }
    single { PostViewModel(get(), get()) }

    single {
        // Provide Room database instance without createFromAsset
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()  // For destructive migration if version changes
            .build()
    }

    single { get<AppDatabase>().postDao() }  // Provide DAO for injection
}