package cn.mw.growthhacker.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zxinsight.MarketingHelper;
import com.zxinsight.TrackAgent;
import com.zxinsight.mlink.annotation.MLinkRouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.R;
import cn.mw.growthhacker.domain.ShopDetail;
import cn.mw.growthhacker.utils.AppPrefs;
import cn.mw.growthhacker.view.NetworkImageHolderView;
import cn.mw.growthhacker.view.banner.ConvenientBanner;
import cn.mw.growthhacker.view.banner.holder.CBViewHolderCreator;

@MLinkRouter(keys = "dianshangDetail")
public class ShopDetailActivity extends BaseActivity {

    Toolbar toolbar;

    ViewPager viewPager;

    ConvenientBanner convenientBanner;

    ImageView shopDetailImg;

    private ShopDetail shopDetail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        initToolBar();
    }

    private void initToolBar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.shop_detail);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //Here you can get the size!
        setViewPager();
    }

    private void setViewPager() {
        shopDetail = AppPrefs.get(this).getShopDetail();
        shopDetailImg = (ImageView) findViewById(R.id.shop_detail_img);
        ImageLoader.getInstance().displayImage(shopDetail.content, shopDetailImg);

//        viewPager.setAdapter(new ImageAdapter(-1, shopDetail.headList, R.drawable.default_640_640));
//        indicator.setViewPager(viewPager);

        List<String> mws = new ArrayList<>();
        mws.add(Config.MW_1);
        mws.add(Config.MW_2);
        mws.add(Config.MW_3);
        mws.add(Config.MW_4);
        Log.e("AppPrefs","shopDetail.headList = "+ shopDetail.headList);
        Log.e("AppPrefs","mws = "+ mws);

        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
        convenientBanner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, shopDetail.headList,mws)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (MarketingHelper.currentMarketing(this).isActive(Config.MW_SHOP_SHARE)) {
            getMenuInflater().inflate(R.menu.menu_shop_detail, menu);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_share) {
            if (MarketingHelper.currentMarketing(this).isActive(Config.MW_SHOP_SHARE)) {
                MarketingHelper.currentMarketing(this).click(this, Config.MW_SHOP_SHARE);
            } else {
//                ToastUtils.showShort(this, R.string.share_closed);
                Toast.makeText(ShopDetailActivity.this, R.string.share_closed, Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);

    }
}
