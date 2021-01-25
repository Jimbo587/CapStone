package com.example.capstone.ui.review

import android.os.Bundle
import android.view.*
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
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
        observeAddReview()
    }

    private fun initRv() {

        reviewAdapter = ReviewAdapter(review)
        viewManager = LinearLayoutManager(activity)

        createItemTouchHelper().attachToRecyclerView(rvReview)

        rvReview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = reviewAdapter
        }
    }

    private fun observeAddReview(){
        viewModel.review.observe(viewLifecycleOwner, Observer { review ->
            this@ReviewFragment.review.clear()
            this@ReviewFragment.review.addAll(review)
            reviewAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val reviewToDelete = review[position]
                viewModel.deleteReview(reviewToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }
}