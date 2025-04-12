package com.example.nasapto.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.nasapto.R;
import com.example.nasapto.databinding.ActivityItineraryBinding;
import com.example.nasapto.utils.DataClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class ItineraryActivity extends BaseActivity {
    private ActivityItineraryBinding binding;
    private int currentPosition = 0;

    @Override
    View getContentView() {
        binding = ActivityItineraryBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    void init() {
            String title = getIntent().getStringExtra(DataClass.INTENT_KEY_TITLE);
           binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onBackPressed();
               }
           });
           binding.toolbar.toolbarTitle.setText("Welcome To Your Itineary");

            ArrayList<String> content = null;
            switch (title.toLowerCase(Locale.ENGLISH)) {
                case "sun":
                    content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.sunitin)));
                    break;
                case "mercury":
                    content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.mercuryitin)));
                    break;
                case "venus":
                    content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.venusitin)));
                    break;
                case "earth":
                    content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.earthitin)));
                    break;
                case "moon":
                    content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.moonitin)));
                    break;
                case "mars":
                    content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.marsitin)));
                    break;
                case "jupiter":
                    content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jupiteritin)));
                    break;
                case "saturn":
                    content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.saturnitin)));
                    break;
                case "uranus":
                    content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.uranusitin)));
                    break;
                case "neptune":
                    content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.neptuneitin)));
                    break;
            }

        for (int i = 0; i < content.size(); i++) {
            AppCompatTextView textView = new AppCompatTextView(this);

            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(32,32,32,32);
            textView.setLayoutParams(params);
            if(i == 0 || i ==1){
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(40f);
            } else if ( i == content.size() - 1) {
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(20f);
            }
            else if(i ==2){
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(16f);
            }else {
                textView.setTextSize(20f);
            }
            textView.setTextColor(ContextCompat.getColor(this, R.color.white));
            textView.setText(content.get(i));

            binding.linearContentHolder.addView(textView);
        }


        // Set the video source and start playing
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.stars);
        binding.backgroundVideoView.setVideoURI(videoUri);

        // Add an OnPreparedListener to resume video playback when it's ready
        binding.backgroundVideoView.setOnPreparedListener(mp -> {
            // Restore the video position
            binding.backgroundVideoView.seekTo(currentPosition);
            binding.backgroundVideoView.start();
        });

        // Add an OnCompletionListener to loop the video when it ends
        binding.backgroundVideoView.setOnCompletionListener(mp -> {
            // Rewind the video to the beginning and start it again
            binding.backgroundVideoView.seekTo(0);
            binding.backgroundVideoView.start();
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (binding.backgroundVideoView.isPlaying()) {
            binding.backgroundVideoView.pause();
            currentPosition = binding.backgroundVideoView.getCurrentPosition();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!binding.backgroundVideoView.isPlaying()) {
            binding.backgroundVideoView.seekTo(currentPosition);
            binding.backgroundVideoView.start();
        }
    }
}

