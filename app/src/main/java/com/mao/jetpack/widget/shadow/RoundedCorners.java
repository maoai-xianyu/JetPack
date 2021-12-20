package com.mao.jetpack.widget.shadow;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class RoundedCorners extends BitmapTransformation {
    private static final String ID = "com.bumptech.glide.load.resource.bitmap.RoundedCorners";
    private final int roundingRadius;

    public RoundedCorners(Context context,int roundingRadius) {
        super(context);
        Preconditions.checkArgument(roundingRadius > 0, "roundingRadius must be greater than 0.");
        this.roundingRadius = roundingRadius;
    }

    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.roundedCorners(pool, toTransform, this.roundingRadius);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RoundedCorners) {
            RoundedCorners other = (RoundedCorners)o;
            return this.roundingRadius == other.roundingRadius;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Util.hashCode("com.bumptech.glide.load.resource.bitmap.RoundedCorners".hashCode(), Util.hashCode(this.roundingRadius));
    }

    @Override
    public String getId() {
        return ID;
    }
}