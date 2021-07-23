package com.ggh.kotlinmvvm

import android.Manifest
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast
import com.ggh.baselibrary.bean.UserPreference
import com.ggh.baselibrary.ext.froward
import com.ggh.baselibrary.utils.AnimateUtil
import com.ggh.baselibrary.utils.InitTools
import com.ggh.librarycommmon.constant.ARouterConstant
import com.mcl.kotlin_mvvm.base.BaseActivity
import com.qw.soul.permission.SoulPermission
import com.qw.soul.permission.bean.Permission
import com.qw.soul.permission.callbcak.CheckRequestPermissionListener
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(R.layout.activity_splash) {

    //业务逻辑
    override fun main() {
        SoulPermission.getInstance()
            .checkAndRequestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                object : CheckRequestPermissionListener {
                    override fun onPermissionOk(permission: Permission?) {
                    }

                    override fun onPermissionDenied(permission: Permission?) {
                    }
                })

        InitTools.get().initVersion()
        iv_icon.post { AnimateUtil.loadXmlAniSet(iv_icon, R.anim.splash,this)}


        Handler(Looper.getMainLooper()).postDelayed({
            if(TextUtils.isEmpty(UserPreference.getUser().accessToken)){
                ARouterConstant.PATH_LOGIN.froward()
            }else{
                //跳转到主页
                ARouterConstant.PATH_MAIN.froward()
            }


        },1500)
    }
}
