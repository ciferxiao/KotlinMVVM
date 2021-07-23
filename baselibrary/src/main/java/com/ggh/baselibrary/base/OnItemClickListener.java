package com.ggh.baselibrary.base;

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：OnItemClickListener
 * 创建时间：2020/7/8
 * 功能描述：
 */
public interface OnItemClickListener<T> {
    void onClickItem(T t, int mPosition);
}
