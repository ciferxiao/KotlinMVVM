package com.ggh.baselibrary

import android.os.Environment
import java.io.File

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：Config
 * 创建时间：2020/8/13
 * 功能描述：   配置信息
 */
object Config {
        //TODO: App 域名
        const val HOST = "http://192.168.1.74:8099"
        const val APP_HOST = "${HOST}/mock/277/"

        const val H5_HOST = "http://dspzbdshop.jp893.com/h5"
        const val H5_URL = "${H5_HOST}/#/pages/"

        //请求成功返回码
        const val CODE_SUCCESS = 1
        //TODO: 微信appId
        const val WX_APP_ID = "1111111"
        //TODO: 微信appSecret
        const val WX_APP_SECRET = "1111111"
        //TODO: 是否使用友盟   false = 不使用   true = 使用
        const val isUMeng  = false

        const val TX_VIDEO_KEY =  "560d13e2abb3a83ecc4918cb908695f4"
        const val TX_VIDEO_URL = "http://license.vod2.myqcloud.com/license/v1/36c1939bfde239788f352e99fb19c3a0/TXUgcSDK.licence"

        const val TX_LIVE_KEY =  "560d13e2abb3a83ecc4918cb908695f4"
        const val TX_LIVE_URL = "http://license.vod2.myqcloud.com/license/v1/36c1939bfde239788f352e99fb19c3a0/TXLiveSDK.licence"


        //文件夹名字
        val DIR_NAME = "yimitang"
        //Environment.getExternalStorageDirectory()   SD卡：这里的SD卡是指内置的SD卡
        //Environment.getDataDirectory().getParentFile()   手机内存：其实是内部存储的根目录
        var CACHE_IMAGE = Environment.getExternalStorageDirectory().absolutePath + "/"+DIR_NAME + "/"+"tvRecordImage"
        var CACHE_VIDEO = Environment.getExternalStorageDirectory().absolutePath + "/"+DIR_NAME + "/"+"video"
}