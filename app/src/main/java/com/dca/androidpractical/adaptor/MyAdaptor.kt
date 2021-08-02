package com.dca.androidpractical.adaptor

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.dca.androidpractical.R
import com.dca.androidpractical.model.post
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


private const val Image_Type: Int = 0
private const val Video_Type: Int = 1
private var videoView:VideoView?=null

class MyAdaptor(val context: Context, val post: List<post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == Image_Type) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle, parent, false)
            return myviewHolder(v)
        }else{
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_video, parent, false)
            return videoviewHolder(v)
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position)== Image_Type){
            (holder as myviewHolder ).bind(post.get(position),context)
        }else{
            ( holder as videoviewHolder ).bind(post.get(position),context)
        }

    }

    override fun getItemCount(): Int {
        return post.size
    }

    override fun getItemViewType(position: Int): Int {
        if (post.get(position).posttype==1){
            return Image_Type
        }else{
            return Video_Type
        }
    }


    class myviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username = itemView.findViewById<TextView>(R.id.username);
        val userdesc = itemView.findViewById<TextView>(R.id.userdesc);
        val profileimg = itemView.findViewById<CircleImageView>(R.id.profilepic);
        val postimage = itemView.findViewById<ImageView>(R.id.postimage);
        val imagelayout = itemView.findViewById<RelativeLayout>(R.id.imagelayout);
        val videolayout = itemView.findViewById<FrameLayout>(R.id.mediaContainer);

        fun bind(post: post, context: Context){

            username.text = post.userName;
            userdesc.text = post.caption?.let { "Capation here" };
            Picasso.with(context)
                .load(post.userImage)
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .into(profileimg);
            if (post.posttype == 1) {
                Picasso.with(context)
                    .load(post.content)
                    .placeholder(R.drawable.ic_launcher_background)
                    .fit()
                    .into(postimage);
            } else if (post.posttype == 2) {
                Picasso.with(context)
                    .load(post.thumb)
                    .placeholder(R.drawable.ic_launcher_background)
                    .fit()
                    .into(postimage);
            }
            itemView.setOnClickListener {
               // Toast.makeText(context, post.userName, Toast.LENGTH_LONG).show()
            }
        }
    }
    class videoviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username = itemView.findViewById<TextView>(R.id.username);
        val userdesc = itemView.findViewById<TextView>(R.id.userdesc);
        val profileimg = itemView.findViewById<CircleImageView>(R.id.profilepic);
        val thumbimg = itemView.findViewById<ImageView>(R.id.postimage);
        val videolayout = itemView.findViewById<FrameLayout>(R.id.mediaContainer);
        val play=itemView.findViewById<ImageView>(R.id.play)
        val videoplayer = itemView.findViewById<VideoView>(R.id.videoplayer);
        fun bind(post: post, context: Context) {
            username.text = post.userName;
            userdesc.text = post.caption?.let { "Capation here" };
            Picasso.with(context)
                .load(post.userImage)
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .into(profileimg);
            if (post.posttype == 2) {
                Picasso.with(context)
                    .load(post.thumb)
                    .placeholder(R.drawable.ic_launcher_background)
                    .fit()
                    .into(thumbimg);
            }

            val uri=Uri.parse(post.content)
           // videoplayer.setMediaController(mediaController!)
            videoplayer.setVideoURI(uri)
            //videoplayer.setLayoutParams( FrameLayout.LayoutParams(850,1080));

            videoplayer.requestFocus()
            videoView=videoplayer;
            play.setOnClickListener{

                if (videoplayer.isPlaying) {
                    thumbimg.visibility = View.VISIBLE
                    videoplayer.visibility = View.GONE
                    videoplayer.stopPlayback()

                }else{

                    if (videoView!!.isPlaying){
                        videoView!!.stopPlayback()
                    }
                    thumbimg.visibility = View.GONE
                    videoplayer.visibility = View.VISIBLE
                    videoplayer.start()

                }
             //   mediaPlayer.start()
            }
            itemView.setOnClickListener {
              //  Toast.makeText(context, post.userName, Toast.LENGTH_LONG).show()
            }
        }

    }
}


