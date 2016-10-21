package cn.mw.growthhacker.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.bean.ImageItem;

import java.util.List;

import com.zxinsight.MarketingHelper;
import com.zxinsight.TrackAgent;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.RecyclerViewItemClickListener;
import cn.mw.growthhacker.adapter.DividerItemDecoration;
import cn.mw.growthhacker.R;
import cn.mw.growthhacker.adapter.SelectAdapter;
import cn.mw.growthhacker.domain.PushGridData;
import cn.mw.growthhacker.view.tab.Utils;
import pub.devrel.easypermissions.AfterPermissionGranted;

/**
 * Created by aaron on 16/9/12.
 */

public class PushPageFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    RecyclerView recyclerView;
    private final int REQ_IMAGE = 1433;
    private static final int RC_CAMERA_PERM = 123;
    private boolean isShowCamera = true;
    public static String ADD = "add";
    private String PIC = "pic";
    private SelectAdapter adapter;
    private String TAG = "PushPageFragment";

    public static PushPageFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        PushPageFragment fragment = new PushPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            Log.e(TAG, "PushPageFragment visible");
            TrackAgent.currentEvent().onPageStart("发布 页");

        } else {
            Log.e(TAG, "PushPageFragment invisible");
            TrackAgent.currentEvent().onPageEnd("发布 页");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_push_page, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

         adapter = new SelectAdapter(new PushGridData().builder());

        LinearLayoutManager mgr = new GridLayoutManager(getContext(), 3);
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mgr);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getContext(), new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e(TAG,"position:"+position +",view:"+view+",viewTag:"+view.getTag());
                if(ADD.equals(view.getTag())){
                    if(MarketingHelper.currentMarketing(view.getContext()).isActive(Config.MW_PHOTO)){
//                        MarketingHelper.currentMarketing(view.getContext()).clickWithAll(view.getContext(),Config.MW_PHOTO);
                        MarketingHelper.currentMarketing(view.getContext()).clickWithAll(view.getContext(), Config.MW_PHOTO, Config.CameraCallBack,null,null,null);
                    } else {
                        storageTask(view.getContext(),position);
                    }
                } else {
                    adapter.remove(position);
                }
            }
        }));
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @AfterPermissionGranted(RC_CAMERA_PERM)
    private void storageTask(Context context, final int position) {
        if (Utils.selfPermissionGranted(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Have permission, do the thing!
            AndroidImagePicker.getInstance().pickSingle(context, isShowCamera, new AndroidImagePicker.OnImagePickCompleteListener() {
                @Override
                public void onImagePickComplete(List<ImageItem> items) {
                    if(items != null && items.size() > 0){
                        Log.i("SelectAdapter","image = "+items.get(0).path);
                        adapter.add(items.get(0).path,position);
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQ_IMAGE) {
//                ivCrop.setVisibility(View.GON E);

                List<ImageItem> imageList = AndroidImagePicker.getInstance().getSelectedImages();
                Log.e("PushPage", "image = " + imageList);
//                mAdapter.clear();
//                mAdapter.addAll(imageList);
            }/*else if(requestCode == REQ_IMAGE_CROP){
                Bitmap bmp = (Bitmap)data.getExtras().get("bitmap");
                Log.i(TAG,"-----"+bmp.getRowBytes());
            }*/
        }
    }


}