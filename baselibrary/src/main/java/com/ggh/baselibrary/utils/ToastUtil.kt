package com.ggh.baselibrary.utils

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.ggh.baselibrary.BaseApp
import com.ggh.baselibrary.R
import com.ggh.baselibrary.databinding.LayoutRecyclerSmartBinding.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_toast.*

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：ToastUtil
 * 创建时间：2020/8/8
 * 功能描述： 吐司工具类   只能作用于前台，不可作用于后台显示
 */
object ToastUtil {
    private lateinit var toast: Toast

    fun show(content: String) {
        val inflater = LayoutInflater.from(BaseApp.instance)
        val view = inflater.inflate(R.layout.layout_toast, null)
        val text = view.findViewById(R.id.tost_message) as TextView
        text.text = content
        toast = Toast(BaseApp.instance)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = view
        toast.show()
    }
}