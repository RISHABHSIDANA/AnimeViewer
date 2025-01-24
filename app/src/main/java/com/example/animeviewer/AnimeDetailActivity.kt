package com.example.animeviewer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.animeviewer.databinding.AnimeDetailActivityBinding
import com.example.animeviewer.databinding.AnimeListActivityBinding

class AnimeDetailActivity : ComponentActivity() {
    private lateinit var binding: AnimeDetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AnimeDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // TODO() to be replaced with room db call
        val animeDetails = intent.getSerializableExtra(AnimeUtils.ANIME_DETAILS) as AnimeDetails
        Log.i(TAG, "onCreate $animeDetails")
        loadVisuals(animeDetails)
        loadGenres((animeDetails))
        binding.textTitle.text = animeDetails.title
        binding.textPlot.text = animeDetails.synopsis
        binding.textRating.text = animeDetails.rating
        binding.textEpisodes.text = animeDetails.numEpisodes
    }

    private fun loadVisuals(animeDetails: AnimeDetails) {
        val webView = binding.webviewTrailer
        val imageView = binding.imageAnimePoster
        webView.settings.javaScriptEnabled = true

        if (animeDetails.url != null) {
            webView.visibility = View.VISIBLE
            imageView.visibility = View.GONE
            webView.loadUrl(animeDetails.url)
        } else {
            webView.visibility = View.GONE
            imageView.visibility = View.VISIBLE
            Glide.with(this)
                .load(animeDetails.imageUrl).into(imageView)
        }
    }

    fun loadGenres(animeDetails: AnimeDetails) {
        val genreContainer = binding.genreContainer
        animeDetails.genres.forEach {
            val genreTextView = TextView(this).apply {
                text = it
                setPadding(8, 4, 8, 4)
                setTextColor(ContextCompat.getColor(this@AnimeDetailActivity, R.color.black))
                textSize = 14f
            }
            genreContainer.addView(genreTextView)
        }
    }

    companion object {
        const val TAG = "AnimeDetailActivity"
    }
}