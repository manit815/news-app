package com.example.android.newsapplication.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.newsapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val newsArticles = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = DetailViewModelFactory(newsArticles, application)
        binding.viewModel = ViewModelProviders.of(
                this, viewModelFactory).get(DetailViewModel::class.java)
        return binding.root
    }
}