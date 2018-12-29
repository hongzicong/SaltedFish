package hongzicong.saltedfish.utils;

/**
 * Created by Dv00 on 2018/1/4.
 */

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import hongzicong.saltedfish.activity.BaseAddActivity;
import hongzicong.saltedfish.activity.BaseApplication;

public class UIUtils {

    public static Context getContext() {
        return BaseApplication.getContext();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static Thread getMainThread() {
        return BaseApplication.getMainThread();
    }

    public static Handler getMainHandler() {
        return BaseApplication.getMainHandler();
    }

    public static void post(Runnable task) {
        getMainHandler().post(task);
    }

    public static void postDelayed(Runnable task, long delayMillis) {
        getMainHandler().postDelayed(task, delayMillis);
    }

    public static void removeCallbacks(Runnable task) {
        getMainHandler().removeCallbacks(task);
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }


    public Uri getUriFromDrawableRes(Context context, int id) {
        Resources resources = context.getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id);
        return Uri.parse(path);
    }

}
