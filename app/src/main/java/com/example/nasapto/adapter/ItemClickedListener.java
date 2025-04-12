package com.example.nasapto.adapter;

import com.example.nasapto.pojo.SolarSystemItem;

public interface ItemClickedListener {
    void itemClicked(SolarSystemItem item);

    void itemClickedInternal(SolarSystemItem item);
}
