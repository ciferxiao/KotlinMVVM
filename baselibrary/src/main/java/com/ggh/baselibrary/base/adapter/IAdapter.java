package com.ggh.baselibrary.base.adapter;


import com.ggh.app.adapter.item.AdapterItem;

import java.util.List;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 孟从伦
 * 文件名：IAdapter.java
 * 创建时间：2020/11/4
 * 功能描述： 通用的adapter必须实现的接口，作为方法名统一的一个规范
 */
public interface IAdapter<T> {

    /**
     * @param data 设置数据源
     */
    void setData(@NonNull List<T> data);

    /**
     * 获取数据源
     * @return
     */
    List<T> getData();

    /**
     * @param t list中的一条数据
     * @return 强烈建议返回string, int, bool类似的基础对象做type，不要返回data中的某个对象
     */
    @NonNull
    Object getItemType(T t);

    /**
     * 当缓存中无法得到所需item时才会调用
     *
     * @param type 通过{@link #getItemType(Object)}得到的type
     * @return 任意类型的 AdapterItem
     */
    @Keep
    @NonNull
    AdapterItem<T> createItem(@NonNull Object type);

    /**
     * 得到当前要渲染的最后一个item的position
     */
    int getCurrentPosition();
}
