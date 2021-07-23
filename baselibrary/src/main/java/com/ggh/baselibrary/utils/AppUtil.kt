package com.ggh.baselibrary.utils

import androidx.fragment.app.FragmentManager
import com.ggh.baselibrary.BaseApp

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 创建时间：2020/11/7
 * 功能描述：
 */
object AppUtil {
    /** 判断是否是快速点击  */
    var lastClickTime: Long = 0
    // 记录点击返回时第一次的时间毫秒值
    var firstTime: Long = 0

    /**
     * 关闭App
     */
    fun closeApp(manager: FragmentManager) {
        if (manager.backStackEntryCount != 0) {
            manager.popBackStack()
        } else {
            exitApp(2000) // 退出应用
        }
    }

    /**
     * 退出应用
     *
     * @param timeInterval 设置第二次点击退出的时间间隔
     */
    private fun exitApp(timeInterval: Long) {
        // 第一次肯定会进入到if判断里面，然后把firstTime重新赋值当前的系统时间
        // 然后点击第二次的时候，当点击间隔时间小于2s，那么退出应用；反之不退出应用
        if (System.currentTimeMillis() - firstTime >= timeInterval) {
            firstTime = System.currentTimeMillis()
        } else {
            ViewManager.getInstance().exitApp(BaseApp.instance)
        }
    }
}