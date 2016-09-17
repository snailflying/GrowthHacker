package cn.mw.growthhacker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zxinsight.MarketingHelper;
import com.zxinsight.mlink.annotation.MLinkRouter;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.R;
import cn.mw.growthhacker.domain.O2ODetail;
import cn.mw.growthhacker.utils.AppPrefs;


@MLinkRouter(keys = {"second", "O2Odetail"})
public class O2ODetailActivity extends BaseActivity {

    Toolbar toolbar;

    ImageView o2oHeader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o2o_detail);
        initToolBar();
        displayImage();
        Intent intent = getIntent();
//        if(intent!=null){
//            String name = intent.getStringExtra("name");
//            Toast.makeText(this,"name = "+name,Toast.LENGTH_LONG).show();
//
//        }
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.o2o_detail);
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
        o2oHeader = (ImageView) findViewById(R.id.header);
        O2ODetail detail = AppPrefs.get(this).getO2ODetail();
        ImageLoader.getInstance().displayImage(detail.detail, o2oHeader);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (MarketingHelper.currentMarketing(this).isActive(Config.MW_O2O_SHARE)) {
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
            if (MarketingHelper.currentMarketing(this).isActive(Config.MW_O2O_SHARE)) {
                MarketingHelper.currentMarketing(this).click(this, Config.MW_O2O_SHARE);
            } else {
                Toast.makeText(O2ODetailActivity.this, R.string.share_closed, Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);

    }
}
