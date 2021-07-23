package com.ggh.baselibrary.mvvm

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 文件名：ResponseData.kt
 * 创建时间：2020/10/16
 * 功能描述：根据项目返回数据而定
 */
data class ResponseData<out T>(val code: Int, val msg: String, val data: T)