package com.example.animeviewer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {
    private val _animeList = MutableLiveData<List<Anime>>()
    val animeList: LiveData<List<Anime>> = _animeList
    private val _currAnime = MutableSharedFlow<Anime>()
    val anime: SharedFlow<Anime> = _currAnime

    init {
        observeAnime()
    }

    private fun observeAnime() {
        viewModelScope.launch {

            val apiResult = RetrofitInstance.api.getAnimeList().animes
            Log.i(TAG, "observeClickEvent $apiResult")
            // TODO() emit to cache
            apiResult.map {
                it.clickEvent = { sendEvent(it) }
            }
            _animeList.postValue(apiResult)
        }
    }

    private fun sendEvent(it: Anime) = viewModelScope.launch { _currAnime.emit(it) }

    companion object {
        const val TAG = "AnimeListViewModel"
    }
}

