package com.example.capstone.ui.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.databinding.ItemReviewBinding
import com.example.capstone.model.Review

class ReviewAdapter(private val review: List<Review>) :
        RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return review.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(review[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemReviewBinding.bind(itemView)

        fun databind(review: Review) {
            binding.tvUser.text = review.name
            binding.tvMovie.text = review.title
            binding.tvReview.text = review.review
            binding.tvRating.text = review.rating.toString()
        }
    }
}