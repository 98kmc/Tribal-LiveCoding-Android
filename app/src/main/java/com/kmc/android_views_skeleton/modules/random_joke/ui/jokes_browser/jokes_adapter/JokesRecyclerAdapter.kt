package com.kmc.android_views_skeleton.modules.random_joke.ui.jokes_browser.jokes_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.kmc.android_views_skeleton.R
import com.kmc.android_views_skeleton.databinding.JokeItemCellBinding
import com.kmc.android_views_skeleton.modules.random_joke.domain.entity.Joke

class JokesRecyclerAdapter(
    private var items: MutableList<Joke> = mutableListOf(),
) :  RecyclerView.Adapter<JokesRecyclerAdapter.JokesItemViewHolder>() {

    inner class JokesItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = JokeItemCellBinding.bind(view)

        fun render(joke: Joke){

            binding.jokeTv.text = joke.text
            binding.jokeImv.load(joke.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
                transformations(RoundedCornersTransformation(24f))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesItemViewHolder {
        val cell = R.layout.joke_item_cell
        return JokesItemViewHolder(
            view = LayoutInflater.from(parent.context).inflate(cell, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: JokesItemViewHolder, position: Int) {
        holder.render(items[position])
    }

    fun applySnapshot(newList: List<Joke>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}