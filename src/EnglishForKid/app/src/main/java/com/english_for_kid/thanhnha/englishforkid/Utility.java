package com.english_for_kid.thanhnha.englishforkid;

/**
 * Created by Thanh Nha on 3/2/2015.
 */

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class Utility {
    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        bitmap = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
