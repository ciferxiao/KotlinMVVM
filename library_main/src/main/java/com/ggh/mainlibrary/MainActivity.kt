package com.ggh.mainlibrary

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.ggh.baselibrary.ext.toast
import com.ggh.baselibrary.utils.ViewManager
import com.ggh.librarycommmon.constant.ARouterConstant
import com.ggh.model_cart.CartFragment
import com.ggh.model_home.HomeFragment
import com.ggh.model_message.MessageFragment
import com.ggh.model_mine.MineFragment
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.mcl.kotlin_mvvm.base.BaseActivity
import com.senseplay.collectsdk.model.account.AccountBean
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

@Route(path = ARouterConstant.PATH_MAIN)
class MainActivity : BaseActivity(R.layout.activity_main) {


    private val fragmentList: MutableList<Fragment> = ArrayList()
    //业务逻辑
    override fun main() {
        initFragment()
        initViewPager()
        main_user_tab_group.apply {
            setViewPager(main_pager)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false

        super.onCreate(savedInstanceState)
    }

    fun initFragment() {
        fragmentList.add(HomeFragment.newInstance())
        fragmentList.add(MessageFragment.newInstance())
        fragmentList.add(CartFragment.newInstance())
        fragmentList.add(MineFragment.newInstance())
    }

    fun initViewPager() {
        //ViewPager2 设置参数
        main_pager.apply {
            //添加适配器
            adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun getItemCount(): Int = fragmentList.size

                override fun createFragment(position: Int): Fragment = fragmentList[position]

            }
            //禁止滑动
            setUserInputEnabled(false)
        }
    }

    private var lastTime: Long = 0
    private val intervalTime = 1000 * 2.toLong()
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime < intervalTime) {
                ViewManager.getInstance().exitApp(mContext)
            } else {
                lastTime = currentTime
                "再按一次退出程序".toast()
                return false
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}
