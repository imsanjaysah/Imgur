/*
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.data.repository.local

import com.sanjay.imgur.data.database.dao.CommentDao
import com.sanjay.imgur.data.database.model.Comment
import com.sanjay.imgur.data.repository.ImgurDataSource
import com.sanjay.imgur.data.repository.remote.model.ImgurImage
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Class to handle local db operations
 *
 * @author Sanjay Sah
 */
class ImgurLocalDataSource @Inject constructor(val commentDao: CommentDao) : ImgurDataSource {
    override fun searchImages(apiKey: String, query: String, page: Int, limit: Int): Flowable<List<ImgurImage>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveComment(comment: Comment) = commentDao.insertComment(comment)

    override fun getComments(imageId: String): Flowable<List<Comment>> = commentDao.getComments(imageId)
}