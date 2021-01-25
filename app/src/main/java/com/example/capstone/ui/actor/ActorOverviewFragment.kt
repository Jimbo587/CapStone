package com.example.capstone.ui.actor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstone.R
import com.example.capstone.databinding.FragmentActorOverviewBinding
import com.example.capstone.model.Actor
import com.example.capstone.viewmodel.ActorViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_actor_overview.*
import kotlinx.android.synthetic.main.fragment_movie_info.*
import kotlinx.android.synthetic.main.fragment_movie_overview.*
import kotlinx.android.synthetic.main.fragment_movie_overview.btnSubmit
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.item_movie.ivMovie

class ActorOverviewFragment: Fragment() {

    private val actor = arrayListOf<Actor>()
    private lateinit var actorAdapter: ActorAdapter
    private val viewModel: ActorViewModel by activityViewModels()
    private lateinit var binding: FragmentActorOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentActorOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actorAdapter = ActorAdapter(actor, ::initViews)
        setSubmitClick()
        observeActor()
        setMovieClick()
        binding.rvActorList.layoutManager = GridLayoutManager(activity, 3)
        binding.rvActorList.adapter = actorAdapter
    }

    private fun observeActor(){
        viewModel.actor.observe(viewLifecycleOwner, {
            actor.clear()
            actor.addAll(it)
            actorAdapter.notifyDataSetChanged()
        })
    }

    private fun initViews(actor: Actor){
        viewModel.setCurrentActor(actor)
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
            viewModel.getActors(title.toString())
        }
    }

    private fun setMovieClick(){
        ivMovie.setOnClickListener {
            findNavController().navigate(R.id.action_actorOverviewFragment_to_FirstFragment)
        }
    }
}