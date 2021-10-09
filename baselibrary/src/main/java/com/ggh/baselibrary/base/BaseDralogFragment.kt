package com.ggh.baselibrary.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ggh.baselibrary.BaseApp
import com.ggh.baselibrary.R
import kotlinx.android.extensions.LayoutContainer

/**
 * 文件名：BaseDralogFragment
 * 创建时间：2020/8/11
 * 功能描述： DialogFragment的基类封装
 */
abstract class BaseDralogFragment : DialogFragment(){
    @JvmField
    protected var TAG = this.javaClass.simpleName
    @JvmField
    protected var mContext: Context? = null

    lateinit var mRootView: View

    //执行在Fragment 的 onGetLayoutInflater 中
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mContext = activity
        mRootView = LayoutInflater.from(mContext).inflate(layoutId, null)
        val dialog = Dialog(mContext!!, dialogStyle)
        dialog.setContentView(mRootView)
        dialog.setCancelable(canCancel())
        dialog.setCanceledOnTouchOutside(canCancel())
        val window = dialog.window
        window!!.setGravity(setGravity())
        window.setWindowAnimations(dialogAnim)
        window.setLayout(setWidth(), setHeight())
        return dialog
    }

    open fun setWidth(): Int {
        return LinearLayout.LayoutParams.MATCH_PARENT
    }

    fun setHeight(): Int {
        return LinearLayout.LayoutParams.WRAP_CONTENT
    }

    protected open fun setGravity(): Int {
        return Gravity.BOTTOM
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        main(savedInstanceState)
    }

    /**
     * 获取布局
     * @return
     */
    protected abstract val layoutId: Int

    /**
     * 处理具体的业务逻辑
     * @param savedInstanceState
     */
    protected abstract fun main(savedInstanceState: Bundle?)
    protected val dialogAnim: Int
        protected get() = R.style.main_menu_animStyle

    protected open fun canCancel(): Boolean {
        return true
    }

    protected val dialogStyle: Int
        protected get() = R.style.dialog_style

    open fun show(manager: FragmentManager?) {
        super.show(manager!!, TAG)
    }
}