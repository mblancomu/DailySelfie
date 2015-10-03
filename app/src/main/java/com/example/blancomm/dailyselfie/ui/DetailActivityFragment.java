package com.example.blancomm.dailyselfie.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.blancomm.dailyselfie.utils.ImageHelper;
import com.example.blancomm.dailyselfie.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private String mPath;
    private ImageView mImage;
    private String TAG = DetailActivityFragment.class.getSimpleName();

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        mPath = getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT);

        mImage = (ImageView)rootView.findViewById(R.id.imageView);

        Log.e(TAG, "El path es: " + mPath);

        ImageHelper.setImageFromFilePath(mPath, mImage);

        return rootView;
    }
}
