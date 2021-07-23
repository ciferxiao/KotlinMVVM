package com.ggh.app.adapter.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 文件名：SimpleItem.kt
 * 创建时间：2020/11/4
 * 功能描述： 实现方法
 */
abstract class SimpleItem<T> : AdapterItem<T> {


    protected lateinit var rootView: View

    /**
     * 获取布局
     */
    override fun getItemView(context: Context, parent: ViewGroup): View {
        rootView = LayoutInflater.from(context).inflate(layoutResId, parent, false)
        return rootView
    }

    /**
     * 设置视图
     *
     * 可在这里面添加点击事件
     */
    override fun setViews() {}

    /**
     * Item绑定到视图
     */
    override fun onAttach() {}

    /**
     * Item从视图解绑
     */
    override fun onDetach() {}


}
