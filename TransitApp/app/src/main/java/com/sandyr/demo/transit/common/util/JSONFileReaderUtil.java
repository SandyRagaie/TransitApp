package com.sandyr.demo.transit.common.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Sandy on 1/31/2018.
 */

public class JSONFileReaderUtil {

    private static JSONFileReaderUtil ourInstance;

    public static JSONFileReaderUtil getInstance() {
        if(ourInstance == null){
            ourInstance = new JSONFileReaderUtil();
        }
        return ourInstance;
    }

    private JSONFileReaderUtil() {
    }
    public String readFileAsString(Context context){
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
            return json;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

