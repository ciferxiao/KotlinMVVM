package com.ggh.baselibrary.utils.image;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.ggh.baselibrary.R;
import com.ggh.baselibrary.base.BaseDralogFragment;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.language.LanguageConfig;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：ImageSelectDialog
 * 创建时间：2020/6/5
 * 功能描述：   图片选择弹框
 * 可选择相册打开与相机
 */
public class ImageSelectDialog extends BaseDralogFragment implements View.OnClickListener {
    private TextView tvPhoto;
    private TextView tvCamera;
    private TextView tvCancel;


    //选择类型    PictureMimeType.ofImage()图片
    private int chooseType;
    //单选或者多选   ture单选   flase多选
    private boolean isSingle;
    //是否压缩
    private boolean isCompress;
    //图片裁剪的宽高
    private int wight;
    private int height;
    private boolean isCircleCrop;
    //回调监听
    private OnSelectCallBackListener onSelectCallBackListener;

    private OnSelectOpenPhotoListener onSelectOpenPhotoListener;
    private OnSelectOpenCameraListener onSelectOpenCameraListener;

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_select_img;
    }

    @Override
    public int setWidth() {
        return LinearLayout.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        tvPhoto = mRootView.findViewById(R.id.select_picture);
        tvCamera = mRootView.findViewById(R.id.select_camera);
        tvCancel = mRootView.findViewById(R.id.select_cancle);

        tvPhoto.setOnClickListener(this);
        tvCamera.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.select_picture) {
            if (onSelectOpenPhotoListener == null){
                openPhoto();
            }else {
                onSelectOpenPhotoListener.clickOpenPhoto();
            }

        } else if (v.getId() == R.id.select_camera) {
            if (onSelectOpenCameraListener == null){
                openCamera();
            }else {
                onSelectOpenCameraListener.clickOpenPhoto();
            }
        } else if (v.getId() == R.id.select_cancle) {
            dismiss();
        }
    }

    /**
     * 打开相册选择   可选图片  视频
     */
    private void openPhoto() {
        dismiss();
        PictureSelector.create(this)
                .openGallery(chooseType)//类型
                .loadImageEngine(GlideEngine.createGlideEngine())//加载器
                .selectionMode(isSingle ? PictureConfig.SINGLE : PictureConfig.MULTIPLE)//是否单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(true)// 是否裁剪
                .compress(isCompress)// 是否压缩
                .freeStyleCropEnabled(isCircleCrop ? false : true)//裁剪框是否可拖拽  圆形裁剪建议去除
                .circleDimmedLayer(isCircleCrop)//圆形剪裁
                .showCropGrid(isCircleCrop ? false : true)//是否显示裁剪网格  圆形裁剪建议去除
                .showCropFrame(isCircleCrop ? false : true)//是否显示裁剪边框  圆形裁剪建议去除
                .setPictureWindowAnimationStyle(ImageSelectConfig.getInstance().getPictureWindowAnimationStyle()) // 自定义相册启动退出动画
                .setPictureStyle(ImageSelectConfig.getInstance().getPictureParameterStyle()) // 动态自定义相册主题
                .setPictureCropStyle(ImageSelectConfig.getInstance().getPictureCropParameterStyle()) // 动态自定义裁剪主题
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .withAspectRatio(wight, height)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .setLanguage(LanguageConfig.CHINESE)//国际化语言
                .forResult(new OnResultCallbackListener() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        List<String> mList = new ArrayList<>();
                        for (LocalMedia media : result) {
                            //判断版本是否为Q 版本
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                mList.add(media.getAndroidQToPath());
                            } else {
                                if (media.isCut()) {//是否剪切
                                    mList.add(media.getCutPath());
                                } else if (media.isCompressed()) {//是否压缩
                                    mList.add(media.getCompressPath());
                                } else {
                                    mList.add(media.getPath());
                                }
                            }
                        }


                        if (onSelectCallBackListener != null) {
                            onSelectCallBackListener.selectCallBack(mList);
                        }
                    }
                });
    }

    /**
     * 打开相机
     */
    private void openCamera() {
        dismiss();
        PictureSelector.create(this)
                .openCamera(chooseType)//类型
                .loadImageEngine(GlideEngine.createGlideEngine())//加载器
                .selectionMode(isSingle ? PictureConfig.SINGLE : PictureConfig.MULTIPLE)//是否单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(true)// 是否裁剪
                .compress(isCompress)// 是否压缩
                .freeStyleCropEnabled(isCircleCrop ? false : true)//裁剪框是否可拖拽  圆形裁剪建议去除
                .circleDimmedLayer(isCircleCrop)//圆形剪裁
                .showCropGrid(isCircleCrop ? false : true)//是否显示裁剪网格  圆形裁剪建议去除
                .showCropFrame(isCircleCrop ? false : true)//是否显示裁剪边框  圆形裁剪建议去除
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .withAspectRatio(wight, height)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .setLanguage(LanguageConfig.CHINESE)//国际化语言
                .forResult(new OnResultCallbackListener() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        List<String> mList = new ArrayList<>();
                        for (LocalMedia media : result) {
                            //判断版本是否为Q 版本
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                mList.add(media.getAndroidQToPath());
                            } else {
                                if (media.isCut()) {//是否剪切
                                    mList.add(media.getCutPath());
                                } else if (media.isCompressed()) {//是否压缩
                                    mList.add(media.getCompressPath());
                                } else {
                                    mList.add(media.getPath());
                                }
                            }
                        }


                        if (onSelectCallBackListener != null) {
                            onSelectCallBackListener.selectCallBack(mList);
                        }
                    }
                });
    }

    /**
     * 清楚缓存的路劲与剪切压缩后的图片
     */
    public void cleanPath() {
        //包括裁剪和压缩后的缓存，要在上传成功后调用，type 指的是图片or视频缓存取决于你设置的ofImage或ofVideo 注意：需要系统sd卡权限
        PictureFileUtils.deleteCacheDirFile(mContext, chooseType);
        // 清除所有缓存 例如：压缩、裁剪、视频、音频所生成的临时文件
        PictureFileUtils.deleteAllCacheDirFile(mContext);
    }

    public void show(FragmentManager manager) {
        show(manager, TAG);
    }

    /**
     * 选择结果回调监听
     */
    public interface OnSelectCallBackListener {
        void selectCallBack(List<String> result);
    }
    /**
     * 点击相册监听
     */
    public interface OnSelectOpenPhotoListener {
        void clickOpenPhoto();
    }
    /**
     * 点击相机监听
     */
    public interface OnSelectOpenCameraListener {
        void clickOpenPhoto();
    }

    public ImageSelectDialog(Builder builder) {
        this.chooseType = builder.chooseType;
        this.isSingle = builder.isSingle;
        this.isCompress = builder.isCompress;
        this.wight = builder.wight;
        this.height = builder.height;
        this.isCircleCrop = builder.isCircleCrop;
        this.onSelectCallBackListener = builder.onSelectCallBackListener;
        this.onSelectOpenPhotoListener = builder.onSelectOpenPhotoListener;
        this.onSelectOpenCameraListener = builder.onSelectOpenCameraListener;
    }

    public static class Builder {
        //选择类型    PictureMimeType.ofImage()图片
        private int chooseType = PictureMimeType.ofImage();
        //单选或者多选   ture单选   flase多选
        private boolean isSingle = true;
        //是否压缩
        private boolean isCompress = true;
        //图片裁剪的宽高   如16:9 3:2 3:4 1:1 可自定义
        private int wight = 1;
        private int height = 1;
        //是否圆形剪裁
        private boolean isCircleCrop = false;
        private OnSelectCallBackListener onSelectCallBackListener;
        private OnSelectOpenPhotoListener onSelectOpenPhotoListener;
        private OnSelectOpenCameraListener onSelectOpenCameraListener;

       public Builder setChooseType(int chooseType) {  // PictureMimeType.ofImage()
            this.chooseType = chooseType;
            return this;
        }

        public Builder setSingle(boolean single) {
            isSingle = single;
            return this;
        }

        public Builder setCompress(boolean compress) {
            isCompress = compress;
            return this;
        }

        public Builder setWightHeight(int wight, int height) {
            this.wight = wight;
            this.height = height;
            return this;
        }

        public Builder setCircleCrop(boolean circleCrop) {
            isCircleCrop = circleCrop;
            return this;
        }

        public Builder setOnSelectCallBackListener(OnSelectCallBackListener onSelectCallBackListener) {
            this.onSelectCallBackListener = onSelectCallBackListener;
            return this;
        }

        public Builder setOnSelectOpenPhotoListener(OnSelectOpenPhotoListener onSelectOpenPhotoListener) {
            this.onSelectOpenPhotoListener = onSelectOpenPhotoListener;
            return this;
        }

        public Builder setOnSelectOpenCameraListener(OnSelectOpenCameraListener onSelectOpenCameraListener) {
            this.onSelectOpenCameraListener = onSelectOpenCameraListener;
            return this;
        }

        public ImageSelectDialog build() {
            return new ImageSelectDialog(this);
        }
    }
}
