package com.mao.jetpack.ui.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.mao.jetpack.R;
import com.mao.jetpack.databinding.ActivityImageBinding;

/**
 * @author zhangkun
 * @time 2022/4/28 15:41
 * @Description
 */
public class ImageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityImageBinding binding = ActivityImageBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.image_girl);

        binding.imageClip.setImageBitmap(getRoundedCornerBitmap(bmp, 40, 40));
        binding.imageClip2.setImageBitmap(getRoundedCornerBitmap(bmp, 40, 40));

        Glide.with(this)
                .load("https://wx4.sinaimg.cn/mw2000/7d89f3d7ly1h1gaugjav4j20nh0tcq65.jpg")
                .asBitmap()
                .centerCrop()
                .override(100, 100)
                .into(new ImageViewTarget<Bitmap>(binding.imagePic) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        binding.imagePic.setImageBitmap(getRoundedCornerBitmap(resource, 10, 10));

                    }
                });

        //binding.tvDest.setTextEllipsize("《奇迹·笨小孩笨小孩笨小孩笨小孩》专项",2,"》");
        //binding.tvDestMY.setText("《奇迹笨小孩love-lovelove》专项");
        binding.tvDestMY.setVisibility(View.INVISIBLE);

        //binding.tvDestMY2.setTextEllipsize("《奇迹·笨小孩笨小孩孩笨小孩孩》专项", "》", 1, "...");
        binding.tvDestMY3.setTextEllipsize("《独行月球》"+"测试热", "》", 1, "...");
    }


    public Bitmap getRoundedCornerBitmap(Bitmap bitmap, int roundPx, int circlePx) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        // 锯齿
        paint.setAntiAlias(true);
        // 画布颜色
        canvas.drawARGB(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);

        //
        Rect block = new Rect(roundPx, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRect(block, paint);
        RectF rectF = new RectF(0, 0, roundPx * 2, bitmap.getHeight());
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        // 把 bitmap 绘制在 画布上
        canvas.drawBitmap(bitmap, rect, rect, paint);

        // 画笔透明
        paint.setColor(Color.TRANSPARENT);
        // 拿掉画布右上角和右下角的 1/4 圆
        canvas.drawCircle(bitmap.getWidth(), 0, circlePx, paint);
        canvas.drawCircle(bitmap.getWidth(), bitmap.getHeight(), circlePx, paint);
        return output;

    }

    public void clip() {
    }


}
