package com.ggh.baselibrary.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.ggh.baselibrary.R;

public class DialogUtil {
    /**
     * 用于网络请求等耗时操作的LoadingDialog
     */
    public static Dialog loadingDialog(Context context, String text) {
        Dialog dialog = new Dialog(context, R.style.dialog_style);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (!TextUtils.isEmpty(text)) {
            TextView titleView = dialog.findViewById(R.id.text);
            if (titleView != null) {
                titleView.setText(text);
            }
        }
        return dialog;
    }

    public static Dialog loadingDialog(Context context) {
        return loadingDialog(context, "loading...");
    }

    public static void show(Dialog dialog) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public static void dismiss(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
