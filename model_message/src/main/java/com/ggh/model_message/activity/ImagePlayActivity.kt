package com.ggh.model_message.activity

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ggh.model_message.R
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.android.material.transition.platform.MaterialFadeThrough
import kotlinx.android.synthetic.main.activity_video_play.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class ImagePlayActivity :AppCompatActivity(R.layout.activity_video_play) {

    companion object{
        fun openActivity(context: Context?, id: Long, view: View, transitionName: String){
            val intent = Intent(context, ImagePlayActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("transition_name", transitionName)
            if(context is Activity){
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context, view, transitionName).toBundle())
            }else {
                context?.startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. 设置动画
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        postponeEnterTransition()

        super.onCreate(savedInstanceState)

        // 2. 设置transitionName
        image.transitionName = intent.getStringExtra("transition_name")
        // 3. 设置具体的动画
        window.sharedElementEnterTransition = MaterialFadeThrough().apply {
            addTarget(image)
            duration = 300L
        }
        window.sharedElementExitTransition = MaterialContainerTransform().apply {
            addTarget(image)
            duration = 300L
        }

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
            .into(image)
    }
}