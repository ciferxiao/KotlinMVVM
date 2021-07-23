package com.ggh.baselibrary.utils

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import com.ggh.baselibrary.BaseApp
import com.ggh.baselibrary.listener.SimpleAnimationListener

open class AnimateUtil {

     companion object{
         fun loadXmlAniSet(view: View?, res: Int,context : Context?): AnimatorSet? {
             val set = AnimatorInflater.loadAnimator(
                 context,
                 res
             ) as AnimatorSet
             set.setTarget(view)
             set.start()
             return set
         }

         fun loadXmlAni(view: View, @AnimRes res: Int) {
             val animation =
                 AnimationUtils.loadAnimation(BaseApp.instance, res)
             view.startAnimation(animation)
         }

         fun loadXmlAni(view: View,@AnimRes res: Int,dismiss: Boolean) {
             view.visibility = View.VISIBLE
             val animation =
                 AnimationUtils.loadAnimation(BaseApp.instance, res)
             animation.duration = 500
             animation.fillAfter = true
             view.startAnimation(animation)
             animation.setAnimationListener(object : SimpleAnimationListener() {
                 override fun onAnimationEnd(animation: Animation?) {
                     super.onAnimationEnd(animation)
                     if (dismiss) {
                         view.clearAnimation()
                         view.visibility = View.GONE
                     }
                 }
             })
         }
     }

}