package com.mao.jetpack.widget.shadowother;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.mao.jetpack.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoDrawable;
import com.squareup.picasso.PicassoDrawableTarget;
import com.squareup.picasso.Transformation;

/**
 * @author zhangkun
 * @time 2021/11/30 15:36
 * @Description
 */
class PicassoUtils {


    public static void setRoundCorner(final View view, final Drawable resourceId, final float cornerDipValue,
                                      final String currentTag) {

        if (cornerDipValue == 0) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                           int oldRight, int oldBottom) {
                    view.removeOnLayoutChangeListener(this);
                    Picasso.with(view.getContext())
                            .load(resourceId)
                            .centerCrop()
                            .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                            .into(new PicassoDrawableTarget(){
                                @Override
                                public void onResourceReady(PicassoDrawable picassoDrawable, Picasso.LoadedFrom from) {
                                    super.onResourceReady(picassoDrawable, from);
                                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                        view.setBackgroundDrawable(picassoDrawable);
                                    } else {
                                        view.setBackground(picassoDrawable);
                                    }
                                }
                            });
                }
            });

            if (view.getMeasuredWidth() != 0 || view.getMeasuredHeight() != 0) {

                Picasso.with(view.getContext())
                        .load(resourceId)
                        .centerCrop()
                        .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                        .into(new PicassoDrawableTarget(){
                            @Override
                            public void onResourceReady(PicassoDrawable picassoDrawable, Picasso.LoadedFrom from) {
                                super.onResourceReady(picassoDrawable, from);
                                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                    view.setBackgroundDrawable(picassoDrawable);
                                } else {
                                    view.setBackground(picassoDrawable);
                                }
                            }
                        });
            }

        } else {

            Transformation transformation = new RoundedTransformationBuilder()
                    .scaleType(ImageView.ScaleType.CENTER_CROP)
                    .borderColor(Color.TRANSPARENT)
                    .borderWidthDp(0)
                    .cornerRadius(cornerDipValue)
                    .oval(false)
                    .build();

            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                           int oldRight, int oldBottom) {
                    view.removeOnLayoutChangeListener(this);

                    Picasso.with(view.getContext())
                            .load(resourceId)
                            .transform(transformation)
                            .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                            .into(new PicassoDrawableTarget(){
                                @Override
                                public void onResourceReady(PicassoDrawable picassoDrawable, Picasso.LoadedFrom from) {
                                    super.onResourceReady(picassoDrawable, from);
                                    String lastTag = (String) view.getTag(R.id.action_container);
                                    if (lastTag.equals(currentTag)) {
                                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                            view.setBackgroundDrawable(picassoDrawable);
                                        } else {
                                            view.setBackground(picassoDrawable);
                                        }
                                    }
                                }
                            });
                }
            });

            if (view.getMeasuredWidth() != 0 || view.getMeasuredHeight() != 0) {

                Picasso.with(view.getContext())
                        .load(resourceId)
                        .transform(transformation)
                        .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                        .into(new PicassoDrawableTarget() {
                            @Override
                            public void onResourceReady(PicassoDrawable picassoDrawable, Picasso.LoadedFrom from) {
                                super.onResourceReady(picassoDrawable, from);
                                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                    view.setBackgroundDrawable(picassoDrawable);
                                } else {
                                    view.setBackground(picassoDrawable);
                                }
                            }
                        });
            }

        }

    }


    public static void setCorners(final View view, final Drawable resourceId, final float leftTop_corner,
                                  final float leftBottom_corner, final float rightTop_corner, final float rightBottom_corner, final String currentTag) {
        if (leftTop_corner == 0 && leftBottom_corner == 0 && rightTop_corner == 0 && rightBottom_corner == 0) {

            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                           int oldRight, int oldBottom) {
                    view.removeOnLayoutChangeListener(this);

                    Picasso.with(view.getContext())
                            .load(resourceId)
                            .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                            .into(new PicassoDrawableTarget(){
                                @Override
                                public void onResourceReady(PicassoDrawable resource, Picasso.LoadedFrom from) {
                                    super.onResourceReady(resource, from);
                                    String lastTag = (String) view.getTag(R.id.action_container);
                                    if (lastTag.equals(currentTag)) {
                                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                            view.setBackgroundDrawable(resource);
                                        } else {
                                            view.setBackground(resource);
                                        }
                                    }
                                }
                            });
                }
            });

            if (view.getMeasuredWidth() != 0 || view.getMeasuredHeight() != 0) {

                Picasso.with(view.getContext())
                        .load(resourceId)
                        .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                        .into(new PicassoDrawableTarget(){
                            @Override
                            public void onResourceReady(PicassoDrawable resource, Picasso.LoadedFrom from) {
                                super.onResourceReady(resource, from);
                                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                    view.setBackgroundDrawable(resource);
                                } else {
                                    view.setBackground(resource);
                                }
                            }
                        });


            }

        } else {

            /**
             * 注意：
             * 有特殊角，长宽不为0的状况(实际上也存在长宽不为0但还未渲染到画面上)
             * */

            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.TRANSPARENT)
                    .borderWidthDp(0)
                    .cornerRadiusEachAngles(leftTop_corner,leftBottom_corner,rightTop_corner,rightBottom_corner)
                    .oval(false)
                    .build();


            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                           int oldRight, int oldBottom) {
                    view.removeOnLayoutChangeListener(this);

                    Picasso.with(view.getContext())
                            .load(resourceId)
                            .transform(transformation)
                            .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                            .into(new PicassoDrawableTarget(){
                                @Override
                                public void onResourceReady(PicassoDrawable resource, Picasso.LoadedFrom from) {
                                    super.onResourceReady(resource, from);
                                    String lastTag = (String) view.getTag(R.id.action_container);
                                    if (lastTag.equals(currentTag)) {
                                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                            view.setBackgroundDrawable(resource);
                                        } else {
                                            view.setBackground(resource);
                                        }
                                    }
                                }
                            });
                }
            });

            if (view.getMeasuredWidth() != 0 || view.getMeasuredHeight() != 0) {
                Picasso.with(view.getContext())
                        .load(resourceId)
                        .transform(transformation)
                        .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                        .into(new PicassoDrawableTarget(){
                            @Override
                            public void onResourceReady(PicassoDrawable resource, Picasso.LoadedFrom from) {
                                super.onResourceReady(resource, from);
                                String lastTag = (String) view.getTag(R.id.action_container);
                                if (lastTag.equals(currentTag)) {
                                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                        view.setBackgroundDrawable(resource);
                                    } else {
                                        view.setBackground(resource);
                                    }
                                }
                            }
                        });
            }
        }

    }

}
