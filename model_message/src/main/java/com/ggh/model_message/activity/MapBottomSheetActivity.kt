package com.ggh.model_message.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.amap.api.maps.AMap
import com.ggh.baselibrary.utils.ToastUtil
import com.ggh.maplibrary.util.AMapInit
import com.ggh.model_message.R
import com.mcl.kotlin_mvvm.base.BaseActivity
import kotlinx.android.synthetic.main.activty_map.*

class MapBottomSheetActivity : BaseActivity(R.layout.activty_map){

    companion object{
        fun openActivity(context: Context){
            val intent = Intent(context,MapBottomSheetActivity::class.java)
            context.startActivity(intent)
        }
    }

    var aMap : AMap? = null
    override fun main() {
        val mapInit = AMapInit()
        aMap = mapInit.initMap(textureView,false)

        initListener()
    }

    fun initListener(){
        aMap?.setOnMapLoadedListener {
            ToastUtil.show("map loaded")
        }

    }

    fun initBehavior(){

    }


}