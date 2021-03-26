package com.apps.videochannel.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.videochannel.MainActivity
import com.apps.videochannel.R
import com.apps.videochannel.adapters.RecyclerYoutubePlayList.MyHolderView
import com.apps.videochannel.classes.OnBottomReachedListener
import com.apps.videochannel.databinding.YoutubePlayListAdapterBinding
import com.apps.videochannel.model.YoutubeModel
import com.bumptech.glide.Glide


class RecyclerYoutubePlayList(
    private val applicationContext: Context,
    private var videoList: ArrayList<YoutubeModel.Item>,
    private val onBottomReachedListener: OnBottomReachedListener,
    private var mainActivity: MainActivity
) : RecyclerView.Adapter<MyHolderView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderView {
        val  view =
            YoutubePlayListAdapterBinding.inflate(
                LayoutInflater.from(applicationContext),
                parent,
                false
            )
        return MyHolderView(view)
    }

    override fun onBindViewHolder(holder: MyHolderView, position: Int) {

        holder.youtubePlayListAdapterBinding.titleYoutube.text = videoList[position].snippet.title

        Glide.with(applicationContext)
            .load(videoList[position].snippet.thumbnails.high.url)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.youtubePlayListAdapterBinding.youtubeThumb)

        if (position == this.videoList.size - 1) {
            onBottomReachedListener.onBottomReached(position)
        }

        holder.youtubePlayListAdapterBinding.layout.setOnClickListener {


            val video = videoList[holder.adapterPosition]
            mainActivity.openVideoPlayer(video)
        }

    }
    override fun getItemCount(): Int {
        return videoList.size
    }

    fun updateData(list: ArrayList<YoutubeModel.Item>) {
        videoList = list
        notifyDataSetChanged()
    }

    inner  class MyHolderView(var youtubePlayListAdapterBinding: YoutubePlayListAdapterBinding) : RecyclerView.ViewHolder(
        youtubePlayListAdapterBinding.root
    )

}