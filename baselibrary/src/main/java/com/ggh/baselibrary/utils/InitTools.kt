package com.ggh.baselibrary.utils

import com.ggh.baselibrary.BaseApp
import com.ggh.baselibrary.MyConfig
import com.ggh.baselibrary.bean.UserPreference
import com.senseplay.collectsdk.AccountManager
import com.senseplay.collectsdk.SDKManager
import com.senseplay.collectsdk.config.MVersion
import com.sensethink.linksdk.LinkCloud
import com.sensethink.linksdk.ble.BleAdapter
import com.sensethink.linksdk.config.VerType
import com.sensethink.linksdk.helper.ICallback
import com.sensethink.linksdk.param.PTTModel
import com.sensethink.linksdk.param.SettingsParam

/**
 *  * description : 
 *  * Author : Xiao
 *  * Date : 2021-7-13
 *  
 **/
class InitTools {
    companion object {
        val TAG = "InitTools"


        private var instance: InitTools? = null
            get() {
                if (field == null) {
                    return InitTools()
                }
                return field
            }

        @Synchronized
        fun get(): InitTools {
            return instance!!
        }
    }

    var manager: SDKManager? = null
    fun initSDK() {

        if (manager == null) {
            manager = SDKManager.getInstance()
        }

        SDKManager.setPkg("com.senseplay.leapcollect")
        SDKManager.setClient(MyConfig.client_id, MyConfig.client_secret)

        manager?.init(BaseApp.instance)

        setSocket()
        BleAdapter.getAdapter(BaseApp.instance)
    }

    public fun initVersion(){
        MVersion.setShow(true)
        val verType = VerType.test
        MVersion.setVerType(verType)
    }

    private fun setSocket() {
        if (UserPreference.getUser().cloudOpen) {
            LinkCloud.getInstance().openCloud(41, UserPreference.getUser().cloud, mCallBack)
            SettingsParam.getInstance()
                .setPttModel(PTTModel.Local_Cloud, UserPreference.getUser().pttOpen, null)
        }
    }

    var mCallBack = ICallback<Boolean> { result ->
        ToastUtil.show(if (result) "success" else "fail")
    }

    private fun initUserValid(){
        AccountManager.getInstance().onAuthInvalid(null)
    }


}