package com.apps.videochannel.classes

import com.apps.videochannel.model.YoutubeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    /*@GET("channels?part=snippet%2CbrandingSettings%2Cstatistics")
    Call<ResponseInfo> getInfo(@Query("id") String str, @Query("key") String str2);

    @GET("playlists?part=snippet%2CcontentDetails")
    Call<ResponseVideos> getPlaylist(@Query("channelId") String str, @Query("maxResults") int i, @Query("pageToken") String str2, @Query("key") String str3);*/
    @GET("playlistItems?part=snippet%2CcontentDetails")
    fun getVideos(
        @Query("maxResults") result: Int,
        @Query("pageToken") token: String?,
        @Query("playlistId") pId: String?,
        @Query("key") apiKey: String?
    ): Call<YoutubeModel?> /* @GET("search?part=snippet&order=title&type=video")
    Call<ResponseSearchVideo> searchVideo(@Query("channelId") String str, @Query("q") String str2, @Query("pageToken") String str3, @Query("maxResults") int i, @Query("key") String str4);*/
}