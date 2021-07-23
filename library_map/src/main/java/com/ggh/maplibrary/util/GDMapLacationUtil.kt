package com.ggh.maplibrary.util

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.ggh.baselibrary.utils.LogUtil
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

/**
 * @Author Create by mcl
 * @Date 2020/4/2
 * @ClassName GDMapLacationUtil
 * @描述  高德地图定位工具类
 */
object GDMapLacationUtil {
    private const val TAG = "GDMapLacationUtil"
    private var mlocationClient: AMapLocationClient? = null
    var mLocationOption: AMapLocationClientOption? = null
    var sLocation: AMapLocation? = null

    /**
     * 初始化地图导航，在Application onCreate中调用，只需调用一次
     * @param context
     */
    @JvmStatic
    fun init(context: Context?) {
        // 声明mLocationOption对象
        mlocationClient = AMapLocationClient(context)
        // 初始化定位参数
        mLocationOption = AMapLocationClientOption()
        // 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption!!.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        // 设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption!!.interval = 2000
        // 设置定位参数
        mlocationClient!!.setLocationOption(mLocationOption)
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
    }

    /**
     * 获取之前的定位信息
     * @param listener
     */
    @JvmStatic
    fun getLocation( listener:(location:AMapLocation) -> Unit) {
        if (sLocation == null) {
            getCurrentLocation(listener)
        } else {
            listener(sLocation!!)
        }
    }

    /**
     * 获取当前定位信息
     * @param listener
     */
    @JvmStatic
    fun getCurrentLocation( listener:(location:AMapLocation) -> Unit) {
        if (mlocationClient == null) {
            return
        }
        // 设置定位监听
        mlocationClient!!.setLocationListener { location ->
            if (location != null) {
                //定位成功，取消定位
//                Log.e(TAG, "获取定位数据成功")
                mlocationClient!!.stopLocation()
                sLocation = location
                listener(location)
            } else {
                //获取定位数据失败
                Log.e(TAG, "获取定位数据失败")
            }
        }
        // 启动定位
        mlocationClient!!.startLocation()
    }

    /**
     *
     * @Title: destroy
     * @Description: 销毁定位，必须在退出程序时调用，否则定位会发生异常
     */
    fun destroy() {
        mlocationClient!!.onDestroy()
    }

    /**
     * 获取 高德地图 SHA1 值
     * @param context
     * @return
     */
    fun getSHA1(context: Context): String? {
        try {
            val info = context.packageManager.getPackageInfo(
                context.packageName, PackageManager.GET_SIGNATURES
            )
            val cert = info.signatures[0].toByteArray()
            val md = MessageDigest.getInstance("SHA1")
            val publicKey = md.digest(cert)
            val hexString = StringBuffer()
            for (i in publicKey.indices) {
                val appendString =
                    Integer.toHexString(0xFF and publicKey[i].toInt())
                        .toUpperCase(Locale.US)
                if (appendString.length == 1) hexString.append("0")
                hexString.append(appendString)
                hexString.append(":")
            }
            val result = hexString.toString()
            return result.substring(0, result.length - 1)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 回调监听
     */
    interface GDMapLocationListener {
        fun result(location: AMapLocation?)
    }
}