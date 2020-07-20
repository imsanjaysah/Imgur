package com.sanjay.imgur.paging.factory

import androidx.paging.DataSource
import com.sanjay.imgur.data.repository.remote.model.ImgurImage
import com.sanjay.imgur.paging.datasource.ImgurSearchPagingDataSource
import javax.inject.Inject

class ImgurSearchPagingDataSourceFactory @Inject constructor(val dataSource: ImgurSearchPagingDataSource) :
    DataSource.Factory<Int, ImgurImage>() {

    override fun create(): DataSource<Int, ImgurImage> {
        return dataSource
    }
}