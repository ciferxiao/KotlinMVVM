package com.ggh.baselibrary.utils

import android.content.res.Resources
import com.ggh.baselibrary.BaseApp

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：WordUtil
 * 创建时间：2020/6/27
 * 功能描述：获取资源文件String.xml文件中的内容
 */
object WordUtil {
    private lateinit var sResources: Resources
    fun getString(res: Int): String {
        return sResources.getString(res)
    }

    init {
        sResources = BaseApp.instance.resources
    }
}