package com.mao.jetpack.widget.shadow;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Build;
import android.os.Build.VERSION;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangkun
 * @time 2021/10/19 12:53
 * @Description
 */
class TransformationUtils {

    private static final Lock BITMAP_DRAWABLE_LOCK;
    private static final Set<String> MODELS_REQUIRING_BITMAP_LOCK = new HashSet<>(Arrays
        .asList("XT1085", "XT1092", "XT1093", "XT1094", "XT1095", "XT1096", "XT1097", "XT1098", "XT1031", "XT1028", "XT937C", "XT1032", "XT1008", "XT1033", "XT1035", "XT1034", "XT939G", "XT1039", "XT1040", "XT1042", "XT1045", "XT1063", "XT1064", "XT1068", "XT1069", "XT1072", "XT1077", "XT1078", "XT1079"));


    static {
        BITMAP_DRAWABLE_LOCK = (Lock)(MODELS_REQUIRING_BITMAP_LOCK.contains(Build.MODEL) ? new ReentrantLock() : new NoLock());
    }

    public static Bitmap roundedCorners(@NonNull BitmapPool pool, @NonNull Bitmap inBitmap, int roundingRadius) {
        Preconditions.checkArgument(roundingRadius > 0, "roundingRadius must be greater than 0.");
        Config safeConfig = getAlphaSafeConfig(inBitmap);
        Bitmap toTransform = getAlphaSafeBitmap(pool, inBitmap);
        Bitmap result = pool.get(toTransform.getWidth(), toTransform.getHeight(), safeConfig);
        result.setHasAlpha(true);
        BitmapShader shader = new BitmapShader(toTransform, TileMode.CLAMP, TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        RectF rect = new RectF(0.0F, 0.0F, (float)result.getWidth(), (float)result.getHeight());
        BITMAP_DRAWABLE_LOCK.lock();

        try {
            Canvas canvas = new Canvas(result);
            canvas.drawColor(0, Mode.CLEAR);
            canvas.drawRoundRect(rect, (float)roundingRadius, (float)roundingRadius, paint);
            clear(canvas);
        } finally {
            BITMAP_DRAWABLE_LOCK.unlock();
        }

        if (!toTransform.equals(inBitmap)) {
            pool.put(toTransform);
        }

        return result;
    }

    @NonNull
    private static Config getAlphaSafeConfig(@NonNull Bitmap inBitmap) {
        return VERSION.SDK_INT >= 26 && Config.RGBA_F16.equals(inBitmap.getConfig()) ? Config.RGBA_F16 : Config.ARGB_8888;
    }

    private static Bitmap getAlphaSafeBitmap(@NonNull BitmapPool pool, @NonNull Bitmap maybeAlphaSafe) {
        Config safeConfig = getAlphaSafeConfig(maybeAlphaSafe);
        if (safeConfig.equals(maybeAlphaSafe.getConfig())) {
            return maybeAlphaSafe;
        } else {
            Bitmap argbBitmap = pool.get(maybeAlphaSafe.getWidth(), maybeAlphaSafe.getHeight(), safeConfig);
            (new Canvas(argbBitmap)).drawBitmap(maybeAlphaSafe, 0.0F, 0.0F, (Paint)null);
            return argbBitmap;
        }
    }

    private static void clear(Canvas canvas) {
        canvas.setBitmap((Bitmap)null);
    }


    private static final class NoLock implements Lock {
        NoLock() {
        }

        public void lock() {
        }

        public void lockInterruptibly() throws InterruptedException {
        }

        public boolean tryLock() {
            return true;
        }

        public boolean tryLock(long time, @NonNull TimeUnit unit) throws InterruptedException {
            return true;
        }

        public void unlock() {
        }

        @NonNull
        public Condition newCondition() {
            throw new UnsupportedOperationException("Should not be called");
        }
    }


}
