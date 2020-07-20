package com.sanjay.imgur.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanjay.imgur.constants.State
import com.sanjay.imgur.data.database.model.Comment
import com.sanjay.imgur.data.repository.ImgurRepository
import com.sanjay.imgur.data.repository.remote.model.ImgurImage
import com.sanjay.imgur.extensions.addToCompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ImgurImageDetailViewModel @Inject constructor(val repository: ImgurRepository) : ViewModel() {

    val comments: MutableLiveData<List<Comment>> = MutableLiveData()
    var image: ImgurImage? = null
    var state = MutableLiveData<State>()
    var disposable = CompositeDisposable()

    fun saveComment(message: String) {
        Comment(0, image!!.id, message, getCurrentDateAndTime()).let {
            repository.saveComment(it)
            fetchComments();
        }
    }

    fun fetchComments() {
        repository.getComments(image!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ it ->
                comments.postValue(it)
                state.value = State.DONE
                Log.d("Comments", it.size.toString())
            }, {
                it.printStackTrace()
                state.value = State.ERROR

            }).addToCompositeDisposable(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose();
    }


    private fun getCurrentDateAndTime(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        return simpleDateFormat.format(calendar.time)
    }
}

