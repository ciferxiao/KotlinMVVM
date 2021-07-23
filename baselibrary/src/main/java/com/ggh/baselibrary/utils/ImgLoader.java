package com.ggh.baselibrary.utils;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.ggh.baselibrary.BaseApp;
import com.ggh.baselibrary.R;
import com.ggh.baselibrary.utils.image.GlideCircleTransform;

import java.io.File;


public class ImgLoader {
    private static RequestManager sManager;

    static {
        sManager = Glide.with(BaseApp.instance);
    }

    public static void displayCircle(String url, ImageView imageView) {
        sManager.load(url).diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.mipmap.loading_error)
                .transform(new GlideCircleTransform())//设置为圆角
                .into(imageView);
    }
    public static void display(String url, ImageView imageView) {
        displayWithError(url,imageView,R.mipmap.loading_error);
    }

    public static void displayWithError(String url, ImageView imageView, int errorRes) {
        sManager.load(url).error(errorRes).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
    }

    public static void display(File file, ImageView imageView) {
        sManager.load(file).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
    }

    public static void display(int res, ImageView imageView) {
        sManager.load(res).into(imageView);
    }

    /**
     * 显示视频封面缩略图
     */
    public static void displayVideoThumb(String videoPath, ImageView imageView) {
        sManager.load(Uri.fromFile(new File(videoPath))).diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().into(imageView);
    }

    public static void displayVideoThumbNet(String videoPath, ImageView imageView) {
        sManager.load(videoPath).diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().into(imageView);
    }

    public static void displayDrawable(String url, final DrawableCallback callback) {
        sManager.load(url).diskCacheStrategy(DiskCacheStrategy.DATA)
                .transform(new GlideCircleTransform())//设置为圆角
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (callback != null) {
                            callback.callback(resource);
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    public static void displayDrawable(int url, final DrawableCallback callback){
        sManager.load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                if (callback != null) {
                    callback.callback(resource);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
    }

    public static void displayDrawable(File file, final DrawableCallback callback) {
        sManager.load(file).diskCacheStrategy(DiskCacheStrategy.DATA).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                if (callback != null) {
                    callback.callback(resource);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
    }


    public static void display(String url, ImageView imageView, int placeholderRes) {
        sManager.load(url).diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(placeholderRes).into(imageView);
    }

    public static void displayGif(String url,ImageView imageView){
        sManager.load(url).listener(new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                if (resource instanceof GifDrawable) {
                    //加载一次
                    ((GifDrawable)resource).setLoopCount(1);
                }
                return false;
            }
        }).into(imageView);
    }


    public interface DrawableCallback {
        void callback(Drawable drawable);
    }


}
