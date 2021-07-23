package com.ggh.maplibrary.util;

import android.content.Context;

import com.amap.api.location.AMapLocation;

/**
 * @Author Create by mcl
 * @Date 2020/4/2
 * @ClassName GDMapTest
 * @描述
 */
public class GDMapTest {

    public void main(Context context){
        // 定位工具初始化    在Application类的onCreate方法中进行初始化
        GDMapLacationUtil.init(context);


        // 获取之前定位位置，如果之前未曾定位，则重新定位
//        GDMapLacationUtil.getLocation();
//
//
//        // 获取当前位置，无论是否定位过，重新进行定位
//        GDMapLacationUtil.getCurrentLocation(new GDMapLacationUtil.GDMapLocationListener() {
//
//            @Override
//            public void result(AMapLocation location) {
//                //针对location进行相关操作，如location.getCity()，无需验证location是否为null;
//            }
//        });


    }


}
