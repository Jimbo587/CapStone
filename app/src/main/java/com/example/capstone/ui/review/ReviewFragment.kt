package com.example.capstone.ui.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.model.Review
import com.example.capstone.repository.ReviewRepository
import com.example.capstone.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_movie_review.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewFragment : Fragment() {
    private var review = arrayListOf<Review>()
    private var reviewAdapter = ReviewAdapter(review)
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val viewModel: ReviewViewModel by viewModels()
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var reviewRepository: ReviewRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()
        observeAddGameResult()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                viewModel.deleteAllReviews()
                reviewAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun initRv() {

        reviewAdapter = ReviewAdapter(review)
        viewManager = LinearLayoutManager(activity)

        rvReview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = reviewAdapter
        }
    }

    private fun observeAddGameResult(){
        viewModel.review.observe(viewLifecycleOwner, Observer { review ->
            this@ReviewFragment.review.clear()
            this@ReviewFragment.review.addAll(review)
            reviewAdapter.notifyDataSetChanged()
        })
    }

    private fun deleteGamesFromDatabase(): Boolean {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                reviewRepository.deleteAllReviews()
            }
            observeAddGameResult()
        }
        return true
    }
}