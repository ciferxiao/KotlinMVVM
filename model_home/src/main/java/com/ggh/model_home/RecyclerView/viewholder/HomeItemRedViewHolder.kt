package com.ggh.model_home.RecyclerView.viewholder

import com.ggh.app.adapter.BaseViewHolder
import com.ggh.baselibrary.base.adapter.ListBean
import com.ggh.baselibrary.base.adapter.MultipleBean
import com.ggh.baselibrary.ext.getColor
import com.ggh.baselibrary.ext.singleClick
import com.ggh.baselibrary.ext.toast
import com.ggh.baselibrary.utils.LogUtil
import com.ggh.model_home.R
import kotlinx.android.synthetic.main.item_home.*
import org.jetbrains.anko.backgroundColor

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 创建时间：2020/10/31
 * 功能描述： 每个item所对应的ViewHolder
 */
class HomeItemRedViewHolder : BaseViewHolder<MultipleBean<ListBean>>() {

    lateinit var mode: MultipleBean<ListBean>

    override val layoutResId: Int
        get() = R.layout.item_home

    /**
     * 添加视图得到时候，可以在这里面添加点击事件
     */
    override fun setViews() {
        super.setViews()
        containerView?.singleClick { mode.title?.toast()}
    }

    /**
     * 这里给视图添加数据
     * @param model 数据
     * @param position 所在的索引
     */
    override fun handleData(model: MultipleBean<ListBean>, position: Int) {
        this.mode = model
        testName.text = model.title
        testName.setBackgroundColor(R.color.red.getColor())
    }

}