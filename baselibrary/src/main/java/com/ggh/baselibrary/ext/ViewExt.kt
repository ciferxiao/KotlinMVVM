package com.ggh.baselibrary.ext

import android.view.View

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 创建时间：2020/10/24
 * 功能描述：
 */

private var lastClickTime: Long = 0
//防止快速点击造成打开多个界面   只允许在 1秒内只能点击一次  single(2000){}   可自定义时间
fun <T : View> T.singleClick(time: Int = 500, block: (T) -> Unit) {
    this.setOnClickListener {
        val curClickTime = System.currentTimeMillis()
        if (curClickTime - lastClickTime >= time) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime
            block(it as T)
        }
    }
}

