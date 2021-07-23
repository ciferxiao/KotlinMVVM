package com.ggh.baselibrary.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.ggh.baselibrary.R

class MaterialTextView : AppCompatTextView {

    private var mContext: Context? = null
    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context!!, attrs!!, 0)

    constructor (
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        mContext = context
        initView()
        intiAttributes(context, attrs)
    }

    private fun intiAttributes(
        context: Context,
        attrs: AttributeSet
    ) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MateriaTextView)
        val isOut = a.getBoolean(R.styleable.MateriaTextView_isOut, false)
        val isbold = a.getBoolean(R.styleable.MateriaTextView_isBold, true)
        setOut(isOut)
        setBold(isbold)
        a.recycle()
    }

    private fun initView() {
        gravity = Gravity.CENTER
    }

    private fun setBold(isbold: Boolean) {
        typeface = if (isbold) {
            Typeface.DEFAULT_BOLD
        } else {
            Typeface.DEFAULT
        }
    }


    private fun setOut(isOut: Boolean) {
        if (isOut) {
            setBackgroundResource(R.drawable.shape_materia_outline_bg)
            setTextColor(Color.parseColor("#6CD3BC"))
        } else {
            setBackgroundResource(R.drawable.shape_materia_bg)
            setTextColor(Color.WHITE)
        }
    }

}