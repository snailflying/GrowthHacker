package cn.mw.growthhacker.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import com.zxinsight.MarketingHelper;
import com.zxinsight.mlink.annotation.MLinkRouter;
import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.R;

@MLinkRouter(keys = "VideoDetail")
public class VideoDetailActivity extends BaseActivity {


    WebView webView;

    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initToolBar();
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }

    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.video_detail);
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


    private void initViews() {
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                         if (url.startsWith("unsafe:")) {
                                             url = url.substring(7);
                                         }
                                         view.loadUrl(url);
                                         return super.shouldOverrideUrlLoading(view, url);
                                     }
                                 }
        );
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(false);
        String url = MarketingHelper.currentMarketing(this).getWebviewURL(Config.MW_VIDEO_SHARE);
        if (TextUtils.isEmpty(url)) {
            url = "http://documentation.magicwindow.cn/demo/video/dist?app=1";
        } else {
            if (url.contains("?")) {
                url = url + "&app=1";
            } else {
                url = url + "?app=1";
            }
        }
        webView.loadUrl(url);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (MarketingHelper.currentMarketing(this).isActive(Config.MW_VIDEO_SHARE)) {
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
            if (MarketingHelper.currentMarketing(this).isActive(Config.MW_VIDEO_SHARE)) {
                MarketingHelper.currentMarketing(this).click(this, Config.MW_VIDEO_SHARE);
            } else {
                Toast.makeText(VideoDetailActivity.this, R.string.share_closed, Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);

    }
}
