package com.ggh.model_message

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ggh.baselibrary.utils.ToastUtil
import com.ggh.baselibrary.widget.FloatingWindow
import com.ggh.model_message.activity.ImagePlayActivity
import com.ggh.model_message.activity.MapBottomSheetActivity
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.mcl.kotlin_mvvm.base.BaseFragment
import kotlinx.android.synthetic.main.floating_video_view.view.*
import kotlinx.android.synthetic.main.fragment_message.*
import java.util.*

class MessageFragment : BaseFragment(R.layout.fragment_message) {

    lateinit var viewModel: MessageViewModel
    var floatingView: FloatingWindow? = null
    var hasFloatingView = false

    companion object {
        @JvmStatic
        fun newInstance() = MessageFragment()
        const val REQUEST_OVERLAY_CODE: Int = 0x01
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        postponeEnterTransition()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun main() {
        val model: MessageViewModel = ViewModelProviders.of(this).get(MessageViewModel::class.java)

        model.nameListResult.observe(this) {
        }
        model.getNames()
//--------------------------------悬浮窗--------------------------------------------------------
        tv_show.setOnClickListener {
            if (!requestOverlayPermission()) {
                initFloatView()
                hasFloatingView = true
            } else {
                ToastUtil.show("请开启悬浮窗权限")
            }
        }

        tv_hide.setOnClickListener {
            floatingView?.dismiss()
            hasFloatingView = false
        }

//--------------------------------过长动画--------------------------------------------------------
        Glide.with(this)
            .load("https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=f240ee32c7160924c325a41be406359b/a08b87d6277f9e2f43b5946c0830e924b999f3d6.jpg")
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

            })
            .into(iv_transform)
        iv_transform.setOnClickListener {
            val transName = "123-imageView"
            it.transitionName = transName
            ImagePlayActivity.openActivity(context, 123, it as View, transName)
        }

//--------------------------------仿高德map--------------------------------------------------------
        tv_title_three.setOnClickListener{
            MapBottomSheetActivity.openActivity(context = context!!)
        }
    }

    private fun initFloatView() {
        if (hasFloatingView) {
            return
        }

        if (floatingView == null) {
            floatingView = FloatingWindow()
        }
        val view = View.inflate(this.context, R.layout.floating_video_view, null)
        Glide.with(this).load(R.drawable.picture_audio_placeholder).into(view.thumb_floating_view)

        view.video_view.let {
            it.setVideoPath("https://stream7.iqilu.com/10339/article/202002/18/2fca1c77730e54c7b500573c2437003f.mp4")
            /*val uri = "android.resource://" + activity?.packageName + "/" + R.raw.login
            it.setVideoURI(Uri.parse(uri))*/
            it.setOnPreparedListener { view.thumb_floating_view.visibility = View.GONE }
            it.setOnCompletionListener {
                it.start()
                it.isLooping = true
            }
            it.start()
        }
        view.close_floating_view.setOnClickListener {
            floatingView?.dismiss()
            hasFloatingView = false
        }
        view.back_floating_view.setOnClickListener {
            context?.let { it1 ->
                floatingView?.setTopApp(it1)
            }
        }

        if (view.isAttachedToWindow) {
            return
        }
        context?.let { floatingView?.showFloatingWindowView(it, view) }

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
