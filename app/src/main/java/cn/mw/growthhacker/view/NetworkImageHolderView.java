package cn.mw.growthhacker.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zxinsight.MWImageView;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.R;
import cn.mw.growthhacker.view.banner.holder.Holder;

public class NetworkImageHolderView implements Holder<String> {
    private MWImageView imageView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new MWImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data,String mwKey) {
        imageView.setImageResource(R.drawable.ic_default);
        imageView.bindEvent(mwKey);
//        initViews(position);
        ImageLoader.getInstance().displayImage(data,imageView);
    }

    private void initViews(int position) {
//        Log.e("aaron","banner po = "+mPosition);

        if (position == 2) {
//            Log.e("aaron","banner 03 = "+Config.MW_3);
            imageView.bindEvent(Config.MW_3);
        } else if (position == 0) {
            imageView.bindEvent(Config.MW_1);
        } else {
            imageView.bindEvent(Config.MW_2);
//            homeBg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Toast.makeText(getActivity(), "lalala3", Toast.LENGTH_LONG);
//                }
//            });
        }

    }
}