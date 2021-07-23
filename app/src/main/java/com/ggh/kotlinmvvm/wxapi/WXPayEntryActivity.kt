package com.ggh.kotlinmvvm.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Message
import com.ggh.baselibrary.ext.toast
import com.ggh.kotlinmvvm.R
import com.ggh.library_pay.wechat.WXUtil
import com.ggh.library_pay.wechat.WXUtil.SDK_PAY_WX
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：WXPayEntryActivity
 * 创建时间：2020/7/17
 * 功能描述： 微信支付回调界面
 */
class WXPayEntryActivity : Activity(), IWXAPIEventHandler {
    private lateinit var api: IWXAPI
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pay_result)
        api = WXUtil.mIWXAPI;
        api.handleIntent(getIntent(), this);
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        api.handleIntent(intent, this)
    }

    override fun onReq(req: BaseReq) {}

    /**
     * 微信支付回调
     */
    override fun onResp(resp: BaseResp) {
		val errCord = resp.errCode

        when(errCord){
            0  -> {"支付成功".toast()}
            -1 -> {"支付错误".toast()}
            -2 -> {"用户取消".toast()}
        }

		//这里接收到了返回的状态码可以进行相应的操作，如果不想在这个页面操作可以把状态码存在本地然后finish掉这个页面，这样就回到了你调起支付的那个页面
		//获取到你刚刚存到本地的状态码进行相应的操作就可以了
		finish();
    }
}