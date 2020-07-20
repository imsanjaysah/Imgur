/*
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.data.repository

import com.sanjay.imgur.data.database.model.Comment
import com.sanjay.imgur.data.repository.remote.model.ImgurImage
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * @author Sanjay Sah
 */
interface ImgurDataSource {

    fun searchImages(apiKey: String, query: String, page: Int, limit: Int): Flowable<List<ImgurImage>>

    fun saveComment(comment: Comment);

    fun getComments(imageId: String): Flowable<List<Comment>>

}