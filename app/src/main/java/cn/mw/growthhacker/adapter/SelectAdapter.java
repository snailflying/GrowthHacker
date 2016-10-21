package cn.mw.growthhacker.adapter;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.bean.ImageItem;

import java.util.List;

import com.zxinsight.MWImageView;
import com.zxinsight.MarketingHelper;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.R;
import cn.mw.growthhacker.domain.PushGridData;
import cn.mw.growthhacker.fragment.PushPageFragment;
import cn.mw.growthhacker.view.tab.Utils;
import pub.devrel.easypermissions.AfterPermissionGranted;

/**
 * @author aaron
 * @date 15/11/25
 */
public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {

    int TYPE_DEFAULT = 0;
    int TYPE0 = 1;
    int TYPE_COUNT = TYPE0 + 1;

    private Context mContext;

    private static final int RC_CAMERA_PERM = 123;

    protected List<PushGridData.ListContent> mList = null;
    private boolean isShowCamera = true;
    private String TAG = "SelectAdapter";

    public SelectAdapter(PushGridData items) {
        mList = items.list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_select, parent, false);

        mContext = view.getContext();
        return new ViewHolder(view);
    }

    public void remove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void add(String path, int position) {
        PushGridData.ListContent grid = new PushGridData.ListContent();
        grid.imgPath = path;
        mList.add(position, grid);
        notifyItemInserted(position);
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return position == getItemCount() - 1 ? TYPE0 : TYPE_DEFAULT;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int type = getItemViewType(position);
        Log.e(TAG, "position = " + position + ",type = " + type);
        final PushGridData.ListContent item = mList.get(position);

        if (type == TYPE0) {
//            if(MarketingHelper.currentMarketing(mContext).isActive(Config.MW_PHOTO)){
//                holder.listBg.bindEvent(mContext,Config.MW_PHOTO);
//            } else {
//                holder.listBg.setImageResource(item.imgRes);
//            }
            holder.listBg.setImageResource(item.imgRes);

            holder.listBg.setTag(PushPageFragment.ADD);

        } else {
            if (item != null) {
                if (!TextUtils.isEmpty(item.imgPath)) {
                    Bitmap bmp = BitmapFactory.decodeFile(item.imgPath);
                    holder.listBg.setImageBitmap(bmp);
                } else {
                    holder.listBg.setImageResource(item.imgRes);
                }

            }
        }


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @AfterPermissionGranted(RC_CAMERA_PERM)
    private void storageTask(Context context) {
        if (Utils.selfPermissionGranted(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Have permission, do the thing!
            AndroidImagePicker.getInstance().pickSingle(context, isShowCamera, new AndroidImagePicker.OnImagePickCompleteListener() {
                @Override
                public void onImagePickComplete(List<ImageItem> items) {
                    if (items != null && items.size() > 0) {
                        Log.i("SelectAdapter", "image = " + items.get(0).path);
//                                mAdapter.clear();
//                                mAdapter.addAll(items);


                    }
                }
            });
        } else {
            // Request one permission
//            EasyPermissions.requestPermissions(this, context.getString(R.string.rationale_sms),
//                    RC_CAMERA_PERM, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }


    @Override
    public int getItemCount() {
        int a = mList.size();
        Log.e(TAG, "getItemCount = " + a);
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        MWImageView listBg;

        public ViewHolder(View view) {
            super(view);
            listBg = (MWImageView) view.findViewById(R.id.id_news_list_img);
        }
    }

}
