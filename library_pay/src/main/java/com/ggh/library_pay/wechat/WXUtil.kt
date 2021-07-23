package com.ggh.library_pay.wechat

import android.content.Context
import android.util.Log
import com.alibaba.fastjson.JSON
import com.ggh.baselibrary.BaseApp
import com.ggh.baselibrary.Config
import com.ggh.baselibrary.utils.LogUtil
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：WXUtil
 * 功能描述：   微信工具类
 */
object WXUtil {
    /**
     * 全局变量
     */
    var mIWXAPI: IWXAPI
    var mContext: Context
    var appId = Config.WX_APP_ID
    var appSecret = Config.WX_APP_SECRET
    const val SDK_PAY_WX = 2
    lateinit var wxLoginCallBack :WXLoginCallBack

    init {
        mContext = BaseApp.instance
        mIWXAPI =
            WXAPIFactory.createWXAPI(mContext, appId, false)
        mIWXAPI.registerApp(appId)
        LogUtil.d(appId)
    }

    /**
     * 微信支付
     *
     * @param info 支付信息
     */
    fun payWX(info: String) {
        if (info == null || info.isEmpty()) return
        if (mIWXAPI == null) {
            mIWXAPI =
                WXAPIFactory.createWXAPI(mContext, appId, false)
            mIWXAPI.registerApp(appId)
        }
        val resultBean =
            JSON.parseObject(info, WXResultBean::class.java)
        LogUtil.d("支付信息=" + JSON.toJSONString(resultBean))
        val request = PayReq()
        request.appId = resultBean.appid
        request.partnerId = resultBean.partnerid
        request.prepayId = resultBean.prepayid
        request.packageValue = resultBean.packageX
        request.nonceStr = resultBean.noncestr
        request.timeStamp = resultBean.timestamp.toString()
        request.sign = resultBean.sign
        mIWXAPI.sendReq(request)
    }

    /**
     * 登录微信请求    调起微信授权界面
     */
    fun loginWX() {
        val req = SendAuth.Req()
        req.scope = "snsapi_userinfo"
        req.state = "sharecommunity_weixin_login"
        mIWXAPI.sendReq(req)
    }


    /**
     * 获取微信登录信息
     */
    public fun getWxInfo(code:String,loginCallBack: WXLoginCallBack){
        wxLoginCallBack = loginCallBack
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weixin.qq.com")
            //.addConverterFactory(GsonConverterFactory.create())
            .build()
        val httpWxService = retrofit.create(HttpWxService::class.java)
        val wxAccessTokenBeanCall: Call<WXAccessTokenBean> = httpWxService.getCook(appId, appSecret, code, "authorization_code")
        wxAccessTokenBeanCall.enqueue(object : Callback<WXAccessTokenBean> {
            override fun onResponse(
                call: Call<WXAccessTokenBean>,
                response: Response<WXAccessTokenBean>
            ) {
                Log.d("得到发送的数据", response.code().toString() + "")

                val tokenBean = response.body()
//                tokenBean!!.access_token.d()
//                tokenBean.openid.d()
                getUserInfo(tokenBean!!.access_token, tokenBean.openid)
            }

            override fun onFailure(call: Call<WXAccessTokenBean>, t: Throwable) {
                wxLoginCallBack.loginError(t.toString())
            }
        })
    }


    /**
     * 获取微信个人信息
     */
    fun getUserInfo(access_token: String, openid: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weixin.qq.com")
            //.addConverterFactory(GsonConverterFactory.create())
            .build()
        val httpWxService = retrofit.create(HttpWxService::class.java)
        val wxUserInfoBeanCall: Call<WXUserInfoBean> =
            httpWxService.getUserInfo(access_token, openid)
        wxUserInfoBeanCall.enqueue(object : Callback<WXUserInfoBean> {
            override fun onResponse(call: Call<WXUserInfoBean>, response: Response<WXUserInfoBean>) {
                LogUtil.d(response.body().toString())
                val infoBean = response.body()
//                infoBean!!.headimgurl!!.d()
//                infoBean.nickname!!.d()
//                infoBean.province!!.d()
                wxLoginCallBack.loginSuccess(infoBean!!)
            }

            override fun onFailure(call: Call<WXUserInfoBean?>?, t: Throwable?) {
                wxLoginCallBack.loginError(t.toString())
            }
        })
    }

}