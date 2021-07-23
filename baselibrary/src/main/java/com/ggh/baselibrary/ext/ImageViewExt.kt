package com.ggh.baselibrary.ext

import android.widget.ImageView
import com.ggh.baselibrary.utils.ImgLoader

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 创建时间：2020/11/7
 * 功能描述：
 */

//ImgLoader
fun String.loader(imageView: ImageView) = ImgLoader.display(this,imageView)
fun Int.loader(imageView: ImageView) = ImgLoader.display(this,imageView)