package com.example.capstone.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstone.R
import com.example.capstone.databinding.FragmentMovieOverviewBinding
import com.example.capstone.model.Movie
import com.example.capstone.model.MovieInfo
import com.example.capstone.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_info.*
import kotlinx.android.synthetic.main.fragment_movie_overview.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieOverviewFragment : Fragment() {

    private val movies = arrayListOf<Movie>()
    lateinit var movieInfo: MovieInfo
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
        setActorClick()
        binding.rvMovieList.layoutManager = GridLayoutManager(activity, 3)
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
//      Log.i("MOV", "going to set ${movie.title!!} as current movie")
        viewModel.setCurrentMovie(movie)
        val code = movie.id
        viewModel.getMovieId(code.toString())
        viewModel.movieInfo.observe(viewLifecycleOwner, {
            movieInfo = it
            viewModel.setCurrentMovieInfo(movieInfo)
        })
        findNavController().navigate(R.id.action_FirstFragment_to_movieInfoFragment)
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

    private fun setActorClick(){
        ivActor.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_actorOverviewFragment)
        }
    }

}