package com.example.animeviewer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeviewer.databinding.AnimeListActivityBinding
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var binding: AnimeListActivityBinding
    private lateinit var adapter: AnimeAdapter
    private val viewModel: AnimeListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AnimeListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(TAG, "onCreate")
        adapter = AnimeAdapter()
        binding.recyclerViewAnimes.adapter = adapter
        binding.loadingLayout.visibility = View.VISIBLE
        binding.recyclerViewAnimes.layoutManager = LinearLayoutManager(this)
        observeAnimeList()
        observeItemClick()
    }

    private fun observeItemClick() {
        lifecycleScope.launch {
            viewModel.anime.collect {
                val clickedAnimeDetail = AnimeDetails(
                    it.trailer.embedUrl ?: "",
                    it.title,
                    it.synopsis,
                    it.genres.map { genre -> genre.name },
                    it.episodes,
                    it.rating,
                    it.images.jpg.imageUrl
                )
                Log.i(TAG, "observeItemClick $clickedAnimeDetail")
                val intent = Intent(this@MainActivity, AnimeDetailActivity::class.java)
                intent.putExtra(AnimeUtils.ANIME_DETAILS, clickedAnimeDetail)
                this@MainActivity.startActivity(intent)
            }
        }
    }

    private fun observeAnimeList() {
        viewModel.animeList.observe(this) {
            binding.loadingLayout.visibility = View.GONE
            adapter.submitList(it)
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}


