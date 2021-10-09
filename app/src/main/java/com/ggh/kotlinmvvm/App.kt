package com.ggh.kotlinmvvm

import com.ggh.librarycommmon.CommonApp
import com.ggh.maplibrary.util.GDMapLacationUtil


class App :  CommonApp(){

    companion object{

        val TAG:String? = "BASEAPPLICATION"

    }

    override fun onCreate() {
        super.onCreate()
        GDMapLacationUtil.init(this)
    }

}