package cn.mw.growthhacker.view.banner.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutionException;

import cn.mw.growthhacker.R;
import cn.mw.growthhacker.view.banner.holder.CBViewHolderCreator;
import cn.mw.growthhacker.view.banner.holder.Holder;
import cn.mw.growthhacker.view.banner.view.CBLoopViewPager;

/**
 * Created by Sai on 15/7/29.
 */
public class CBPageAdapter<T> extends PagerAdapter {
    protected List<T> mDatas;
    protected List<String> mws;
    protected CBViewHolderCreator holderCreator;
//    private View.OnClickListener onItemClickListener;
    private boolean canLoop = true;
    private CBLoopViewPager viewPager;
    private final int MULTIPLE_COUNT = 300;

    public int toRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount == 0)
            return 0;
        int realPosition = position % realCount;
        return realPosition;
    }

    @Override
    public int getCount() {
        return canLoop ? getRealCount()*MULTIPLE_COUNT : getRealCount();
    }

    public int getRealCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = toRealPosition(position);

        View view = getView(realPosition, null, container);
//        if(onItemClickListener != null) view.setOnClickListener(onItemClickListener);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        int position = viewPager.getCurrentItem();
        if (position == 0) {
            position = viewPager.getFristItem();
        } else if (position == getCount() - 1) {
            position = viewPager.getLastItem();
        }
        try {
            viewPager.setCurrentItem(position, false);
        }catch (IllegalStateException e){}
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }

    public void setViewPager(CBLoopViewPager viewPager) {
        this.viewPager = viewPager;
    }


    public CBPageAdapter(CBViewHolderCreator holderCreator, List<T> datas,List<String>mws) {
        this.holderCreator = holderCreator;
        this.mDatas = datas;
        this.mws = mws;
        if(mDatas != null && !mDatas.isEmpty()){
            if((mws == null)||(mws!=null&&mws.size()!=mDatas.size())){
                throw new RuntimeException("mws size must be equal to mDatas");
            }
        }
    }


    public CBPageAdapter(CBViewHolderCreator holderCreator, List<T> datas) {
        this.holderCreator = holderCreator;
        this.mDatas = datas;
    }

    public View getView(int position, View view, ViewGroup container) {
        Holder holder = null;
        if (view == null) {
            holder = (Holder) holderCreator.createHolder();
            view = holder.createView(container.getContext());
            view.setTag(R.id.cb_item_tag, holder);
        } else {
            holder = (Holder<T>) view.getTag(R.id.cb_item_tag);
        }
        if (mDatas != null && !mDatas.isEmpty()&&mws != null){
            holder.UpdateUI(container.getContext(), position, mDatas.get(position),mws.get(position));
        }else if (mDatas != null && !mDatas.isEmpty()){
            holder.UpdateUI(container.getContext(), position, mDatas.get(position),null);

        }
        return view;
    }

//    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
}
