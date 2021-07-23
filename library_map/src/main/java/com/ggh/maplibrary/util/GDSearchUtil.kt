package com.ggh.maplibrary.util

import android.content.Context
import android.util.Log
import com.amap.api.services.geocoder.GeocodeQuery
import com.amap.api.services.geocoder.GeocodeResult
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener
import com.amap.api.services.geocoder.RegeocodeResult
import com.ggh.maplibrary.bean.AddressInfoBean

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：GDSearchUtil
 * 创建时间：2020/8/21
 * 功能描述：
 */
object GDSearchUtil {
    private const val TAG = "GDSearchUtil"
    fun changeLngLat(
        context: Context?,
        info: AddressInfoBean?,
        callBack: GDSearchUtilCallBack
    ) {
        if (info == null) return
        val geocoderSearch = GeocodeSearch(context)
        geocoderSearch.setOnGeocodeSearchListener(object : OnGeocodeSearchListener {
            override fun onRegeocodeSearched(
                regeocodeResult: RegeocodeResult,
                i: Int
            ) {
                info.lng = "0"
                info.lat = "0"
                callBack.onSuccess(info)
            }

            override fun onGeocodeSearched(
                geocodeResult: GeocodeResult,
                i: Int
            ) {
                val lat =
                    geocodeResult.geocodeAddressList[0].latLonPoint.latitude
                        .toString()
                val lng =
                    geocodeResult.geocodeAddressList[0].latLonPoint.longitude
                        .toString()
                Log.d(TAG, lat)
                Log.d(TAG, lng)
                info.lng = lng
                info.lat = lat
                Log.d(TAG, info.toString())
                callBack.onSuccess(info)
            }
        })
        // name表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode
        val query = GeocodeQuery(info.province, info.city)
        geocoderSearch.getFromLocationNameAsyn(query)
    }

    interface GDSearchUtilCallBack {
        fun onSuccess(infoBean: AddressInfoBean?)
    }
}