package cn.mw.growthhacker.activity;

import android.support.v7.app.AppCompatActivity;

import cn.magicwindow.Session;


/**
 * Created by aaron on 16/9/14.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onPause() {
        Session.onPause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        Session.onResume(this);
        super.onResume();
    }
}
