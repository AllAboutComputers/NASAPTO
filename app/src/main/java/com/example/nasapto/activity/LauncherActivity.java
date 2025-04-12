package com.example.nasapto.activity;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.nasapto.R;
import com.example.nasapto.adapter.ItemClickedListener;
import com.example.nasapto.adapter.SolarSystemAdapter;
import com.example.nasapto.databinding.ActivityLauncherBinding;
import com.example.nasapto.pojo.SolarSystemItem;
import com.example.nasapto.utils.DataClass;

public class LauncherActivity extends BaseActivity {
    private ActivityLauncherBinding binding;

    @Override
    View getContentView() {
        binding = ActivityLauncherBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    void init() {
        binding.textViewGreetings.setText(getString(R.string.greetings, getString(R.string.our_solar_system)));

        binding.recyclerViewSolarSystem.setLayoutManager(new LinearLayoutManager(this));
        SolarSystemAdapter solarSystemAdapter = new SolarSystemAdapter(DataClass.getSolarSystemItems(this), new ItemClickedListener() {
            @Override
            public void itemClicked(SolarSystemItem item) {
                openActivity(item.title);
            }

            @Override
            public void itemClickedInternal(SolarSystemItem item) {
                openActivity(item.title2);
            }
        });
        binding.recyclerViewSolarSystem.setAdapter(solarSystemAdapter);
    }

    private void openActivity(String title) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(DataClass.INTENT_KEY_TITLE, title);
        startActivity(intent);
    }
}
