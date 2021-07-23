package com.ggh.app.adapter

import android.view.View
import com.ggh.app.adapter.item.SimpleItem
import kotlinx.android.extensions.LayoutContainer

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 文件名：BaseViewHolder.kt
 * 创建时间：2020/11/4
 * 功能描述： LayoutContainer 自定义构建View缓存 需要实现这个接口
 *          //开启自定义构建View缓存  具体在ViewHolder中
 *          androidExtensions {
 *             experimental = true
 *          }
 */
abstract class BaseViewHolder<T> : SimpleItem<T>(), LayoutContainer {
    override val containerView: View?
        get() = rootView
}
