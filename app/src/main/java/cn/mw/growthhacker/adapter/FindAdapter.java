package cn.mw.growthhacker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zxinsight.MWImageView;

import cn.mw.growthhacker.R;
import cn.mw.growthhacker.domain.FindListData;

/**
 * @author aaron
 * @date 15/11/25
 */
public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder> {


    int TYPE_DEFAULT = 0;
    int TYPE0 = 1;
    int TYPE_COUNT = TYPE0 + 1;

    protected FindListData mList = null;

    public FindAdapter(FindListData items) {
        mList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_find_list, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final FindListData.ListContent item = mList.list.get(position);

        if (item != null) {
//            holder.convenientBanner.setImageResource(R.drawable.add);
            ImageLoader.getInstance().displayImage(mList.list.get(position).imgUrl,holder.listBg);
            holder.listBg.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                        Intent intent = new Intent(v.getContext(), WebViewActivity.class);
//                        intent.putExtra(WebViewActivity.WEB_URL, item.url);
//                        holder.convenientBanner.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        MWImageView listBg;

        public ViewHolder(View view) {
            super(view);
            listBg = (MWImageView) view.findViewById(R.id.id_news_list_img);
        }
    }

}
