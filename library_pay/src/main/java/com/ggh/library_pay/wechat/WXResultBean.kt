package com.ggh.library_pay.wechat

import com.google.gson.annotations.SerializedName

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：WXResultBean
 * 创建时间：2020/7/17
 * 功能描述：  微信支付回调数据
 */

/**
 * appid : wx2f5332ffbb643be4
 * partnerid : 1502151531
 * prepayid : wx1717381566267048ff7e2e831713876800
 * package : Sign=WXPay
 * noncestr : czZbipVryNyhX3CLcEHVMCrOv1zs0KhC
 * timestamp : 1594978687
 * sign : 37076327A59112F7BD812893DF116039
 */
data class WXResultBean(
    var appid: String? = null,
    var partnerid: String? = null,
    var prepayid: String? = null,
    @SerializedName("package")
    var packageX: String? = null,
    var noncestr: String? = null,
    var timestamp: Int = 0,
    var sign: String? = null
)