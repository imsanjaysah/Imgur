/*
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.data.api


import com.sanjay.imgur.data.repository.remote.model.ImgurSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**Interface where all the api used in app are defined.
 * @author Sanjay.Sah
 */
interface ImgurService {

    /**
     * Api for fetching Images list
     */
    @GET("/3/gallery/search/{page}")
    fun searchImages(@Path("page") page: Int, @Query("q") query: String,  @Header("Authorization") key: String
    ): Single<ImgurSearchResponse>

}