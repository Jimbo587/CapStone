package com.example.capstone.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.capstone.R
import com.example.capstone.model.Review
import com.example.capstone.viewmodel.ReviewViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_rate.*


class AddReviewFragment : Fragment() {

    private val viewModel: ReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_rate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSubmitReview.setOnClickListener {
            onAddReview()
        }
    }

    private fun onAddReview(){
        val reviewer = etName.text.toString()
        val title = etMovie.text.toString()
        val rating = etRating.text.toString().toInt()
        val review = etReview.text.toString()

        if (rating > 10 || rating < 1){
            Snackbar.make(btnSubmitReview, "Rating is not correct! Please try again", Snackbar.LENGTH_LONG).show()
        } else if(reviewer.isBlank() || title.isBlank() || review.isBlank()) {
            Snackbar.make(btnSubmitReview, "Please fill in all the fields", Snackbar.LENGTH_LONG).show()
        } else {
            viewModel.insertReview(Review(reviewer, title, rating, review))

            findNavController().navigate(R.id.action_movieRateFragment_to_movieReviewFragment)
        }
    }
}