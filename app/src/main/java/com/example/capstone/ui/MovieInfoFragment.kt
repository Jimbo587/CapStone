package com.example.capstone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.capstone.databinding.FragmentMovieInfoBinding
import com.example.capstone.viewmodel.MovieViewModel

class MovieInfoFragment : Fragment() {
    private lateinit var binding: FragmentMovieInfoBinding
    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        viewModel.getCurrentMovie().value?.let {
            binding.tvTitle.text = it.title
            binding.tvReleasedate.text = it.releaseDate
            binding.tvRunTime.text = it.runTimeStr
            binding.tvPlot.text = it.plot

            Glide.with(requireContext()).load(it.image).into(binding.ivMoviebg)
        }
    }
}