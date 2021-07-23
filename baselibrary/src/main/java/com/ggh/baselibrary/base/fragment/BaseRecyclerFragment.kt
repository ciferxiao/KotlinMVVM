package com.ggh.baselibrary.base.fragment

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ggh.baselibrary.R
import com.ggh.baselibrary.base.AbsAdapter
import com.ggh.baselibrary.mvvm.RecyclerViewModel
import com.ggh.baselibrary.ext.toast
import com.mcl.kotlin_mvvm.base.BaseFragment
import kotlinx.android.synthetic.main.layout_recycler_smart.*
import luyao.mvvm.core.view.SpaceItemDecoration
import luyao.util.ktx.ext.dp

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：BaseRecyclerFragment
 * 创建时间：2020/9/12
 * 功能描述：
 */
abstract class BaseRecyclerFragment<T, VDB : ViewDataBinding> : BaseFragment(R.layout.layout_recycler_smart) {

    //页码
    var mPage = 1
    //每页数量
    var mLimit = 20
    //刷新
    var isRefresh = true
    //适配器数据
    lateinit var mAdapter: AbsAdapter<T, VDB>
    //ViewModel 基类
    lateinit var mViewModel: RecyclerViewModel<T>

    override fun main() {
        initData()
        sendHttpRequest()
        /**
         * Smart 设置相关信息
         */
        layout_recycler_smart_smart.apply {
            //刷新监听
            setOnRefreshListener {
                isRefresh = true
                mPage = 1
                mViewModel.mPage.value = mPage
                mViewModel.mLimit.value = mLimit
                sendHttpRequest()
            }
            //加载监听
            setOnLoadMoreListener {
                isRefresh = false
                mPage++
                mViewModel.mPage.value = mPage
                mViewModel.mLimit.value = mLimit
                sendHttpRequest()
            }
        }

        /**
         * RecyclerView 设置相关信息
         */
        layout_recycler_smart_recycler.apply {
            adapter = getRecyclerAdapter()
            layoutManager = getRecyclerLayoutManager()
            addItemDecoration(addItemDecoration())
        }

        /**
         * 返回数据订阅
         */
        mViewModel.apply {
            uiState.observe(this@BaseRecyclerFragment, Observer {
                if (it.showLoading)showLoading()

                it.isSuccess?.let {

                    if (mViewModel.mPage.value == 1){
                        if (mAdapter.list.size > 0){
                            mAdapter.remove()
                        }
                    }

                    mAdapter.list = it
                    refreshData(it)
                    dissLoading()
                }

                it.isError?.let {
                    dissLoading()
                    it.toast()
                }
            })
        }
    }

    /**
     * 初始化
     */
    abstract fun initData()

    /**
     * 数据请求
     */
    fun sendHttpRequest() {
        mViewModel.load()
    }

    /**
     * 适配器
     */
    abstract fun getRecyclerAdapter(): AbsAdapter<T, VDB>

    /**
     * 设置布局方式   默认线型布局
     */
    fun getRecyclerLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(mContext)

    /**
     * 设置空数据布局样式   默认  R.layout.empty_view
     */
    fun setEmptyView(): View = LayoutInflater.from(mContext!!).inflate(R.layout.empty_view, null)

    /**
     * 添加数据分割线  默认距离顶部 1dp
     */
    fun addItemDecoration(): SpaceItemDecoration = SpaceItemDecoration(top = 1.dp.toInt(),left = 0.dp.toInt(),bottom = 0.dp.toInt(),right = 0.dp.toInt())

    /**
     * 走这里之前已经把数据添加到适配器上    对数据处理
     */
    fun refreshData(mList: List<T>? = null) {

        if (mPage == 1) {

            layout_recycler_smart_smart.finishRefresh()

            if (mList!!.size == 0) {
                //添加空布局
                layout_recycler_smart_empty.addView(setEmptyView())
                //不可加载布局
                layout_recycler_smart_smart.setEnableLoadMore(false)
            } else if (mList!!.size < mLimit) {
                layout_recycler_smart_smart.setEnableLoadMore(false)
            } else {
                layout_recycler_smart_smart.setEnableLoadMore(true)
            }
        } else {
            layout_recycler_smart_smart.finishLoadMore()
            if (mList!!.size == mLimit) {
                layout_recycler_smart_smart.setEnableLoadMore(true)
            } else {
                layout_recycler_smart_smart.setNoMoreData(true)
            }
        }


    }
}