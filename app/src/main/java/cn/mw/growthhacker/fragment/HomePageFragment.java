package cn.mw.growthhacker.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxinsight.TrackAgent;

import cn.mw.growthhacker.adapter.DividerItemDecoration;
import cn.mw.growthhacker.adapter.HomeAdapter;
import cn.mw.growthhacker.R;
import cn.mw.growthhacker.domain.HomeList;

/**
 * Created by aaron on 16/9/12.
 */

public class HomePageFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    RecyclerView recyclerView;
    private String TAG = "HomePageFragment";

    public static HomePageFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            Log.e(TAG, "HomePageFragment visible");
            TrackAgent.currentEvent().onPageStart("首 页");

        } else {
            Log.e(TAG, "HomePageFragment invisible");
            TrackAgent.currentEvent().onPageEnd("首 页");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        initRecyclerView(view);
//        initBanner(view);
        return view;
    }

    private void initRecyclerView(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        HomeAdapter adapter = new HomeAdapter(new HomeList().builder());


        LinearLayoutManager mgr = new LinearLayoutManager(getContext());
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mgr);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

    }


}