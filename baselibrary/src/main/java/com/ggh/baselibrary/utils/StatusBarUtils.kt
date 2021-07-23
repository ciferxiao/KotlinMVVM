package com.ggh.baselibrary.utils

import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.view.View

/**
 * @Author Create by mcl
 * @Date 2020/5/11
 * @ClassName StatusBarUtils
 * @描述   状态栏工具
 *
 * setStatusBarTransparent   状态栏设置透明
 * setStateBarTextColor     设置状态栏字体颜色  亮色 或者 暗色
 */
object StatusBarUtils {

    /**
     * 状态栏设置透明
     * @param activity
     */
    fun setStatusBarTransparent(activity: Activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = activity.window.decorView
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            activity.window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 设置状态栏字体颜色  亮色 或者 暗色
     * @param activity 当前 Activity
     * @param dark true = 黑色   false = 白色
     */
    fun setStateBarTextColor(activity: Activity, dark: Boolean) {
        val decor = activity.window.decorView
        when(dark){
            true -> decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            false-> decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }

    }

}