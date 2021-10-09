package com.ggh.maplibrary.util

import com.amap.api.maps.AMap
import com.amap.api.maps.TextureMapView
import com.amap.api.maps.UiSettings

class AMapInit {

    var aMap: AMap? = null
    var mUiSettings : UiSettings? = null

    fun initMap(map_view: TextureMapView, flag: Boolean) : AMap {
        if(aMap == null){
            aMap = map_view.map
        }
        mUiSettings = aMap?.uiSettings

        //显示默认定位按钮
        mUiSettings.let {
            it?.isMyLocationButtonEnabled = false
            it?.isRotateGesturesEnabled = false
            it?.isTiltGesturesEnabled = false
            it?.isZoomControlsEnabled = false
        }

        //设置地图显示
        aMap?.isTrafficEnabled = false
        aMap?.showBuildings(false)
        aMap?.mapType = AMap.MAP_TYPE_NORMAL


        return aMap!!
    }

}