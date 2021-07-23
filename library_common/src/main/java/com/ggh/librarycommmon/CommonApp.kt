package com.ggh.librarycommmon

import com.alibaba.android.arouter.launcher.ARouter
import com.ggh.baselibrary.BaseApp
import com.ggh.baselibrary.BuildConfig
import com.ggh.baselibrary.Config
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure


open class CommonApp :BaseApp() {

    override fun onCreate() {
        super.onCreate()
        initARouter()
        initUMeng()
    }

    /**
     * 初始化路由
     */
    fun initARouter(){
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
            ARouter.printStackTrace()

        }
        ARouter.init(instance)
    }

    /**
     * 初始化友盟统计
     *
     *  AUTO: Android 4.0及以上版本支持Activity生命周期的自动监控   AUTO模式下SDK会自动调用MobclickAgent.onResume/MobclickAgent.onPause接口，用户无须手动调用这两个接口
     *  MANUAL: 如果需要统计 Android 4.0 以下版本设备统计数据，则必须选择手动模式(MANUAL)，对宿主App中所有Activity都手动调用MobclickAgent.onResume/MobclickAgent.onPause手动埋点
     * LEGACY_AUTO:SDK默认情况下使用此模式
     * LEGACY_AUTO:对非Activity页面(如:Fragment)进行埋点统计的SDK老用户，则请选择LEGACY_MANUAL模式，这样您的App埋点代码不需要做任何修改，SDK即可正常工作
     *
     */
    fun initUMeng(){

        if(!Config.isUMeng)return

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null)
        // 选用LEGACY_AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.LEGACY_AUTO)
        // interval 单位为毫秒，如果想设定为40秒，interval应为 40*1000.   当app  进入后台后再次启动的时间间隔，大于则启动数+1
        MobclickAgent.setSessionContinueMillis(40*1000);
    }
}