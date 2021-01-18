package com.example.capstone.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstone.R
import com.example.capstone.databinding.FragmentMovieOverviewBinding
import com.example.capstone.model.Movie
import com.example.capstone.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_overview.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieOverviewFragment : Fragment() {

    private val movies = arrayListOf<Movie>()
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MovieViewModel by activityViewModels()
    private lateinit var binding: FragmentMovieOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentMovieOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter(movies, ::initViews)
        setSubmitClick()
        observeMovie()
        binding.rvMovieList.layoutManager = GridLayoutManager(activity, 2)
        binding.rvMovieList.adapter = movieAdapter
    }

    private fun observeMovie(){
        viewModel.movie.observe(viewLifecycleOwner, {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun initViews(movie: Movie){
        Log.i("MOV", "going to set ${movie.title!!} as current movie")
        viewModel.setCurrentMovie(movie)
        findNavController().navigate(R.id.action_FirstFragment_to_movieInfoFragment)
        Log.i("MOV", viewModel.getCurrentMovie().value!!.title!!)
    }

    private fun setSubmitClick(){
        binding.btnSubmit.setOnClickListener{
            val title  = binding.etTitle.text!!

            if (title.isEmpty()) {
                Snackbar.make(btnSubmit, "The title is invalid",
                    Snackbar.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            viewModel.getMovies(title.toString())

        }
    }
}