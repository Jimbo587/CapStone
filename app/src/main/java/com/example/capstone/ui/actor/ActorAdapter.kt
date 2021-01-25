package com.example.capstone.ui.actor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone.R
import com.example.capstone.databinding.ItemActorBinding
import com.example.capstone.model.Actor

class ActorAdapter(private val actor: List<Actor>, private val onClick: (Actor) -> Unit) :
    RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_actor, parent, false)
        )
    }

    override fun getItemCount(): Int = actor.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(actor[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemActorBinding.bind(itemView)

        init {
            itemView.setOnClickListener { onClick(actor[adapterPosition]) }
        }

        fun bind(actor: Actor) {
            binding.tvTitle.text = actor.title
            Glide.with(context).load(actor.image).into(binding.ivActor)
            binding.tvDescription.text = actor.description
        }
    }
}