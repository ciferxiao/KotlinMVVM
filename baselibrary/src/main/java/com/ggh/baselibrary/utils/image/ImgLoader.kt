package com.ggh.baselibrary.utils.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ggh.baselibrary.BaseApp
import com.ggh.baselibrary.R

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：ImgLoader
 * 创建时间：2020/8/10
 * 功能描述：
 */
object ImgLoader {
     val sManager : RequestManager

    init {
        //你想静态化的东西,外面不要有函数
        sManager = Glide.with(BaseApp.instance)
    }

    //加载图片    路径与控件不能为空
    open fun display(url: String, imageView: ImageView) {
        displayWithError(url, imageView, R.mipmap.loading_error)
    }

    open fun display(url: Int, imageView: ImageView) {
        sManager.load(url)
            .placeholder(R.drawable.bg_placeholder)
            .error(R.mipmap.loading_error)
            .diskCacheStrategy(
                DiskCacheStrategy.DATA
            ).into(imageView)
    }

    //加载  加载有错误显示图片
    fun displayWithError(url: String, imageView: ImageView, errorRes: Int) {
        sManager.load(url)
            .placeholder(R.drawable.bg_placeholder)
            .error(errorRes)
            .diskCacheStrategy(
                DiskCacheStrategy.DATA
            ).into(imageView)
    }

    //加载圆形图片
    fun displayCircle(url: String, imageView: ImageView) {
        sManager.load(url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .error(R.mipmap.loading_error)
            .transform(GlideCircleTransform()) //设置为圆角
            .into(imageView)
    }
}