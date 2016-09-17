package cn.mw.growthhacker.view.tab;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.media.VolumeProviderCompat;
import android.util.TypedValue;


public class Utils {

    /**
     * Drawable转换成Bitmap
     *
     * @param drawable {@link Drawable}
     * @return {@link Bitmap}
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 获取自定义属性的资源ID
     *
     * @param context 上下文
     * @param attrRes 自定义属性
     * @return resourceId
     */
    public static int getResourceId(@NonNull Context context, @VolumeProviderCompat.ControlType int attrRes) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return typedValue.resourceId;
    }

    /**
     * 获取颜色类自定义属性的颜色值
     *
     * @param context 上下文
     * @param attrRes 自定义属性
     * @return A single color value in the form 0xAARRGGBB
     */
    public static int getAttrColor(@NonNull Context context, @VolumeProviderCompat.ControlType int attrRes) {
        return ContextCompat.getColor(context, getResourceId(context, attrRes));
    }

    /**
     * 获取Drawable类自定义属性的drawable
     *
     * @param context 上下文
     * @param attrRes 自定义属性
     * @return Drawable An object that can be used to draw this resource.
     */
    public static Drawable getAttrDrawable(@NonNull Context context, @VolumeProviderCompat.ControlType int attrRes) {
        return ContextCompat.getDrawable(context, getResourceId(context, attrRes));
    }


    public static int dp2sp(Context context, float dipValue) {
        float pxValue = dp2px(context, dipValue);
        return px2sp(context, pxValue);
    }

    public static int sp2dp(@NonNull Context context, float spValue) {
        float pxValue = sp2px(context, spValue);
        return px2dp(context, pxValue);
    }

    public static int px2dp(@NonNull Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale);
    }

    public static float dp2px(@NonNull Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dipValue * scale);
    }

    public static int px2sp(@NonNull Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale);
    }

    public static int sp2px(@NonNull Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale);
    }

    public static boolean selfPermissionGranted(Context context, String permission) {
        // For Android < Android M, self permissions are always granted.
        boolean result = true;

        int targetSdkVersion = 0;
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= Android M, we can
                // use Context#checkSelfPermission
                result = context.checkSelfPermission(permission)
                        == PackageManager.PERMISSION_GRANTED;
            } else {
                // targetSdkVersion < Android M, we have to use PermissionChecker
                result = PermissionChecker.checkSelfPermission(context, permission)
                        == PermissionChecker.PERMISSION_GRANTED;
            }
        }

        return result;
    }

}
