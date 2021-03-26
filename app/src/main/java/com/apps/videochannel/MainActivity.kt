package com.apps.videochannel

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.sfaapp.view.base.BaseActivity
import com.apps.videochannel.adapters.RecyclerYoutubePlayList
import com.apps.videochannel.classes.Constant
import com.apps.videochannel.classes.OnBottomReachedListener
import com.apps.videochannel.databinding.ActivityMainBinding
import com.apps.videochannel.model.YoutubeModel
import com.apps.videochannel.viewmodel.YoutubePlayListViewModel

class MainActivity : BaseActivity() {


    private var arrayList: ArrayList<YoutubeModel.Item> =
        ArrayList<YoutubeModel.Item>()
    var nextKey = ""

    //private lateinit var arrayList: ArrayList<YoutubeModel.Item>
    private lateinit var youtubePlayListViewModel: YoutubePlayListViewModel
    private lateinit var recyclerYoutubePlayList: RecyclerYoutubePlayList

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        youtubePlayListViewModel = ViewModelProvider(this).get(YoutubePlayListViewModel::class.java)



        Handler().postDelayed(Runnable { getVideos("") }, 3500)


        binding.recyclerviewHome.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerviewHome.setHasFixedSize(true)
        recyclerYoutubePlayList = RecyclerYoutubePlayList(
            applicationContext,
            arrayList,
            OnBottomReachedListener {
                if (nextKey != "") {
                    getVideos(nextKey)
                }
            }, this
        )
        binding.recyclerviewHome.adapter = recyclerYoutubePlayList
        recyclerYoutubePlayList.notifyDataSetChanged()







        youtubePlayListViewModel.getYouChannelData()?.observe(this, Observer {

            if (it == null || it.items == null) {
                binding.linearLayoutPageError.visibility = View.VISIBLE
            } else {
                binding.linearLayoutPageError.visibility = View.GONE
                if (nextKey != "") {
                    if (it.items.size > 0) {
                        arrayList.addAll(it.items)
                    }
                } else {
                    arrayList = (it.items as ArrayList<YoutubeModel.Item>)
                }
                //recyclerYoutubePlayList.notifyDataSetChanged()
                recyclerYoutubePlayList.updateData(arrayList)
                nextKey = if (it.nextPageToken == null || it.nextPageToken.equals("")) {
                    ""
                } else {
                    it.nextPageToken
                }
            }


            /* Log.e("msg", "${arrayList.size}")
            if(it.nextPageToken == null || it.nextPageToken.equals("")){
                nextKey = ""
            }else{
                nextKey = it.nextPageToken
            }

            if(nextKey != "")
            {
                if (it.items.size > 0) {
                    arrayList.addAll(it.items)

                }
            }*/




            dismissDialog()
        })


    }


    private fun getVideos(TOKEN_ID: String) {

        showDialog()
        youtubePlayListViewModel.passData(
            Constant.MAX_LOAD_VIDEO,
            TOKEN_ID,
            Constant.API_KEY,
            Constant.PLAYLIST_ID
        )

    }

    fun openVideoPlayer(video: YoutubeModel.Item) {

        /*startActivity(Intent(this@MainActivity,VideoPlayerActivity::class.java)
            .putExtra("key.EXTRA_OBJECT",video))*/

       /* val intent = Intent(this@MainActivity, VideoPlayerActivity::class.java)
        intent.putExtra("key.EXTRA_OBJECT",video)
        startActivity(intent)*/

        val intent = Intent(this@MainActivity, VideoPlayerActivity::class.java)
        val b = Bundle()
        b.putString("videoId", video.snippet.resourceId.videoId)
        b.putString("videoTitle", video.snippet.title)
        b.putString("videoDescription", video.snippet.description)
        intent.putExtras(b)
        startActivity(intent)
    }


}


