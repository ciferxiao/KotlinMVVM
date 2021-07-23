package com.ggh.baselibrary.ext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：Fragment
 * 创建时间：2020/9/2
 * 功能描述：
 */


inline fun <T : ViewDataBinding> Fragment.binding(inflater: LayoutInflater = layoutInflater,@LayoutRes resId: Int,viewGroup: ViewGroup?): T =
    DataBindingUtil.inflate<T>(inflater, resId,viewGroup,false).apply {
        lifecycleOwner = this@binding
    }