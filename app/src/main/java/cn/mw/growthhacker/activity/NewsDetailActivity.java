package cn.mw.growthhacker.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import cn.magicwindow.MarketingHelper;
import cn.magicwindow.mlink.annotation.MLinkRouter;
import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.R;


@MLinkRouter(keys = "NewsDetail")
public class NewsDetailActivity extends BaseActivity {
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initToolBar();
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.news_detail);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        if(MarketingHelper.currentMarketing(this).isActive(Config.MW_NEWS_SHARE)){
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
            if (MarketingHelper.currentMarketing(this).isActive(Config.MW_NEWS_SHARE)) {
                MarketingHelper.currentMarketing(this).click(this, Config.MW_NEWS_SHARE);
            } else {
                Toast.makeText(NewsDetailActivity.this, R.string.share_closed, Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);

    }
}
