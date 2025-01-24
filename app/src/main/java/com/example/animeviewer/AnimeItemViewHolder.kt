package com.example.animeviewer

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeviewer.databinding.AnimeItemBinding

class AnimeItemViewHolder(private var binding: AnimeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(anime: Anime) {
        binding.AnimeTitle.text = anime.title
        binding.animeRating.text = anime.rating
        binding.animeEpisodes.text = anime.episodes
        val imageView = binding.AnimeThumbnail
        Glide.with(binding.root.context).load(anime.images.jpg.imageUrl).into(imageView)
        binding.root.setOnClickListener {
            anime.clickEvent.invoke()
        }
    }
}