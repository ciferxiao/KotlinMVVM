package com.ggh.baselibrary.widget

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ActivityManager.RunningTaskInfo
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.*
import android.view.WindowManager.LayoutParams

class FloatingWindow {
    var windowManager: WindowManager? = null
    var showView: View? = null
    var floatParams: LayoutParams? = null

    fun showFloatingWindowView(context: Context, view: View) {
        //悬浮视图
        showView = view
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
        floatParams = getParams()
        //设置窗口触摸事件
        view.setOnTouchListener(FloatViewMoveListener())
        //悬浮窗生成
        windowManager?.addView(view, floatParams)
    }

    public fun dismiss() {
        if (windowManager != null && showView != null && showView?.isAttachedToWindow!!) {
            windowManager?.removeView(showView)
        }
    }

    private fun getParams(): LayoutParams? {
        val layoutParams = LayoutParams()
        //设置悬浮窗口类型
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = LayoutParams.TYPE_SYSTEM_ALERT;
        }
        //设置悬浮窗口属性
        layoutParams.flags = (LayoutParams.FLAG_NOT_FOCUSABLE
                or LayoutParams.FLAG_NOT_TOUCH_MODAL
                or LayoutParams.FLAG_LAYOUT_IN_SCREEN
                or LayoutParams.FLAG_LAYOUT_INSET_DECOR
                or LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH)
        //设置悬浮窗口透明
        layoutParams.format = PixelFormat.TRANSLUCENT
        //设置悬浮窗口长宽数据
        layoutParams.width = 600
        layoutParams.height = 340
        //设置悬浮窗显示位置
        layoutParams.gravity = Gravity.START or Gravity.TOP
        layoutParams.x = 100
        layoutParams.y = 100
        return layoutParams
    }

    public fun setTopApp(context: Context) {
        //获取ActivityManager
        val activityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        //获得当前运行的task(任务)
        var taskInfoList: List<RunningTaskInfo?>? = null
        taskInfoList = activityManager.getRunningTasks(100)
        if (taskInfoList == null || taskInfoList.isEmpty()) {
            return
        }
        for (taskInfo in taskInfoList) {
            //找到本应用的 task，并将它切换到前台
            if (taskInfo.topActivity != null && taskInfo.topActivity!!.getPackageName()
                    .equals(context.getPackageName())
            ) {
                activityManager.moveTaskToFront(taskInfo.id, 0);
                break;
            }
        }
    }

    inner class FloatViewMoveListener : View.OnTouchListener {
        //相对于屏幕
        private var startX: Int? = 0
        private var startY: Int? = 0;


        //相对于view
        private var viewStartX: Int? = 0
        private var viewStartY: Int? = 0
        private var isMoving: Boolean = false


        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            val action = event?.action
            val x = event?.x?.toInt()
            val y = event?.y?.toInt()
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    isMoving = true
                    startX = event.rawX.toInt()
                    startY = event.rawY.toInt()

                    viewStartX = x
                    viewStartY = y
                }
                MotionEvent.ACTION_MOVE -> {
                    val currentStartX = event.rawX.toInt()
                    val currentStartY = event.rawY.toInt()

                    floatParams?.x = floatParams?.x?.plus(currentStartX.minus(startX!!))
                    floatParams?.y = floatParams?.y?.plus(currentStartY.minus(startY!!))

                    windowManager?.updateViewLayout(showView, floatParams)

                    startX = currentStartX
                    startY = currentStartY

                    val deltaX = x?.minus(viewStartX!!)
                    val deltaY = y?.minus(viewStartY!!)
                    if (Math.abs(deltaX!!) >= 5 || Math.abs(deltaY!!) >= 5) {
                        isMoving = true
                    }
                }
                MotionEvent.ACTION_UP -> {
                }
            }
//如果是移动事件不触发OnClick事件，防止移动的时候一放手形成点击事件
            return isMoving
        }
    }

}