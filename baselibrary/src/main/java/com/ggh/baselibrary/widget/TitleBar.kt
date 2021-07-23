package com.ggh.baselibrary.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ggh.baselibrary.R

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：TitleBar
 * 创建时间：2020/7/3
 * 功能描述： 自定义标题栏
 */
class TitleBar @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(mContext, attrs, defStyleAttr) {
    private var titleCenter: String?
    private val titleRight: String?
    private val titleLeft: String?
    private var titleCenterColor: Int
    private var titleRightColor: Int
    private var titleLeftColor: Int
    private var titleRightIcon: Int
    private var titleLeftIcon: Int
    var tvTitleCenter: TextView? = null
        private set
    var tvTitleRight: TextView? = null
        private set
    var tvTitleLeft: TextView? = null
        private set
    private var imgTitleLeft: ImageView? = null
    private var imgTitleRight: ImageView? = null
    private fun initData() {
        tvTitleCenter!!.text = titleCenter
        tvTitleRight!!.text = titleRight
        tvTitleLeft!!.text = titleLeft
        tvTitleCenter!!.setTextColor(titleCenterColor)
        tvTitleRight!!.setTextColor(titleRightColor)
        tvTitleLeft!!.setTextColor(titleLeftColor)
        imgTitleLeft!!.setImageResource(titleLeftIcon)
        imgTitleRight!!.setImageResource(titleRightIcon)
    }

    private fun initView(
        context: Context,
        attrs: AttributeSet?
    ) {
        LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this, true)
        tvTitleCenter = findViewById(R.id.title_bar_center)
        tvTitleRight = findViewById(R.id.title_bar_right_text)
        tvTitleLeft = findViewById(R.id.title_bar_left_text)
        imgTitleLeft = findViewById(R.id.title_bar_left_img)
        imgTitleRight = findViewById(R.id.title_bar_right_img)
    }

    fun setTitleCenter(titleCenter: String?) {
        this.titleCenter = titleCenter
        tvTitleCenter!!.text = titleCenter
    }

    fun setImgTitleLeft(imgTitleLeft: ImageView?) {
        this.imgTitleLeft = imgTitleLeft
        tvTitleLeft!!.text = titleLeft
    }

    fun setImgTitleRight(imgTitleRight: ImageView?) {
        this.imgTitleRight = imgTitleRight
        tvTitleRight!!.text = titleRight
    }

    fun setTitleRightIcon(titleRightIcon: Int) {
        this.titleRightIcon = titleRightIcon
        imgTitleRight!!.setImageResource(titleRightIcon)
    }

    fun setTitleLeftIcon(titleLeftIcon: Int) {
        this.titleLeftIcon = titleLeftIcon
        imgTitleLeft!!.setImageResource(titleLeftIcon)
    }

    fun setTitleCenterColor(titleCenterColor: Int) {
        this.titleCenterColor = titleCenterColor
        tvTitleCenter!!.setTextColor(titleCenterColor)
    }

    fun setTitleLeftColor(titleLeftColor: Int) {
        this.titleLeftColor = titleLeftColor
        tvTitleLeft!!.setTextColor(titleLeftColor)
    }

    fun setTitleRightColor(titleRightColor: Int) {
        this.titleRightColor = titleRightColor
        tvTitleRight!!.setTextColor(titleRightColor)
    }

    fun getImgTitleLeft(): ImageView? {
        return imgTitleLeft
    }

    fun getImgTitleRight(): ImageView? {
        return imgTitleRight
    }

    init {
        val ta = mContext.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        titleRightIcon = ta.getResourceId(R.styleable.TitleBar_bar_titie_tv_right_icon, 0)
        titleLeftIcon = ta.getResourceId(R.styleable.TitleBar_bar_titie_tv_left_icon, 0)
        titleCenter = ta.getString(R.styleable.TitleBar_bar_title_tv_center)
        titleLeft = ta.getString(R.styleable.TitleBar_bar_titie_tv_left)
        titleRight = ta.getString(R.styleable.TitleBar_bar_titie_tv_right)
        titleCenterColor = ta.getColor(R.styleable.TitleBar_bar_title_tv_center_color, -0x1)
        titleRightColor = ta.getColor(R.styleable.TitleBar_bar_title_tv_right_color, -0x1)
        titleLeftColor = ta.getColor(R.styleable.TitleBar_bar_title_tv_left_color, -0x1)
        initView(mContext, attrs)
        initData()
        ta.recycle()
    }
}