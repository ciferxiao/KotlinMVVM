package com.ggh.baselibrary.ext

import com.ggh.baselibrary.BaseApp

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 创建时间：2020/11/4
 * 功能描述：
 */

//获取颜色
fun Int.getColor() : Int = BaseApp.instance.resources.getColor(this)