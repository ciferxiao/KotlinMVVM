package com.ggh.kotlinmvvm.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.alibaba.fastjson.JSON
import com.ggh.baselibrary.utils.LogUtil
import com.ggh.library_pay.wechat.WXLoginCallBack
import com.ggh.library_pay.wechat.WXUserInfoBean
import com.ggh.library_pay.wechat.WXUtil
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import org.json.JSONException
import org.json.JSONObject


/**
 * 作者：Create by (mcl)
 * 时间：2019/12/26
 * 文件名：WXEntryActivity.java
 * 描述：  微信登录
 */
class WXEntryActivity : Activity(), IWXAPIEventHandler {
    private lateinit var api: IWXAPI
    private lateinit var code: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        api = WXUtil.mIWXAPI;

        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            val result = api.handleIntent(intent, this)
            if (!result) {
                LogUtil.d("参数不合法，未被SDK处理，退出")
                finish()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        api.handleIntent(data, this)
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        //报错   MicroMsg.SDK.WXApiImplV10: handleIntent fail, ex = replacement == null
        api.handleIntent(intent, this)
        finish()
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    override fun onReq(baseReq: BaseReq) {}

    /**
     * 回调方法
     */
    override fun onResp(baseResp: BaseResp) {
        try {
            val jsonObject = JSONObject(JSON.toJSONString(baseResp))
            code = jsonObject.get("code") as String
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        LogUtil.d("baseResp:${baseResp.errStr},${baseResp.openId},${baseResp.transaction},baseResp.errCode")
        var result = ""
        when (baseResp.errCode) {
            BaseResp.ErrCode.ERR_OK -> {//授权成功
                result = "发送成功"
                WXUtil.getWxInfo(code, object : WXLoginCallBack {
                    override fun loginSuccess(userInfoBean: WXUserInfoBean) {
                        loginWxLogin(userInfoBean)
                    }

                    override fun loginError(msg: String) {
                        LogUtil.d(msg)
                    }

                })
                finish()
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                result = "发送取消"
                LogUtil.d(result)
                finish()
            }
            BaseResp.ErrCode.ERR_AUTH_DENIED -> {
                result = "发送被拒绝"
                LogUtil.d(result)
                finish()
            }
            else -> {
                result = "发送返回"
                LogUtil.d(result)
                finish()
            }
        }
    }

    //微信登陆
    private fun loginWxLogin(userInfoBean: WXUserInfoBean) {

    }
}