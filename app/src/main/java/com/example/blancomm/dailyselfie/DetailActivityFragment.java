package com.example.blancomm.dailyselfie;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private String mPath;
    private ImageView mImage;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        mPath = getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT);
        mImage = (ImageView)rootView.findViewById(R.id.imageView);

        ImageHelper.setImageFromFilePath(mPath, mImage, mImage.getWidth(), mImage.getHeight());

        return rootView;
    }
}
