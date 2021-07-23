package com.ggh.baselibrary.base.fragment

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ggh.baselibrary.base.BaseView
import com.ggh.baselibrary.utils.DialogUtil

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：AbsFragment
 * 创建时间：2020/8/8
 * 功能描述：
 */
abstract class AbsFragment : Fragment() , BaseView {
    protected var TAG = this.javaClass.simpleName
    protected lateinit var mContext: Context
    protected lateinit var mActivity: Activity
    //数据加载框
    private var loadDialog : Dialog? = null
    //是否第一次加载数据
    private var isFirstLoad  = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity!!
        mActivity = activity!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDialog = DialogUtil.loadingDialog(mContext,"加载中")
        main(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        //将数据加载逻辑放到onResume()方法中   只有当Fragment 可见时且第一次
        if (isFirstLoad){
            loadData()
            isFirstLoad = false
        }

    }

    open fun loadData(){}

    open fun main(savedInstanceState: Bundle?) {
        main()
    }

    protected abstract fun main()

    override fun showLoading() {
        if (loadDialog!=null){
            loadDialog!!.show()
        }
    }

    override fun dissLoading() {
        if (loadDialog!=null && loadDialog!!.isShowing){
            loadDialog!!.hide()
        }
    }

    override fun onDestroy() {
        loadDialog = null
        super.onDestroy()
    }

}