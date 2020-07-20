package com.sanjay.imgur.ui

import android.content.Context
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.sanjay.imgur.ImgurApplication
import com.sanjay.imgur.injection.FragmentComponent
import com.sanjay.imgur.injection.module.FragmentModule
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Sanjay
 */
abstract class BaseFragment : Fragment() {

    //Disposable to add all returned dispose values.
    val disposable = CompositeDisposable()

    lateinit var fragmentComponent: FragmentComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent =
            (activity!!.application as ImgurApplication).appComponent.fragmentModule(
                FragmentModule(this)
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        //Clear disposable for preventing memory leaks
        disposable.clear()
    }

    fun removeFlag() {
        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}