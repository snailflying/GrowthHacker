package cn.mw.growthhacker.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zxinsight.MWImageView;
import com.zxinsight.MarketingHelper;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.R;
import cn.mw.growthhacker.domain.TravelDetailData;
import cn.mw.growthhacker.utils.AppPrefs;

/**
 * 旅游的详情页面,绑定了uber的mLink服务.</br>
 * Config.MWS[91]:大众点评的魔窗位,跳转到大众点评三亚的美食服务 </br>
 * Config.MWS[100]:携程的魔窗位,跳转到携程的酒店团购服务 </br>
 * Config.MWS[101]:携程的魔窗位,跳转到携程的特价机票服务 </br>
 *
 * @author aaron
 * @date 16/01/14
 */
public class TourDetailActivity extends BaseActivity {

    Toolbar toolbar;

    MWImageView tour_detail_banner;

    ImageView map_img;

    TextView tour_detail_car;

    ImageView food01;

    ImageView food02;

    ImageView food03;

    MWImageView more_food;

    ImageView hotel_img;

    MWImageView order;

    RelativeLayout hotel_layout1;

    RelativeLayout hotel_layout2;

    ImageView travel_img01;

    ImageView travel_img02;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);
        initViews();
        displayImage();
        bindMW();
    }

    private void initViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tour_detail_title);
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

    private void displayImage() {
        tour_detail_banner = (MWImageView) findViewById(R.id.tour_detail_banner);
        map_img = (ImageView) findViewById(R.id.map_img);
        food01 = (ImageView) findViewById(R.id.food01);
        food02 = (ImageView) findViewById(R.id.food02);
        food03 = (ImageView) findViewById(R.id.food03);
        hotel_img = (ImageView) findViewById(R.id.hotel_img);
        travel_img01 = (ImageView) findViewById(R.id.travel_img01);
        travel_img02 = (ImageView) findViewById(R.id.travel_img02);
        TravelDetailData detail = AppPrefs.get(this).getTravelDetail();
        ImageLoader.getInstance().displayImage(detail.banner, tour_detail_banner);
        ImageLoader.getInstance().displayImage(detail.map, map_img);
        ImageLoader.getInstance().displayImage(detail.stay, hotel_img);
        ImageLoader.getInstance().displayImage(detail.travel.get(0), travel_img01);
        ImageLoader.getInstance().displayImage(detail.travel.get(1), travel_img02);
        ImageLoader.getInstance().displayImage(detail.food.get(0), food01);
        ImageLoader.getInstance().displayImage(detail.food.get(1), food02);
        ImageLoader.getInstance().displayImage(detail.food.get(2), food03);


    }

    private void bindMW() {

        //绑定旅游-detail-Uber
//        MaterialRippleLayout.on(tour_detail_car).create();
        tour_detail_car = (TextView) findViewById(R.id.tour_detail_car);
        tour_detail_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarketingHelper.currentMarketing(TourDetailActivity.this).click(TourDetailActivity.this, Config.MW_Uber);
            }
        });

        //绑定旅游-detail01
        more_food = (MWImageView) findViewById(R.id.more_food);
        more_food.bindEvent(this, Config.MW_DianPing);
        //绑定旅游-detail02
        order = (MWImageView) findViewById(R.id.order);
        order.bindEvent(this, Config.MW_Ctrip_Hotel);
        //绑定旅游-detail03
        hotel_layout1 = (RelativeLayout) findViewById(R.id.hotel_layout1);
        hotel_layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarketingHelper.currentMarketing(TourDetailActivity.this).click(TourDetailActivity.this, Config.MW_Ctrip_Flight);
            }
        });
        //绑定旅游-detail03
        hotel_layout2 = (RelativeLayout) findViewById(R.id.hotel_layout2);
        hotel_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarketingHelper.currentMarketing(TourDetailActivity.this).click(TourDetailActivity.this, Config.MW_Ctrip_Flight);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (MarketingHelper.currentMarketing(this).isActive(Config.MW_TOUR_SHARE)) {
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
            if (MarketingHelper.currentMarketing(this).isActive(Config.MW_TOUR_SHARE)) {
                MarketingHelper.currentMarketing(this).click(this, Config.MW_TOUR_SHARE);
            } else {
                Toast.makeText(TourDetailActivity.this, R.string.share_closed, Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);

    }

}
