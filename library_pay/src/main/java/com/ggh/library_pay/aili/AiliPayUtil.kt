package com.ggh.library_pay.aili

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import com.alipay.sdk.app.PayTask

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：AiliPayUtil
 * 创建时间：2020/10/7
 * 功能描述：
 */
object AiliPayUtil {

    const val SDK_PAY_FLAG = 1
    @SuppressLint("HandlerLeak")
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            val result =
                PayResult(msg.obj as Map<String, String>)
        }
    }

    fun AliPayUtil(
        mActivity: Activity?,
        info: String?,
        mHandler: Handler
    ) {
        val payRunnable = Runnable {
            val alipay = PayTask(mActivity)
            val result: Map<String, String> =
                alipay.payV2(info, true)
            val msg = Message()
            msg.what = SDK_PAY_FLAG
            msg.obj = result
            mHandler.sendMessage(msg)
        }
        // 必须异步调用
        val payThread = Thread(payRunnable)
        payThread.start()
    }

    fun AliPayUtil(mActivity: Activity?, info: String?) {
        this.AliPayUtil(mActivity, info, mHandler)
    }

    data class PayResult(val rawResult: Map<String, String>)

}