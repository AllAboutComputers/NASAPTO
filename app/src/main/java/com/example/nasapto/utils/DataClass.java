package com.example.nasapto.utils;

import android.content.Context;

import com.example.nasapto.R;
import com.example.nasapto.pojo.SolarSystemItem;

import java.util.ArrayList;

public class DataClass {
    public static final String INTENT_KEY_TITLE = "title";

    public static ArrayList<SolarSystemItem> getSolarSystemItems(Context context) {
        int[] images = new int[]
                {R.drawable.sun2, R.drawable.mercury2, R.drawable.venus2, R.drawable.earth, R.drawable.moonsurface, R.drawable.mars, R.drawable.jupiter, R.drawable.saturn, R.drawable.uranus, R.drawable.neptune};
        String[] planets = context.getResources().getStringArray(R.array.planets);
        ArrayList<SolarSystemItem> items = new ArrayList<>();
        SolarSystemItem sun = new SolarSystemItem();
        sun.imageBg = images[0];
        sun.title = context.getString(R.string.star_sun);
        items.add(sun);
        for (int i = 0; i < planets.length; i++) {
            SolarSystemItem item = new SolarSystemItem();
            item.imageBg = images[i + 1];
            item.title = planets[i];


            items.add(item);
        }
        return items;
    }
}
