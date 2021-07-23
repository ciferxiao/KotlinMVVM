package com.ggh.model_mine.view

import android.content.Context
import android.graphics.Canvas
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import com.ggh.model_mine.R

class CustomView1 : View {


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {

    }

    init {

    }

    /**
     * 测量视图大小
     *
     *  Q: 为什么要测量View大小？
     *
     *  A: View的大小不仅由自身所决定，同时也会受到父控件的影响，为了我们的控件能更好的适应各种情况，一般会自己进行测量。
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthsize =  MeasureSpec.getSize(widthMeasureSpec);      //取出宽度的确切数值
        val widthmode =  MeasureSpec.getMode(widthMeasureSpec);      //取出宽度的测量模式

        val heightsize =  MeasureSpec.getSize(heightMeasureSpec);    //取出高度的确切数值
        val heightmode =  MeasureSpec.getMode(heightMeasureSpec);    //取出高度的测量模式

        //如果对View的宽高进行修改了，不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec); 要调用 setMeasuredDimension( widthsize, heightsize); 这个函数
        setMeasuredDimension(widthsize,heightsize)
    }

    /**
     * 在视图大小发生改变时调用
     *
     *  Q: 在测量完View并使用setMeasuredDimension函数之后View的大小基本上已经确定了，那么为什么还要再次确定View的大小呢？
     *
     *  A: 这是因为View的大小不仅由View本身控制，而且受父控件的影响，所以我们在确定View大小的时候最好使用系统提供的onSizeChanged回调函数。
     *
     *
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //宽度，高度，上一次宽度，上一次高度。
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(resources.getColor(R.color.app_color))
    }
}