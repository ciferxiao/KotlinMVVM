package com.mcl.kotlin_mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.ggh.baselibrary.base.fragment.AbsFragment
import com.ggh.baselibrary.ext.binding

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：BaseFragment
 * 创建时间：2020/6/27
 * 功能描述：  Fragment 基类封装  ViewDataBinding + ViewModel
 */
abstract class BaseDBFragment<DB : ViewDataBinding>(val layoutId:Int) : AbsFragment() {
    open lateinit var mBinding: DB
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //注解获取布局
//        val annotation: LayoutBind = this.javaClass.getAnnotation(LayoutBind::class.java)!!
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding = binding( resId = layoutId,viewGroup = container)
        return mBinding.root
    }
}