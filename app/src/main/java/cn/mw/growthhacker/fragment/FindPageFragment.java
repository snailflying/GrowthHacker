package cn.mw.growthhacker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.zxinsight.TrackAgent;
import cn.mw.growthhacker.RecyclerViewItemClickListener;
import cn.mw.growthhacker.activity.NewsDetailActivity;
import cn.mw.growthhacker.activity.O2ODetailActivity;
import cn.mw.growthhacker.activity.ShopDetailActivity;
import cn.mw.growthhacker.activity.TourDetailActivity;
import cn.mw.growthhacker.activity.VideoDetailActivity;
import cn.mw.growthhacker.adapter.DividerItemDecoration;
import cn.mw.growthhacker.adapter.FindAdapter;
import cn.mw.growthhacker.R;
import cn.mw.growthhacker.domain.FindListData;

/**
 * Created by aaron on 16/9/12.
 */

public class FindPageFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    RecyclerView recyclerView;

    private List<String> networkImages;
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };
    private String TAG = "FindPageFragment";

    public static FindPageFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        FindPageFragment fragment = new FindPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            Log.e(TAG, "FindPageFragment visible");
            TrackAgent.currentEvent().onPageStart("首 页");

        } else {
            Log.e(TAG, "FindPageFragment invisible");
            TrackAgent.currentEvent().onPageEnd("首 页");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_page, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        FindAdapter adapter = new FindAdapter(new FindListData().builder());


        LinearLayoutManager mgr = new LinearLayoutManager(getContext());
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mgr);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getContext(), new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    default:
                    case 0:
                        startActivity(new Intent(view.getContext(), TourDetailActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(view.getContext(), ShopDetailActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(view.getContext(), O2ODetailActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(view.getContext(), VideoDetailActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(view.getContext(), NewsDetailActivity.class));
                        break;
                }
            }
        }));
    }


}