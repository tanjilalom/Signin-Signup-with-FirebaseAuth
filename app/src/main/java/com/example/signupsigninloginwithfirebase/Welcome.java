package com.example.signupsigninloginwithfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.VideoView;

import org.xml.sax.helpers.XMLFilterImpl;

public class Welcome extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    int loopCount = 0;
    private static final int MAX_LOOP_COUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        mediaPlayer = mediaPlayer.create(this, R.raw.meme);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);


        /*String video = "<iframe width=\"100%\" height=\"315\" src=\"https://www.youtube.com/embed/BBJa32lCaaY?si=vgm5mCq10JSyMDea\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";

        webView.loadData(video, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());*/

    }
}