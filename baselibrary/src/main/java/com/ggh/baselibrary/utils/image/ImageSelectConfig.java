package com.ggh.baselibrary.utils.image;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.ggh.baselibrary.BaseApp;
import com.ggh.baselibrary.R;
import com.luck.picture.lib.style.PictureCropParameterStyle;
import com.luck.picture.lib.style.PictureParameterStyle;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;

/**
 * @Author Create by mcl
 * @Date 2020/6/5
 * @ClassName ImageSelectConfig
 * @描述
 */
public class ImageSelectConfig {

    //相册进入与退出动画   配置全局
    private static PictureWindowAnimationStyle windowAnimationStyle;
    // 相册主题   配置全局
    private static PictureParameterStyle mPictureParameterStyle;
    // 裁剪主题
    private static PictureCropParameterStyle mCropParameterStyle;

    private static Context mContext;

    static {
        mContext = BaseApp.instance;
    }

    //单例封装对象
    public static ImageSelectConfig getInstance(){
        return ImageSelectConfigHoder.instance;
    }
    private static class ImageSelectConfigHoder{
        public static ImageSelectConfig instance = new ImageSelectConfig();
    }

    /**
     * 相册启动退出动画
     * @return
     */
    public PictureWindowAnimationStyle getPictureWindowAnimationStyle(){
        windowAnimationStyle = new PictureWindowAnimationStyle();
        windowAnimationStyle.ofAllAnimation(R.anim.picture_anim_up_in, R.anim.picture_anim_down_out);
        return windowAnimationStyle;
    }

    /**
     * 剪裁主题
     * @return
     */
    public PictureCropParameterStyle getPictureCropParameterStyle() {
        mCropParameterStyle = new PictureCropParameterStyle(
                ContextCompat.getColor(mContext, R.color.black),
                ContextCompat.getColor(mContext, R.color.black),
                ContextCompat.getColor(mContext, R.color.white),
                mPictureParameterStyle.isChangeStatusBarFontColor);
        return mCropParameterStyle;
    }

    /**
     * 这里可以配置全局相册主题
     */
    public PictureParameterStyle getPictureParameterStyle() {
        mPictureParameterStyle = new PictureParameterStyle();
        // 是否改变状态栏字体颜色(黑白切换)
        mPictureParameterStyle.isChangeStatusBarFontColor = false;
        // 是否开启右下角已完成(0/9)风格
        mPictureParameterStyle.isOpenCompletedNumStyle = false;
        // 是否开启类似QQ相册带数字选择风格
        mPictureParameterStyle.isOpenCheckNumStyle = false;
        // 相册状态栏背景色
        mPictureParameterStyle.pictureStatusBarColor = Color.parseColor("#393a3e");
        // 相册列表标题栏背景色
        mPictureParameterStyle.pictureTitleBarBackgroundColor = Color.parseColor("#393a3e");
        // 相册列表标题栏右侧上拉箭头
        mPictureParameterStyle.pictureTitleUpResId = R.drawable.picture_icon_arrow_up;
        // 相册列表标题栏右侧下拉箭头
        mPictureParameterStyle.pictureTitleDownResId = R.drawable.picture_icon_arrow_down;
        // 相册文件夹列表选中圆点
        mPictureParameterStyle.pictureFolderCheckedDotStyle = R.drawable.picture_orange_oval;
        // 相册返回箭头
        mPictureParameterStyle.pictureLeftBackIcon = R.drawable.picture_icon_back;
        // 标题栏字体颜色
        mPictureParameterStyle.pictureTitleTextColor = ContextCompat.getColor(mContext, R.color.picture_color_white);
        // 相册右侧取消按钮字体颜色
        mPictureParameterStyle.pictureCancelTextColor = ContextCompat.getColor(mContext, R.color.picture_color_white);
        // 相册列表勾选图片样式
        mPictureParameterStyle.pictureCheckedStyle = R.drawable.picture_checkbox_selector;
        // 选择相册目录背景样式
//        mPictureParameterStyle.pictureAlbumStyle = R.drawable.picture_new_item_select_bg;
        // 相册列表底部背景色
        mPictureParameterStyle.pictureBottomBgColor = ContextCompat.getColor(mContext, R.color.picture_color_fa);
        // 已选数量圆点背景样式
        mPictureParameterStyle.pictureCheckNumBgStyle = R.drawable.picture_num_oval;
        // 相册列表底下预览文字色值(预览按钮可点击时的色值)
        mPictureParameterStyle.picturePreviewTextColor = ContextCompat.getColor(mContext, R.color.picture_color_fa632d);
        // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
        mPictureParameterStyle.pictureUnPreviewTextColor = ContextCompat.getColor(mContext, R.color.picture_color_9b);
        // 相册列表已完成色值(已完成 可点击色值)
        mPictureParameterStyle.pictureCompleteTextColor = ContextCompat.getColor(mContext, R.color.picture_color_fa632d);
        // 相册列表未完成色值(请选择 不可点击色值)
        mPictureParameterStyle.pictureUnCompleteTextColor = ContextCompat.getColor(mContext, R.color.picture_color_9b);
        // 预览界面底部背景色
        mPictureParameterStyle.picturePreviewBottomBgColor = ContextCompat.getColor(mContext, R.color.picture_color_grey_3e);
        // 外部预览界面删除按钮样式
        mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_delete;
        // 外部预览界面是否显示删除按钮
        mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true;
        // 相册右侧按钮背景样式,只针对isWeChatStyle 为true时有效果
        mPictureParameterStyle.pictureUnCompleteBackgroundStyle = R.drawable.picture_send_button_default_bg;
        // 相册右侧按钮可点击背景样式,只针对isWeChatStyle 为true时有效果
        mPictureParameterStyle.pictureCompleteBackgroundStyle = R.drawable.picture_send_button_bg;

        // 以下设置如果用不上则不要设置，使用默认即可
        // 自定义相册右侧文本内容设置
        mPictureParameterStyle.pictureRightDefaultText = "";
        // 自定义相册未完成文本内容
        mPictureParameterStyle.pictureUnCompleteText = "";
        // 完成文案是否采用(%1$d/%2$d)的字符串，只允许两个占位符哟
        mPictureParameterStyle.isCompleteReplaceNum = true;
        // 自定义相册完成文本内容
        mPictureParameterStyle.pictureCompleteText = "";
        // 自定义相册列表不可预览文字
        mPictureParameterStyle.pictureUnPreviewText = "";
        // 自定义相册列表预览文字
        mPictureParameterStyle.picturePreviewText = "";
        // 自定义相册标题字体大小
        mPictureParameterStyle.pictureTitleTextSize = 18;
        // 自定义相册右侧文字大小
        mPictureParameterStyle.pictureRightTextSize = 14;
        // 自定义相册预览文字大小
        mPictureParameterStyle.picturePreviewTextSize = 14;
        // 自定义相册完成文字大小
        mPictureParameterStyle.pictureCompleteTextSize = 14;
// 自定义原图文字大小
        mPictureParameterStyle.pictureOriginalTextSize = 14;


        return mPictureParameterStyle;

    }
}
