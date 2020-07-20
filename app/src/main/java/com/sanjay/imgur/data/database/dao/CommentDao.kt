package com.sanjay.imgur.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanjay.imgur.data.database.model.Comment
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertComment(comment: Comment)

    @Query("select * from comment where image_id = :imageId")
    fun getComments(imageId: String): Flowable<List<Comment>>
}