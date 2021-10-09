/*
 * Copyright (c) 2020. 上海感悟通信科技有限公司
 * All rights reserved
 * Project：LeapCollect
 * Last Modify：2020年7月16日 17:38:39
 * Author：上海感悟通信科技有限公司
 * http://www.sensethink.com
 */

package com.ggh.baselibrary.utils;

import android.app.AppOpsManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.ggh.baselibrary.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * author : Gecx
 * date : 2019-06-13
 * description :
 */
public class NotificationHelper {

/*
    private static final String TAG = "NotificationHelper";

    public final static int id = 10;
    private final static String channelId = "com.senseplay.leapcollect";
    //    private final static String channelId =id+"";
    private NotificationManager notificationManager;

    private static NotificationHelper instance;
    private NotificationCompat.Builder mBuilder;

    private RemoteViews mRemoteViews;


    public static NotificationHelper getInstance() {
        if (instance == null) {
            synchronized (NotificationHelper.class) {
                if (instance == null) {
                    instance = new NotificationHelper();
                }
            }
        }
        return instance;
    }

    private NotificationHelper() {
    }

    public NotificationCompat.Builder init() {
        try {
            if (notificationManager == null) {
                notificationManager = (NotificationManager) UIUtil.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(channelId, "LeapCollect", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                }
            }

            if (mBuilder == null) {

                mBuilder = new NotificationCompat.Builder(UIUtil.getContext(), channelId);
                //设置通知的小图标
                mBuilder.setSmallIcon(R.mipmap.logo);
                mBuilder.setWhen(System.currentTimeMillis());
                //设置通知的大图标，当下拉系统状态栏时，就可以看到设置的大图标
                mBuilder.setLargeIcon(BitmapFactory.decodeResource(UIUtil.getResources(), R.mipmap.logo));

                Intent intent = new Intent(UIUtil.getContext(), HomeActivity.class);
                PendingIntent pi = PendingIntent.getActivity(UIUtil.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(pi);

                mBuilder.setOnlyAlertOnce(true);
                //设置通知的标题
                mRemoteViews = new RemoteViews(UIUtil.getContext().getPackageName(), R.layout.view_notify);
                mRemoteViews.setTextViewText(R.id.tv_notify_content, "LeapCollect");
                mRemoteViews.setTextViewText(R.id.tv_notify_time, TimeUUtiles.getCurrTime("HH:mm"));

                mBuilder.setContent(mRemoteViews);
            }
            notificationManager.notify(id, mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mBuilder;
    }

    public void notify(ChatInfo chatInfo) {

        if(chatInfo==null){
            return;
        }
        if (notificationManager == null || mBuilder==null) {
            init();
        }

        String content = chatInfo.getContent();

        if (chatInfo.getContentType() == MsgConstant.TYPE_CONTENT_MAP) {
            content = UIUtil.getString(R.string.receiver_location);
        }
        notify(content);
        if (!HomeActivity.resume) {
            notifyVibrator();
        }
    }

    public void notify(String content) {

        if (notificationManager == null || mBuilder==null) {
            init();
        }
        if (mRemoteViews == null) {
            return;
        }

        try {
            Intent intent = new Intent(UIUtil.getContext(), User.myUid==0?SplashActivtiy.class:HomeActivity.class);
            PendingIntent pi = PendingIntent.getActivity(UIUtil.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pi);

            mRemoteViews.setTextViewText(R.id.tv_notify_content, content);
            mRemoteViews.setTextViewText(R.id.tv_notify_time, TimeUUtiles.getCurrTime("HH:mm"));

            //设置通知的标题  这种不能改时间
//            mBuilder.setContentText(content);
//            mBuilder.setWhen(System.currentTimeMillis());
            notificationManager.notify(id, mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notify(int id, String content) {

        if (notificationManager == null || mBuilder==null) {
            init();
        }

        if (mRemoteViews == null) {
            return;
        }

        try {

            Intent intent = new Intent(UIUtil.getContext(), User.myUid==0?SplashActivtiy.class:HomeActivity.class);
            PendingIntent pi = PendingIntent.getActivity(UIUtil.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pi);
            mRemoteViews.setTextViewText(R.id.tv_notify_content, content);
            mRemoteViews.setTextViewText(R.id.tv_notify_time, TimeUUtiles.getCurrTime("HH:mm"));

            //设置通知的标题  这种不能改时间
//            mBuilder.setContentText(content);
//            mBuilder.setWhen(System.currentTimeMillis());
            notificationManager.notify(id, mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notifyVibrator() {
        vibrator();

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(UIUtil.getContext(), notification);
        r.play();
    }

    public static void vibrator() {
        Vibrator vibrator = (Vibrator) UIUtil.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(100);
        }

    }

    public NotificationCompat.Builder getBuild() {
        return mBuilder;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isNotificationEnabled(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //8.0手机以上
            if (((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).getImportance() == NotificationManager.IMPORTANCE_NONE) {
                return false;
            }
        }

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;

        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void cancelNotify() {
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }

    }
*/
}
