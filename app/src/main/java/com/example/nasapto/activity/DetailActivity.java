package com.example.nasapto.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.nasapto.R;
import com.example.nasapto.databinding.ActivityDetailBinding;
import com.example.nasapto.utils.DataClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class DetailActivity extends BaseActivity {
    private ActivityDetailBinding binding;
    private int currentPosition = 0;


    @Override
    View getContentView() {
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        return binding
                .getRoot();
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
        switch (title.toLowerCase(Locale.ENGLISH)){
            case "sun":
                content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.sun)));
                binding.buttonItinerary.setBackgroundResource(R.drawable.rounded_rectangle_button);
                binding.buttonItinerary.setText("Click Here To Create Your Itinerary");
                break;
            case "mercury":
                content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.mercury)));
                binding.buttonItinerary.setBackgroundResource(R.drawable.mercuryiti);
                binding.buttonItinerary.setText("Click Here To Create Your Itinerary");
                break;
            case "venus":
                content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.venus)));
                binding.buttonItinerary.setBackgroundResource(R.drawable.venusiti);
                binding.buttonItinerary.setText("Click Here To Create Your Itinerary");
                break;
            case "earth":
                content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.earth)));
                binding.buttonItinerary.setBackgroundResource(R.drawable.earthiti);
                binding.buttonItinerary.setText("Click Here To Create Your Itinerary");
                break;
            case "moon":
                content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.moon)));
                binding.buttonItinerary.setBackgroundResource(R.drawable.mooniti);
                binding.buttonItinerary.setText("Click Here To Create Your Itinerary");
                break;
            case "mars":
                content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.mars)));
                binding.buttonItinerary.setBackgroundResource(R.drawable.marsiti);
                binding.buttonItinerary.setText("Click Here To Create Your Itinerary");
                break;
            case "jupiter":
                content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jupiter)));
                binding.buttonItinerary.setBackgroundResource(R.drawable.jupiteriti);
                binding.buttonItinerary.setText("Click Here To Create Your Itinerary");
                break;
            case "saturn":
                content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.saturn)));
                binding.buttonItinerary.setBackgroundResource(R.drawable.saturniti);
                binding.buttonItinerary.setText("Click Here To Create Your Itinerary");
                break;
            case "uranus":
                content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.uranus)));
                binding.buttonItinerary.setBackgroundResource(R.drawable.uranusiti);
                binding.buttonItinerary.setText("Click Here To Create Your Itinerary");
                break;
            case "neptune":
                content = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.neptune)));
                binding.buttonItinerary.setBackgroundResource(R.drawable.neptuneiti);
                binding.buttonItinerary.setText("Click Here To Create Your Itinerary");
                break;
        }

        binding.buttonItinerary.setOnClickListener(view -> {
            Intent intent = new Intent(this, ItineraryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(DataClass.INTENT_KEY_TITLE, title);
            startActivity(intent);
        });

        for (int i = 0; i < content.size(); i++) {
            AppCompatTextView textView = new AppCompatTextView(this);

            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(32,32,32,32);
            textView.setLayoutParams(params);

            if(i == 0 || i ==1 || i == content.size() - 1){
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(30f);
            }else if(i ==2){
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(16f);
            }else{
                textView.setTextSize(14f);
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
