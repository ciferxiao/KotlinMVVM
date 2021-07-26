package com.ggh.model_message

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.provider.Settings
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.ggh.baselibrary.utils.ToastUtil
import com.ggh.baselibrary.widget.FloatingWindow
import com.google.common.reflect.Reflection.getPackageName
import com.mcl.kotlin_mvvm.base.BaseFragment
import kotlinx.android.synthetic.main.floating_video_view.*
import kotlinx.android.synthetic.main.floating_video_view.view.*
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : BaseFragment(R.layout.fragment_message) {

    lateinit var viewModel: MessageViewModel
    var floatingView: FloatingWindow? = null

    companion object {
        @JvmStatic
        fun newInstance() = MessageFragment()
        const val REQUEST_OVERLAY_CODE: Int = 0x01
    }

    override fun main() {
        val model: MessageViewModel = ViewModelProviders.of(this).get(MessageViewModel::class.java)

        tv_show.setOnClickListener {
            if (!requestOverlayPermission()) {
                //showFloatingView()
                initFloatView()
            } else {
                ToastUtil.show("请开启悬浮窗权限")
            }
        }

        tv_hide.setOnClickListener {

        }
    }

    private fun initFloatView() {
        if (floatingView == null) {
            floatingView = FloatingWindow()
        }
        val view = View.inflate(this.context, R.layout.floating_video_view, null)
        Glide.with(this).load(R.drawable.picture_audio_placeholder).into(view.thumb_floating_view)

        view.video_view.let {
            it.setVideoPath("https://stream7.iqilu.com/10339/article/202002/18/2fca1c77730e54c7b500573c2437003f.mp4")
            it.setOnPreparedListener { view.thumb_floating_view.visibility = View.GONE }
            it.setOnCompletionListener {
                it.start()
                it.isLooping = true
            }
        }
        view.close_floating_view.setOnClickListener { floatingView?.dismiss() }
        view.back_floating_view.setOnClickListener {
            context?.let { it1 ->
                floatingView?.setTopApp(
                    it1
                )
            }
        }

        context?.let { floatingView?.showFloatingWindowView(it, view) }
    }

    private fun showFloatingView() {

    }

    fun requestOverlayPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(activity)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + activity?.getPackageName())
                )
                startActivityForResult(intent, REQUEST_OVERLAY_CODE)
                return true
            } else {
                return false
            }
        }
        return false
    }

}
