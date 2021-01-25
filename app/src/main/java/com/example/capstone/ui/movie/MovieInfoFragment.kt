package com.example.capstone.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.capstone.R
import com.example.capstone.databinding.FragmentMovieInfoBinding
import com.example.capstone.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_info.*

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
        setRateClick()
        setReviewClick()
    }

    private fun initViews() {
        viewModel.movieInfo.observe(viewLifecycleOwner, {
            viewModel.getCurrentMovieInfo().value?.let {
                binding.tvTitle.text = it.title
                binding.tvReleasedate.text = it.releaseDate
                binding.tvRunTime.text = it.runTimeStr
                binding.tvPlot.text = it.plot
                binding.tvAwards.text = it.awards
                binding.tvActor.text = it.stars

                Glide.with(requireContext()).load(it.image).into(binding.ivMoviebg)
            }
        })
    }

    private fun setRateClick(){
        btnRate.setOnClickListener {
            findNavController().navigate(R.id.action_movieInfoFragment_to_movieRateFragment)
        }
    }

    private fun setReviewClick(){
        btnReview.setOnClickListener {
            findNavController().navigate(R.id.action_movieInfoFragment_to_movieReviewFragment)
        }
    }
}