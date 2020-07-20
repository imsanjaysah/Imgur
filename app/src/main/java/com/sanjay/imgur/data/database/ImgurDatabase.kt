package com.sanjay.imgur.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanjay.imgur.data.database.dao.CommentDao
import com.sanjay.imgur.data.database.model.Comment

@Database(entities = [Comment::class], version = 1)
abstract class ImgurDatabase : RoomDatabase() {

    abstract fun commentDao(): CommentDao

}