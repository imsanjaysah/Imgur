package com.sanjay.imgur.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "image_id")
    var imageId: String,
    @ColumnInfo(name = "message")
    var message: String,
    @ColumnInfo(name = "timestamp")
    var timeStamp: String
);