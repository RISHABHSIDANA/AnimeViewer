package com.example.animeviewer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeviewer.databinding.AnimeListActivityBinding
import com.example.animeviewer.ui.theme.AnimeViewerTheme

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
        binding.recyclerViewAnimes.layoutManager = LinearLayoutManager(this)
        observeAnimeList()
        observeItemClick()
    }

    private fun observeItemClick() {
        viewModel.anime.observe(this) {
            val clickedAnimeDetail = AnimeDetails(
                it.trailer.embedUrl,
                it.title,
                it.synopsis,
                it.genres.map { genre -> genre.name },
                it.episodes,
                it.rating,
                it.images.jpg.imageUrl
            )
            Log.i(TAG, "observeItemClick $clickedAnimeDetail")
            val intent = Intent(this, AnimeDetailActivity::class.java)
            intent.putExtra(AnimeUtils.ANIME_DETAILS, clickedAnimeDetail)
            this.startActivity(intent)
        }
    }

    private fun observeAnimeList() {
        viewModel.animeList.observe(this) {
            adapter.submitList(it)
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}


