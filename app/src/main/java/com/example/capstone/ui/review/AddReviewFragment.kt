package com.example.capstone.ui.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.model.Review
import com.example.capstone.viewmodel.ReviewViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_overview.*
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
        val rating = etRating.text.toString()
        val review = etReview.text.toString()

        if (reviewer.isNotBlank() || title.isNotBlank() || rating.isNotBlank() || review.isNotBlank()){
            viewModel.insertReview(Review(reviewer, title, rating.toInt(), review))

            findNavController().navigate(R.id.action_movieRateFragment_to_movieReviewFragment)
        } else {
            Snackbar.make(btnSubmitReview, "Please fill in all the fields", Snackbar.LENGTH_LONG).show()
        }
    }
}