/*
 * DatabaseModule.kt
 */

package com.sanjay.imgur.injection.module

import android.content.Context
import androidx.room.Room
import com.sanjay.imgur.data.database.ImgurDatabase
import com.sanjay.imgur.data.database.dao.CommentDao

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * class creates database and provide dao
 *
 */
@Module
class DatabaseModule {

    companion object {

        private const val DATABASE = "database_name"
    }

    @Provides
    @Named(DATABASE)
    fun provideDatabaseName(): String = "com.imgur.android.db"

    @Provides
    @Singleton
    fun provideDatabase(context: Context, @Named(DATABASE) databaseName: String): ImgurDatabase {
        return Room.databaseBuilder(context, ImgurDatabase::class.java, databaseName)
                .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideCommentDao(database: ImgurDatabase): CommentDao = database.commentDao()
}