package cn.mw.growthhacker.view.tab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;


/**
 * 开放的导航按钮构建入口
 */
public class TabItemBuilder {
    private TabItem mTabItem;

    private Context mContext;

    public TabItemBuilder(@NonNull Context context) {
        mContext = context;
    }

    public TabItemBuild create() {
        mTabItem = new TabItem(mContext);
        return mTabItem.builder(this);
    }

    protected TabItem getTabItem() {
        return mTabItem;
    }

    public static TabItem getItem(Context context, @DrawableRes int drawable, @DrawableRes int selectedDrawable, @NonNull int text, @ColorInt int selectedColor) {
        return getItem(context, ContextCompat.getDrawable(context, drawable), ContextCompat.getDrawable(context, selectedDrawable), context.getString(text), selectedColor);
    }


    public static TabItem getItem(Context context, @DrawableRes int drawable, @DrawableRes int selectedDrawable, @NonNull String text, @ColorInt int selectedColor) {
        return getItem(context, ContextCompat.getDrawable(context, drawable), ContextCompat.getDrawable(context, selectedDrawable), text, selectedColor);
    }

    public static TabItem getItem(Context context, Drawable drawable, Drawable selectedDrawable, String text, int selectedColor) {

        if (selectedDrawable == null) {
            selectedDrawable = drawable;
        }
        return new TabItemBuilder(context).create()
                .setDefaultIcon(drawable)
                .setSelectedIcon(selectedDrawable)
                .setText(text)
                .setSelectedColor(selectedColor)
                .build().getTabItem();
    }
}
