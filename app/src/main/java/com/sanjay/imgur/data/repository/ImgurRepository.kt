/*
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.data.repository


import com.sanjay.imgur.data.database.model.Comment
import com.sanjay.imgur.data.repository.remote.model.ImgurImage
import com.sanjay.imgur.injection.annotations.Local
import com.sanjay.imgur.injection.annotations.Remote
import io.reactivex.Flowable
import javax.inject.Inject

/**
 *
 * @author Sanjay Sah.
 */
class ImgurRepository @Inject constructor(@Local private val localDataSource: ImgurDataSource, @Remote private val remoteDataSource: ImgurDataSource) :
    ImgurDataSource {
    override fun searchImages(apiKey: String, query: String, page: Int, limit: Int): Flowable<List<ImgurImage>> =
        remoteDataSource.searchImages(apiKey, query, page, limit)

    override fun saveComment(comment: Comment) = localDataSource.saveComment(comment)

    override fun getComments(imageId: String): Flowable<List<Comment>> = localDataSource.getComments(imageId)

}
