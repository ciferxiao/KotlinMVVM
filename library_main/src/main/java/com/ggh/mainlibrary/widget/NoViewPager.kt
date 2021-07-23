package com.ggh.mainlibrary.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import com.ggh.mainlibrary.R

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：MyViewPager
 * 创建时间：2020/7/3
 * 功能描述：可以禁止滑动的ViewPager
 */
class NoViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {
    private var mCanScroll: Boolean
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (mCanScroll) {
            try {
                super.onInterceptTouchEvent(ev)
            } catch (e: Exception) {
                false
            }
        } else {
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (mCanScroll) {
            try {
                super.onTouchEvent(ev)
            } catch (e: Exception) {
                false
            }
        } else {
            false
        }
    }

    fun setCanScroll(canScroll: Boolean) {
        mCanScroll = canScroll
    }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.NoViewPager)
        mCanScroll = ta.getBoolean(R.styleable.NoViewPager_canScroll, true)
        ta.recycle()
    }
}