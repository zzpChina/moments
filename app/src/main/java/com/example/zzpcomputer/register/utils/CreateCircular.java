package com.example.zzpcomputer.register.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 *  作者：MG_ZXC
 *     来源：CSDN
 *     原文：https://blog.csdn.net/chaoshenzhaoxichao/article/details/79699282
 *     版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class CreateCircular {
    public static Bitmap createCircleImage(Bitmap source) {
        int length = source.getWidth() < source.getHeight() ? source.getWidth() : source.getHeight();
        Paint paint = new Paint(); paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(length, length, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target); canvas.drawCircle(length / 2, length / 2, length / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); canvas.drawBitmap(source, 0, 0, paint); return target; }
}
