package com.mao.jetpack.widget.shadow;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mao.jetpack.R;


/**
 * Created by leo
 * on 2020/8/3.
 */
class GlideRoundUtils {

    public static void setRoundCorner(final View view, final Drawable resourceId, final float cornerDipValue,
        final String currentTag) {

        if (cornerDipValue == 0) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                    int oldRight, int oldBottom) {
                    view.removeOnLayoutChangeListener(this);
                    Glide.with(view.getContext())
                        .load(resourceId)
                        .transform(new CenterCrop(view.getContext()))
                        .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                    view.setBackgroundDrawable(resource);
                                } else {
                                    view.setBackground(resource);
                                }
                            }
                        });
                }
            });

            if (view.getMeasuredWidth() != 0 || view.getMeasuredHeight() != 0) {
                Glide.with(view.getContext())
                    .load(resourceId)
                    .transform(new CenterCrop(view.getContext()))
                    .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                            GlideAnimation<? super GlideDrawable> glideAnimation) {
                            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                view.setBackgroundDrawable(resource);
                            } else {
                                view.setBackground(resource);
                            }
                        }
                    });
            }

        } else {

            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                    int oldRight, int oldBottom) {
                    view.removeOnLayoutChangeListener(this);
                    Glide.with(view.getContext())
                        .load(resourceId)
                        .transform(new CenterCrop(view.getContext()), new RoundedCorners(view.getContext(), (int) cornerDipValue))
                        .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                GlideAnimation<? super GlideDrawable> glideAnimation) {
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
                Glide.with(view.getContext())
                    .load(resourceId)
                    .transform(new CenterCrop(view.getContext()), new RoundedCorners(view.getContext(), (int) cornerDipValue))
                    .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                            GlideAnimation<? super GlideDrawable> glideAnimation) {
                            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                                view.setBackgroundDrawable(resource);
                            } else {
                                view.setBackground(resource);
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
                    Glide.with(view.getContext())
                        .load(resourceId)
                        .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                GlideAnimation<? super GlideDrawable> glideAnimation) {
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
                Glide.with(view.getContext())
                    .load(resourceId)
                    .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                            GlideAnimation<? super GlideDrawable> glideAnimation) {
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
            final GlideRoundTransform transform = new GlideRoundTransform(view.getContext(), leftTop_corner, leftBottom_corner,
                rightTop_corner, rightBottom_corner);
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                    int oldRight, int oldBottom) {
                    view.removeOnLayoutChangeListener(this);
//                        GlideRoundTransform transform = new GlideRoundTransform(view.getContext(), leftTop_corner, leftBottom_corner, rightTop_corner, rightBottom_corner);
                    Glide.with(view.getContext())
                        .load(resourceId)
                        .bitmapTransform(transform)
                        .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                GlideAnimation<? super GlideDrawable> glideAnimation) {
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
                Glide.with(view.getContext())
                    .load(resourceId)
                    .bitmapTransform(transform)
                    .override(view.getMeasuredWidth(), view.getMeasuredHeight())
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                            GlideAnimation<? super GlideDrawable> glideAnimation) {
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
