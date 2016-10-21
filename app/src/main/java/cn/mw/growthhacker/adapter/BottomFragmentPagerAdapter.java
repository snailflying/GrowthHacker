package cn.mw.growthhacker.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.fragment.FindPageFragment;
import cn.mw.growthhacker.fragment.HomePageFragment;
import cn.mw.growthhacker.fragment.PushPageFragment;

public class BottomFragmentPagerAdapter extends FragmentPagerAdapter {
    public final int COUNT = 4;
    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3", "Tab4"};
    private Context context;

    public BottomFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == Config.HOME){
            return HomePageFragment.newInstance(position + 1);
        }else if(position ==Config.PUSH) {
            return PushPageFragment.newInstance(position + 1);
        } else {
            return FindPageFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}