package cn.mw.growthhacker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxinsight.MWImageView;
import com.zxinsight.MarketingHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.view.NetworkImageHolderView;
import cn.mw.growthhacker.R;
import cn.mw.growthhacker.view.banner.ConvenientBanner;
import cn.mw.growthhacker.view.banner.holder.CBViewHolderCreator;
import cn.mw.growthhacker.domain.HomeList;
import cn.mw.growthhacker.view.banner.listener.OnItemClickListener;

/**
 * @author aaron
 * @date 15/11/25
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    int TYPE_DEFAULT = 0;
    int TYPE0 = 1;
    int TYPE_COUNT = TYPE0 + 1;
    MarketingHelper marketingHelper;


    protected HomeList home = null;

    public HomeAdapter(HomeList items) {
        home = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        marketingHelper = MarketingHelper.currentMarketing(parent.getContext());
        if (viewType == TYPE0) {
            View view = inflater.inflate(R.layout.cell_home_list_top_banner, parent, false);
            return new TopViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_home_list, parent, false);
            return new ViewHolder(view);
        }
    }


    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return position == 0 ? TYPE0 : TYPE_DEFAULT;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final HomeList.ListContent item = home.list.get(position);

        int type = getItemViewType(position);

        if (type == TYPE0) {
            TopViewHolder viewHolder = (TopViewHolder) holder;
            initBanner(viewHolder.convenientBanner);
        } else {
            if (item != null) {
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.title.setText(item.title);
                viewHolder.desc.setText(item.desc);
                if (marketingHelper.isActive(item.mwKey)) {
                    viewHolder.title.setText(marketingHelper.getTitle(item.mwKey));
                    viewHolder.desc.setText(marketingHelper.getDescription(item.mwKey));
                    viewHolder.listBg.bindEvent(viewHolder.listBg.getContext(), item.mwKey);
                    viewHolder.listBg.getRootView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            marketingHelper.click(v.getContext(), item.mwKey);
                        }
                    });
                }

            }
        }


    }


    @Override
    public int getItemCount() {
        return home.list.size();
    }


    public class TopViewHolder extends RecyclerView.ViewHolder {

        ConvenientBanner convenientBanner;

        public TopViewHolder(View view) {
            super(view);
            convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MWImageView listBg;
        TextView title;
        TextView desc;

        public ViewHolder(View view) {
            super(view);
            listBg = (MWImageView) view.findViewById(R.id.detail_icon);
            title = (TextView) view.findViewById(R.id.title);
            desc = (TextView) view.findViewById(R.id.content);
        }
    }


    private void initBanner(ConvenientBanner convenientBanner) {
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        List<String> networkImages = Arrays.asList(home.bannerImages);
        List<String> mws = new ArrayList<>();
        mws.add(Config.MW_1);
        mws.add(Config.MW_2);
        mws.add(Config.MW_3);
        mws.add(Config.MW_4);

        convenientBanner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, networkImages, mws)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        convenientBanner.setManualPageable(false);//设置不能手动影响
    }

}
