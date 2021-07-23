package com.ggh.baselibrary.ext

import com.ggh.baselibrary.BaseApp
import com.ggh.baselibrary.utils.ToastUtil

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：Toast
 * 创建时间：2020/9/2
 * 功能描述：
 */

fun String.toast() = ToastUtil.show(this)
fun Int.toast() = ToastUtil.show(BaseApp.instance.resources.getText(this).toString())
