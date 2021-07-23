package com.ggh.baselibrary.base.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.ggh.baselibrary.R
import com.ggh.baselibrary.ext.bindingInflate
import com.ggh.baselibrary.widget.TitleBar
import kotlinx.android.synthetic.main.base_title_layout.*
import kotlinx.android.synthetic.main.layout_title_bar.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.textColor

abstract class BaseDBActivity<DB : ViewDataBinding>(val layoutRes : Int) : AbsActivity(R.layout.base_title_layout) {
    lateinit var mBinding: DB
    lateinit var mTitleBar: TitleBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = bindingInflate(resId = layoutRes)
        //将内容布局添加到基础布局中
        frame_content.addView(mBinding.root)
        main()
        initTitle()
    }

    /**
     * 初始化标题内容
     */
    private fun initTitle() {
        mTitleBar = title_bar
        title_bar.apply {

            visibility = if (setTitleText().trim().isNotEmpty())View.VISIBLE else View.GONE

            if (visibility == View.GONE) else {
                //设置公共属性
                backgroundColor = resources.getColor(R.color.white)
                //左侧控件
                title_bar_left_img.apply {
                    imageResource = R.drawable.icon_title_back_aeaeae
                    setOnClickListener { onBackPressed()  }
                }
                //标题
                title_bar_center.apply {
                    textColor = resources.getColor(R.color.black)
                    text = setTitleText()
                }

            }
        }
    }

    abstract fun main()

    /**
     * 默认不显示标题
     */
    open fun setTitleText(): String = " "

    override fun onBackPressed() {
        super.onBackPressed()
        // Fragment 逐个出栈
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}