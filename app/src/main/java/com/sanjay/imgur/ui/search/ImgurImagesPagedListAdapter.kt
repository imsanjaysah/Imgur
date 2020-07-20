/*
 * Created by Sanjay.Sah
 */

package com.sanjay.imgur.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sanjay.imgur.R
import com.sanjay.imgur.constants.State
import com.sanjay.imgur.data.repository.remote.model.ImgurImage
import com.sanjay.imgur.databinding.ItemListImgurImageBinding
import com.sanjay.imgur.ui.view.ListFooterViewHolder


/**
 * Adapter responsible for binding the list of ingredients to RecyclerView
 */
class ImgurImagesPagedListAdapter(
) : PagedListAdapter<ImgurImage, RecyclerView.ViewHolder>(diffCallback) {


    private var state = State.LOADING

    companion object {
        const val DATA_VIEW_TYPE = 1
        const val FOOTER_VIEW_TYPE = 2
        /**
         * DiffUtils is used improve the performance by finding difference between two lists and updating only the new items
         */
        private val diffCallback = object : DiffUtil.ItemCallback<ImgurImage>() {
            override fun areItemsTheSame(oldItem: ImgurImage, newItem: ImgurImage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImgurImage, newItem: ImgurImage): Boolean {
                return oldItem.id == newItem.id
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) ImgurImageViewHolder.create(parent) else ListFooterViewHolder.create(
            {

            },
            parent
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as ImgurImageViewHolder).bind(getItem(position)!!)
        else (holder as ListFooterViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}

/**
 * ViewHolder to display ingredient information
 */
class ImgurImageViewHolder(val binding: ItemListImgurImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setClickListener {
            binding.imgurImage?.let { image ->
                navigateToImageDetail(image, it)
            }
        }
    }

    private fun navigateToImageDetail(image: ImgurImage, view: View) {
        val direction = ImgurImagesFragmentDirections.actionImageDetail(image)
        view.findNavController().navigate(direction)
    }

    fun bind(item: ImgurImage) {
        binding.apply {
            imgurImage = item
            executePendingBindings()
        }

    }

    companion object {
        fun create(parent: ViewGroup): ImgurImageViewHolder {

            val binding = DataBindingUtil.inflate<ItemListImgurImageBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_list_imgur_image,
                parent,
                false
            )
            return ImgurImageViewHolder(binding)
        }
    }
}
