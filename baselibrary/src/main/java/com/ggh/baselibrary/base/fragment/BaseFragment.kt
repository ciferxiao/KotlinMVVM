package com.mcl.kotlin_mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ggh.baselibrary.base.fragment.AbsFragment

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：BaseFragment
 * 创建时间：2020/6/27
 * 功能描述：  Fragment 基类封装  ViewDataBinding + ViewModel
 */
abstract class BaseFragment(val layoutId:Int) : AbsFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = layoutInflater.inflate(layoutId,container,false)
        return view
    }


}