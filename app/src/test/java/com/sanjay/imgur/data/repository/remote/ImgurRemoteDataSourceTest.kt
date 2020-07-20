package com.sanjay.imgur.data.repository.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sanjay.imgur.data.api.ImgurService
import com.sanjay.imgur.data.repository.remote.model.ImgurImage
import com.sanjay.imgur.data.repository.remote.model.ImgurSearchResponse
import io.reactivex.Single
import org.hamcrest.CoreMatchers
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ImgurRemoteDataSourceTest {
    @Mock
    lateinit var apiService: ImgurService

    private var remoteDataSource: ImgurRemoteDataSource? = null

    private val currentPage = 1
    private val limit = 20

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = ImgurRemoteDataSource(apiService)
    }

    @After
    fun tearDown() {
        remoteDataSource = null
    }

    @Test
    fun searchImages() {
        val imagesList = emptyList<ImgurImage>()
        val imagesResponse = ImgurSearchResponse(imagesList)
        val observable = Single.just(imagesResponse)

        val apiKey = "api_key"
        val query = "fruits"

        val argumentCaptorInt = argumentCaptor<Int>()
        val argumentCaptorString = argumentCaptor<String>()

        whenever(apiService.searchImages(apiKey, query, currentPage)).thenReturn(observable)

        remoteDataSource?.searchImages(apiKey, query, currentPage, limit)

        verify(apiService).searchImages(
            argumentCaptorString.capture(),
            argumentCaptorString.capture(),
            argumentCaptorInt.capture()
        )

        Assert.assertThat(
            apiService.searchImages(
                argumentCaptorString.firstValue,
                argumentCaptorString.secondValue,
                argumentCaptorInt.firstValue
            ),
            CoreMatchers.instanceOf(Single::class.java)
        )
    }

}