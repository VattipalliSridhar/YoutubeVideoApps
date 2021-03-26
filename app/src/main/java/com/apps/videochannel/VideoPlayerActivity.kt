package com.apps.videochannel

import android.os.Bundle
import com.apps.videochannel.classes.Constant
import com.apps.videochannel.databinding.ActivityVideoPlayerBinding
import com.apps.videochannel.model.YoutubeModel
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class VideoPlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var binding: ActivityVideoPlayerBinding
    private var arrayList: ArrayList<YoutubeModel.Item> =
        ArrayList<YoutubeModel.Item>()
    lateinit var videoId : String
    lateinit var videoDesc : String
    lateinit var videoTitle : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ytPv.initialize(Constant.API_KEY, this)

        val b = intent.extras
        videoId = b!!.getString("videoId").toString()
        videoDesc = b!!.getString("videoDescription").toString()
        videoTitle = b!!.getString("videoTitle").toString()

        binding.tvDetailDesc.text = videoDesc
        binding.tvTitle.text=videoTitle

    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean
    ) {
        player?.loadVideo(videoId);
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        TODO("Not yet implemented")
    }
}