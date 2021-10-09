package com.ggh.model_login.activity

import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.MotionEvent
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.alibaba.android.arouter.facade.annotation.Route
import com.ggh.baselibrary.base.activity.BaseDBActivity
import com.ggh.baselibrary.ext.froward
import com.ggh.baselibrary.utils.ToastUtil
import com.ggh.librarycommmon.constant.ARouterConstant
import com.ggh.model_login.R
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
    private var mFrom: String? = null
    lateinit var model: LoginViewModel

    public inline fun<T,R> T.let(block:(T) ->R):R = block(this)


    override fun main() {
        model = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        model.accountBean.observe(this) {
            it?.let {//表示accountBean不为空时进行
                Log.d("xiao111"," it $it")
                ARouterConstant.PATH_MAIN.froward()
            }
        }
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

            it.setOnErrorListener(object : MediaPlayer.OnErrorListener {
                override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
                    it.visibility = View.GONE
                    scroll.visibility = View.VISIBLE
                    scroll.setOnTouchListener(object : View.OnTouchListener {
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
        if (TextUtils.isEmpty(mFrom)) {
            return
        }
    }

    fun initListener() {
        mb_login.setOnClickListener {
            /*if(et_veri.text.length < 6){
                ToastUtil.show("23333333")
                return@setOnClickListener
            }*/
            model.login("13661610000","123456",et_veri.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        if (video == null) {
            return
        }
        if (isPrepared && !video.isPlaying) {
            video.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (video == null) {
            return
        }
        if (isPrepared && !video.isPlaying) {
            video.pause()
        }
    }
}