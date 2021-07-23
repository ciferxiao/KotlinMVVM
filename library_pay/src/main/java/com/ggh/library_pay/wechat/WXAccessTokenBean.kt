package com.ggh.library_pay.wechat

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：WXAccessTokenBean
 * 创建时间：2020/10/7
 * 功能描述：获取微信授权信息
 */
data class WXAccessTokenBean(
    val access_token:String,
    val expires_in:Int,
    val refresh_token:String,
    val openid:String,
    val scope:String,
    val unionid:String
    )