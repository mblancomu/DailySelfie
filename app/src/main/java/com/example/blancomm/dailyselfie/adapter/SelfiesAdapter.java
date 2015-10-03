package com.example.blancomm.dailyselfie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blancomm.dailyselfie.R;
import com.example.blancomm.dailyselfie.model.SelfieInfo;
import com.example.blancomm.dailyselfie.ui.DetailActivity;
import com.example.blancomm.dailyselfie.utils.ImageHelper;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelfiesAdapter extends RecyclerView.Adapter<SelfiesAdapter.ViewHolder> {

    private Context mContext;
    private List<SelfieInfo> mItems = new ArrayList<SelfieInfo>();
    private String TAG = SelfiesAdapter.class.getSimpleName();

    public SelfiesAdapter(Context context) {
        mContext = context;

        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (storageDir != null) {
            File[] selfieFiles = storageDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String name) {
                    return name.endsWith(".jpg");
                }
            });

            for (File file : selfieFiles) {
                SelfieInfo selfieRecord = new SelfieInfo(file.getAbsolutePath(), file.getName());
                mItems.add(selfieRecord);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_grid_row, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final SelfieInfo item = mItems.get(i);

        ImageHelper.setImageFromFilePath(item.getPath(), viewHolder.mThumbnail);
        viewHolder.mTitle.setText(item.getmName().substring(0,item.getmName().lastIndexOf("_")));
        viewHolder.mDate.setText(item.getmName().substring(item.getmName().lastIndexOf("_")+1));
        viewHolder.mThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, item.getPath());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle, mDate;
        private ImageView mThumbnail;

        ViewHolder(View v) {
            super(v);
            mThumbnail = (ImageView) v.findViewById(R.id.thumbnail_selfie);
            mTitle = (TextView) v.findViewById(R.id.title_card);
            mDate = (TextView) v.findViewById(R.id.date);

        }
    }

    public void updateResults(SelfieInfo item, Context context) {
        this.mContext = context;

        mItems.add(item);
        //Triggers the list update
        notifyDataSetChanged();
    }

}