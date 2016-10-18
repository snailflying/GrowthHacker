package cn.mw.growthhacker.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cn.magicwindow.MLink;
import cn.magicwindow.MLinkAPIFactory;
import cn.magicwindow.MWConfiguration;
import cn.magicwindow.MagicWindowSDK;
import cn.magicwindow.mlink.YYBCallback;
import cn.magicwindow.mlink.annotation.MLinkDefaultRouter;
import cn.mw.growthhacker.R;
import cn.mw.growthhacker.adapter.BottomFragmentPagerAdapter;
import cn.mw.growthhacker.view.tab.Controller;
import cn.mw.growthhacker.view.tab.PagerBottomTabLayout;
import cn.mw.growthhacker.view.tab.TabItemBuilder;
import cn.mw.growthhacker.view.tab.TabLayoutMode;
import cn.mw.growthhacker.view.tab.listener.OnTabItemSelectListener;
import pub.devrel.easypermissions.EasyPermissions;


@MLinkDefaultRouter
public class HomeActivity extends BaseActivity {

    private static final int RC_CAMERA_PERM = 123;
    int[] testColors = {0xFF00796B, 0xFF5B4947, 0xFF607D8B, 0xFFF57C00, 0xFFF57C00};
    private Controller controller;
    private String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initToolBar();
        initImageLoader();
        initBottom();
        cameraTask();
        initMW();
    }


    private void initMW() {
        MLink.getInstance(this).registerWithAnnotation(this);
        if(getIntent().getData()!=null){
            MLink.getInstance(this).router(getIntent().getData());
        } else {
            MLinkAPIFactory.createAPI(this).checkYYB();
        }
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle(R.string.app_name);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initBottom() {

        //Fragment+ViewPager+FragmentViewPager组合的使用
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        BottomFragmentPagerAdapter adapter = new BottomFragmentPagerAdapter(getSupportFragmentManager(),
                this);
        viewPager.setAdapter(adapter);

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (controller != null) {
                    controller.setSelect(position);
                }
                invalidateOptionsMenu();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        PagerBottomTabLayout bottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab_layout);

        //用TabItemBuilder构建一个导航按钮
        TabItemBuilder tabItemBuilder = new TabItemBuilder(this).create()
                .setDefaultIcon(R.drawable.ic_menu_home_normal)
                .setText(R.string.home)
                .setSelectedColor(testColors[0])
                .setTag("A")
                .build();

        //构建导航栏,得到Controller进行后续控制
        controller = bottomTabLayout.builder()
                .addTabItem(tabItemBuilder)
                .addTabItem(R.drawable.ic_menu_camera_normal, R.string.push, testColors[1])
                .addTabItem(R.drawable.ic_menu_find_normal, R.string.activity, testColors[2])
//                .setMode(TabLayoutMode.HIDE_TEXT)
                .setMode(TabLayoutMode.CHANGE_BACKGROUND_COLOR)
//                .setMode(TabLayoutMode.HIDE_TEXT | TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .build();

        controller.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag) {
                Log.i("asd", "onSelected:" + index + "   TAG: " + tag.toString());
                viewPager.setCurrentItem(index);
                invalidateOptionsMenu();
            }

            @Override
            public void onRepeatClick(int index, Object tag) {
                Log.i("asd", "onRepeatClick:" + index + "   TAG: " + tag.toString());
            }
        });

    }


    //初始化网络图片缓存库
    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .showImageForEmptyUri(R.drawable.ic_default)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void cameraTask() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
//        if(Utils.selfPermissionGranted(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
//            return;
//        }
        if (EasyPermissions.hasPermissions(this, perms)) {
            Log.i(TAG, "has permission");
            // Have permissions, do the thing!
        } else {
            Log.i(TAG, "has not permission");

            // Ask for both permissions
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_sms),
                    RC_CAMERA_PERM, perms);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return 1 == controller.getSelected();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.right_menu) {
            Toast.makeText(HomeActivity.this, "已发布", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
