package com.sanjay.imgur.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanjay.imgur.R
import com.sanjay.imgur.databinding.FragmentImageDetailBinding
import com.sanjay.imgur.ui.BaseFragment
import javax.inject.Inject

class ImgurDetailImageFragment : BaseFragment() {

    private lateinit var binding: FragmentImageDetailBinding
    private val args: ImgurDetailImageFragmentArgs by navArgs()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var imageDetailViewModel: ImgurImageDetailViewModel
    private var commentsAdapter = CommentsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        imageDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(ImgurImageDetailViewModel::class.java)
        imageDetailViewModel.image = args.image

        binding = DataBindingUtil.inflate<FragmentImageDetailBinding>(
            inflater,
            R.layout.fragment_image_detail,
            container,
            false
        ).apply {
            viewModel = imageDetailViewModel
            lifecycleOwner = viewLifecycleOwner

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

        }
        init()
        loadComments()
        return binding.root
    }

    private fun init() {
        binding.buttonSaveComment.setOnClickListener {
            if (binding.editTextComment.text.isNotEmpty()) {
                imageDetailViewModel.saveComment(binding.editTextComment.text.toString())
                binding.editTextComment.text.clear()
            }
        }
        imageDetailViewModel.comments.observe(viewLifecycleOwner, Observer {
            commentsAdapter.submitList(it)
        })
    }

    private fun loadComments() {
        binding.recyclerViewComments.layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
        binding.recyclerViewComments.adapter = commentsAdapter
        imageDetailViewModel.fetchComments()
    }
}