package com.example.animeviewer

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animeviewer.databinding.AnimeItemBinding

class AnimeAdapter : ListAdapter<Anime, AnimeItemViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeItemViewHolder {
        val binding = AnimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Anime>() {
            override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem == newItem
            }
        }
    }
}