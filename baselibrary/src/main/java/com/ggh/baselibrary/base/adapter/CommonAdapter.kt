package com.ggh.baselibrary.base.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ggh.app.adapter.item.AdapterItem
import com.ggh.app.adapter.util.ItemTypeUtil
import com.ggh.baselibrary.base.AbsAdapter
import java.util.*

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 文件名：CommonAdapter.kt
 * 创建时间：2020/11/4
 * 功能描述： 公共 RecyclerView  适配器
 */
abstract class CommonAdapter<T> : RecyclerView.Adapter<CommonAdapter.AbsViewHolder<T>>(), IAdapter<T> {
    private var mDataList: List<T> = ArrayList()
    private var mType: Any? = null  // item 类型
    private val mUtil = ItemTypeUtil()
    private var currentPos = 0


    override fun getItemCount(): Int {
        return if (mDataList == null) 0 else mDataList.size
    }

    override fun setData(data: List<T>) {
        mDataList = data
    }

    override fun getData(): List<T> {
        return mDataList
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * instead by[.getItemType]
     *
     *
     * 通过数据得到obj的类型的type
     * 然后，通过[ItemTypeUtil]来转换位int类型的type
     */
    @Deprecated("")
    override fun getItemViewType(position: Int): Int {
        currentPos = position
        mType = getItemType(mDataList[position])
        return mUtil.getIntType(mType!!)
    }

    override fun getItemType(t: T): Any {
        return -1 // default
    }

    /**
     * 绑定视图
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsViewHolder<T> {
        return AbsViewHolder(parent.context, parent, createItem(mType!!))
    }

    /**
     * 绑定数据
     */
    override fun onBindViewHolder(holder: AbsViewHolder<T>, position: Int) {
        holder.item.handleData(mDataList[position], position)
    }

    override fun getCurrentPosition(): Int {
        return currentPos
    }

    override fun onViewAttachedToWindow(holder: AbsViewHolder<T>) {
        super.onViewAttachedToWindow(holder)
        holder.item.onAttach()
    }

    override fun onViewDetachedFromWindow(holder: AbsViewHolder<T>) {
        super.onViewDetachedFromWindow(holder)
        holder.item.onDetach()
    }


    class AbsViewHolder<T>(context: Context, parent: ViewGroup, val item: AdapterItem<T>) : RecyclerView.ViewHolder(item.getItemView(context, parent)) {

        init {
            this.item.setViews()
        }
    }
}