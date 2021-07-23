package com.ggh.library_pay.wechat

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：WXLoginCallBack
 * 创建时间：2020/10/7
 * 功能描述： 微信登录回调，获取用户信息成功后走后台登录接口
 */
interface WXLoginCallBack {
    fun loginSuccess(userInfoBean: WXUserInfoBean)
    fun loginError(msg :String)
}