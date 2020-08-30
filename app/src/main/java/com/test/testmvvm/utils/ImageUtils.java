package com.test.testmvvm.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtils {
    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
//        Bitmap bitmap = BitmapFactory.decodeFile(url, options);
//        OutputStream out = null;
//        try {
//            out = new FileOutputStream(file.getPath());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Bitmap temp = bitmap;
//        boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG,80,out);
//        if(compress){
            return BitmapFactory.decodeFile(url, options);
//        }else{
//            return temp;
//        }

    }
}
