package com.ggh.baselibrary.ext

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：activity
 * 创建时间：2020/9/2
 * 功能描述：
 */

 fun <T : ViewDataBinding> AppCompatActivity.binding(@LayoutRes resId: Int): T =
    DataBindingUtil.setContentView<T>(this, resId).apply {
        lifecycleOwner = this@binding
    }

 fun <T : ViewDataBinding> AppCompatActivity.bindingInflate(inflater: LayoutInflater = layoutInflater, @LayoutRes resId: Int, viewGroup: ViewGroup? = null): T =
    DataBindingUtil.inflate<T>(inflater, resId,viewGroup,false).apply {
        lifecycleOwner = this@bindingInflate
    }

fun <T>AppCompatActivity.frowrand(clazz: Class<T>){
    startActivity(Intent(this,clazz))
}
