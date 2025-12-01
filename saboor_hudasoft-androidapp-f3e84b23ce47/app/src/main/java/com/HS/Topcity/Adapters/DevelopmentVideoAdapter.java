package com.HS.Topcity.Adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.R;

import java.util.ArrayList;

import hb.xvideoplayer.MxVideoPlayer;
import hb.xvideoplayer.MxVideoPlayerWidget;

public class DevelopmentVideoAdapter extends RecyclerView.Adapter<DevelopmentVideoAdapter.viewHolder> {
    Context context;
    ArrayList<String> video;
    View mBottomLayout;
    View mVideoLayout;
    Activity activity;



    public DevelopmentVideoAdapter(Context context, ArrayList<String> video, Activity activity) {
        this.context = context;
        this.video = video;
        this.activity = activity;

    }

    @NonNull
    @Override
    public DevelopmentVideoAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.development_video_layout, parent, false );
        return new DevelopmentVideoAdapter.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull DevelopmentVideoAdapter.viewHolder holder, int position) {

//       Uri videoUri = Uri.parse(video.get( position ));

        String videoUrl = video.get(position);
        // Handle video playback using MxVideoPlayerWidget (if it's configured correctly)
//        try {
//            holder.videoPlayer.startPlay(videoUrl, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "TopCity-1");
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle the exception or display an error message as needed
//        }

        // Load video in WebView (if necessary, but not recommended for YouTube videos)
        holder.webView.loadUrl(videoUrl);
        holder.webView.getSettings().setMediaPlaybackRequiresUserGesture(true);
        WebSettings webSettings = holder.webView.getSettings();
        holder.webView.getSettings().setJavaScriptEnabled(true);
        holder.webView.setWebChromeClient(new WebChromeClient());
        webSettings.setMediaPlaybackRequiresUserGesture(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);

//
//        MediaController mediaController= new MediaController(context);
//
//        mediaController.setMediaPlayer( holder.Video);
//         holder.Video.setMediaController( mediaController );
//        holder.Video.setVideoURI(videoUri);

//        String videos = video.get( position );


//        holder.videoView.setVideoURI( videoUri );
//        holder.videoView.setOnCompletionListener( new IMediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(IMediaPlayer iMediaPlayer) {
//                Toast.makeText( context, "again play", Toast.LENGTH_SHORT ).show();
//            }
//        } );


//        try{
//            holder.videoPlayer.startPlay(videos, MxVideoPlayer.SCREEN_LAYOUT_NORMAL,"TopCity-1");
//        }
//        catch (Exception exception){
//            System.out.println("falied");
//        }
//
//        holder.videoPlayer.mTinyBackImageView.setVisibility( View.GONE );
//
//
//        holder.webView.loadUrl( videos);
//        holder.webView.getSettings().setMediaPlaybackRequiresUserGesture(true);
//        WebSettings webSettings =  holder.webView.getSettings();
//        holder.webView.getSettings().setJavaScriptEnabled( true );
//        holder.webView.setWebChromeClient( new WebChromeClient() );
//        webSettings.setMediaPlaybackRequiresUserGesture(true);
//        webSettings.setPluginState( WebSettings.PluginState.ON );




//        holder.Video.setVideoViewCallback( new UniversalVideoView.VideoViewCallback() {
//            @Override
//            public void onScaleChange(boolean isFullscreen) {
//                isFullscreen = isFullscreen;
//                if (isFullscreen) {
//                    ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
//                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//                    mVideoLayout.setLayoutParams(layoutParams);
//                    //GONE the unconcerned views to leave room for video and controller
//                    mBottomLayout.setVisibility(View.GONE);
//                } else {
//                    ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
//                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                  //  layoutParams.height = context.cachedHeight;
//                    mVideoLayout.setLayoutParams(layoutParams);
//                    mBottomLayout.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onPause(MediaPlayer mediaPlayer) {
//                Log.d(TAG, "onPause UniversalVideoView callback");
//            }
//
//            @Override
//            public void onStart(MediaPlayer mediaPlayer) {
//
//            }
//
//            @Override
//            public void onBufferingStart(MediaPlayer mediaPlayer) {
//
//            }
//
//            @Override
//            public void onBufferingEnd(MediaPlayer mediaPlayer) {
//
//            }
//        } );
//            holder.Video.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.setLooping( true );
//                }
//            } );

    }

    @Override
    public int getItemCount() {
        return video.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder {
//        UniversalVideoView Video;
//        UniversalMediaController mediaController;

        TextView title;
        LinearLayout open;
        MxVideoPlayerWidget videoPlayer;
        WebView webView;



        public viewHolder(@NonNull View itemView) {
            super( itemView );
         //   Video = itemView.findViewById( R.id.development_video );
            videoPlayer = itemView.findViewById( R.id.mpw_video_player );
            webView = itemView.findViewById( R.id.webvideo );
         //   mediaController = itemView.findViewById( R.id.media );


        }
    }
}
