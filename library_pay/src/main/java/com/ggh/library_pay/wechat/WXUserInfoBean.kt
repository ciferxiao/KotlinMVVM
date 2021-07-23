package com.ggh.library_pay.wechat

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：WXUserInfoBean
 * 创建时间：2020/10/7
 * 功能描述：  微信用户信息
 */
data class WXUserInfoBean(
    var openid: String? = null,
    var nickname: String? = null,
    var sex : Int = 0,
    var province: String? = null,
    var city: String? = null,
    var country: String? = null,
    var headimgurl: String? = null,
    var unionid: String? = null,
    var privilege: List<String>? = null
)