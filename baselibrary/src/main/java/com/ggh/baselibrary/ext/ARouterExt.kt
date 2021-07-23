package com.ggh.baselibrary.ext

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

fun  String.froward(bundle: Bundle ? = null){
    val pointRange = ARouter.getInstance().build(this)
    if (bundle != null){
        pointRange.with(bundle)
    }

    pointRange.navigation()
}
