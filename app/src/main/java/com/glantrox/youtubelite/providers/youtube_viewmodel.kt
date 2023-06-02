package com.glantrox.youtubelite.providers

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glantrox.youtubelite.constants.PlayingVideoState
import com.glantrox.youtubelite.constants.YoutubeState
import com.glantrox.youtubelite.core.database.Repository
import com.glantrox.youtubelite.core.models.Item
import kotlinx.coroutines.launch

data class VideoItemState(
    var videos: List<Item> = emptyList(),
    var currentVideo: Item? = null
)

class YoutubeViewModel: ViewModel() {
    private val _repository = Repository.create()

    // List of Videos Data
    private val _listOfVideos = mutableStateOf(VideoItemState())
    val listOfVideos: State<VideoItemState> = _listOfVideos
    private val _currentListState = mutableStateOf(YoutubeState.none)
    val currentListState: State<YoutubeState> = _currentListState

    // Playing Video Data
    private val _playingVideo = mutableStateOf(VideoItemState())
    val playingVideo: State<VideoItemState> = _playingVideo
    private val _currentVideoState = mutableStateOf(PlayingVideoState.none)
    val currentVideoState: State<PlayingVideoState> = _currentVideoState

    private fun _setCurrentListState(state: YoutubeState) {
        _currentListState.value = state
    }

    private fun _setCurrentVideoState(state: PlayingVideoState) {
        _currentVideoState.value = state
    }

    fun getVideoDetail(id: String) {
        try {
            viewModelScope.launch {
                _setCurrentVideoState(PlayingVideoState.loading)
                val response = _repository.getDetailVideoById(id = id)
                if(response != null) {
                    _playingVideo.value.currentVideo = response.items[0]
                    _setCurrentVideoState(PlayingVideoState.success)
                } else {
                    _setCurrentVideoState(PlayingVideoState.empty)
                    _playingVideo.value.currentVideo = null
                }
            }
        } catch(e: Exception) {
            _setCurrentVideoState(PlayingVideoState.error)
            Log.d("apiDatas", "ERROR $e")
        }
    }

    fun getListOfVideos() {
        try {
         viewModelScope.launch {
             _setCurrentListState(YoutubeState.loading)
             Log.d("stateApiCheck","Loading...")
             val response = _repository.listOfPopularVideos()
             if(response != null) {
                 _listOfVideos.value.videos = response.items
                 _setCurrentListState(YoutubeState.success)
                 Log.d("stateApiCheck","Success")

             } else {
                 Log.d("stateApiCheck","Empty")
                 _setCurrentListState(YoutubeState.empty)
             }


         }

        } catch(e: Exception) {
            _setCurrentListState(YoutubeState.error)
            Log.d("apiDatas", "ERROR $e")
        }
    }

}