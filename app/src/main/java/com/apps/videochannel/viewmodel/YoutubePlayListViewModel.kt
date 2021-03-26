package com.apps.videochannel.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apps.videochannel.classes.RetrofitClient
import com.apps.videochannel.model.YoutubeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YoutubePlayListViewModel : ViewModel() {

    var getLoginData = MutableLiveData<YoutubeModel>()

    var youtubeModel: YoutubeModel? = null
    fun passData(maxLoadVideo: Int, tokenId: String, apiKey: String, playlistId: String) {

        val call = RetrofitClient.apiInterface.getVideos(maxLoadVideo, tokenId, playlistId, apiKey)
        call.enqueue(object : Callback<YoutubeModel?> {
            override fun onResponse(call: Call<YoutubeModel?>, response: Response<YoutubeModel?>) {
                if (response.isSuccessful) {
                    youtubeModel = response.body()
                    getLoginData.value = youtubeModel
                }
            }

            override fun onFailure(call: Call<YoutubeModel?>, t: Throwable) {

            }

        })

    }

    fun getYouChannelData(): MutableLiveData<YoutubeModel>? {

        return getLoginData
    }

}




