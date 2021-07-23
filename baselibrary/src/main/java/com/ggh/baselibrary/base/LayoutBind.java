package com.ggh.baselibrary.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：LayoutBind
 * 创建时间：2020/6/27
 * 功能描述：获取布局的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LayoutBind {
    //当只有一个属性的时候，可以使用value来表示，在使用时可以省略掉key和=
    int value();
}