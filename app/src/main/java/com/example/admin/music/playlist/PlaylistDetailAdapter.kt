package com.example.admin.music.playlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.admin.music.R
import com.example.admin.music.base.BaseRVAdapter
import com.example.admin.music.data.PlaylistDetailBean

class PlaylistDetailAdapter(context: Context, private val playlistDetailData: PlaylistDetailBean.PlaylistDetailData) :
    BaseRVAdapter(context), PlaylistContact.PlaylistDetailAdapter {
    companion object {
        private const val HEAD_TYPE = 0
        private const val MAIN_TYPE = 1
    }

    private val tracksList: List<PlaylistDetailBean.Track> = playlistDetailData.playlist.tracks

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            HEAD_TYPE
        else
            MAIN_TYPE
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        lateinit var viewHolder: RecyclerView.ViewHolder
        when (i) {
            HEAD_TYPE -> viewHolder = HeadHolder(layoutInflater.inflate(R.layout.item_playlist_head, viewGroup, false))
            MAIN_TYPE -> viewHolder = MainHolder(layoutInflater.inflate(R.layout.item_playlist_main, viewGroup, false))
        }
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        when (getItemViewType(i)) {
            HEAD_TYPE -> {
                val headHolder = viewHolder as HeadHolder
                val creatorBean = playlistDetailData.playlist.creator
                headHolder.author.text = creatorBean.nickname
                Glide.with(viewHolder.itemView)
                    .load(creatorBean.avatarUrl)
                    .into(headHolder.avatar)
                headHolder.name.text = playlistDetailData.playlist.name
            }
            MAIN_TYPE -> {
                val mainHolder = viewHolder as MainHolder
                val tracksBean = tracksList[i - 1]
                mainHolder.serialNumber.text = (i - 1).toString()
                mainHolder.name.text = tracksBean.name
                val author = tracksBean.ar[0].name + tracksBean.al.name
                mainHolder.author.text = author
            }
        }
    }

    override fun getItemCount(): Int {
        return tracksList!!.size + 1
    }

    internal inner class HeadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatar: ImageView
        var name: TextView
        var author: TextView

        init {
            avatar = itemView.findViewById(R.id.head_avatar)
            name = itemView.findViewById(R.id.head_name)
            author = itemView.findViewById(R.id.head_author)
        }
    }

    internal inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var serialNumber: TextView
        var name: TextView
        var author: TextView

        init {
            serialNumber = itemView.findViewById(R.id.serial_number)
            name = itemView.findViewById(R.id.song_name)
            author = itemView.findViewById(R.id.song_author)
        }
    }
}