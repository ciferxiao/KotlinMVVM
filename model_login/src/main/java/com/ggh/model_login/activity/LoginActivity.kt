package com.ggh.model_login.activity

import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ggh.baselibrary.base.activity.BaseDBActivity
import com.ggh.baselibrary.ext.froward
import com.ggh.librarycommmon.constant.ARouterConstant
import com.ggh.model_login.R
import com.ggh.model_login.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

/**
 *  * description : 
 *  * Author : Xiao
 *  * Date : 2021-07-13
 *  
 **/
@Route(path = ARouterConstant.PATH_LOGIN)
class LoginActivity : BaseDBActivity<ViewDataBinding>(R.layout.activity_login) {

    private var isPrepared = false
    private var mFrom : String? = null

    override fun main() {
        initData()

        video.let {
            val uri = "android.resource://" + packageName + "/" + R.raw.login
            it.setVideoURI(Uri.parse(uri))
            it.setOnPreparedListener {
                isPrepared = true
                it.setOnInfoListener(object : MediaPlayer.OnInfoListener {
                    override fun onInfo(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                            video.setBackgroundColor(Color.TRANSPARENT)
                        }
                        return true
                    }
                })
            }
            it.setOnCompletionListener {
                it.start()
                it.isLooping = true
            }

            it.setOnErrorListener(object :MediaPlayer.OnErrorListener{
                override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
                    it.visibility = View.GONE
                    scroll.visibility = View.VISIBLE
                    scroll.setOnTouchListener(object : View.OnTouchListener{
                        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                            return true
                        }
                    })
                    return true
                }
            })
            it.requestFocus()
            it.start()
        }

        initListener()
    }

    private fun initData() {
        mFrom = intent.getStringExtra("from")
        if(TextUtils.isEmpty(mFrom)){
            return
        }
    }

    fun initListener(){
        mb_login.setOnClickListener {
            ARouterConstant.PATH_MAIN.froward()
        }
    }

    override fun onResume() {
        super.onResume()
        if(video  == null){
            return
        }
        if(isPrepared && ! video.isPlaying){
            video.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if(video  == null){
            return
        }
        if(isPrepared && !video.isPlaying){
            video.pause()
        }
    }
}