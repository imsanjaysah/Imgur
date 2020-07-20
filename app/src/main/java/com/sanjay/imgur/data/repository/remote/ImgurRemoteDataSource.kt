/*
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.data.repository.remote

import com.sanjay.imgur.data.api.ImgurService
import com.sanjay.imgur.data.database.model.Comment
import com.sanjay.imgur.data.repository.ImgurDataSource
import com.sanjay.imgur.data.repository.remote.model.ImgurImage

import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Class to handle remote operations
 *
 * @author Sanjay.Sah
 */
class ImgurRemoteDataSource @Inject constructor(private var remoteService: ImgurService) :
    ImgurDataSource {
    override fun searchImages(apiKey: String, query: String, page: Int, limit: Int): Flowable<List<ImgurImage>> =
        remoteService.searchImages(page, query, apiKey).map {
            it.images
        }.toFlowable().take(1)

    override fun saveComment(comment: Comment) {
        TODO("Not yet implemented")
    }

    override fun getComments(imageId: String): Flowable<List<Comment>> {
        TODO("Not yet implemented")
    }
}