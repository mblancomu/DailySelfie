package com.example.blancomm.dailyselfie.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blancomm.dailyselfie.R;
import com.example.blancomm.dailyselfie.model.SelfieInfo;
import com.example.blancomm.dailyselfie.adapter.SelfiesAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private SelfiesAdapter mAdapter;
    private String TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mAdapter = new SelfiesAdapter(getActivity());
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void updateSelfiesList(SelfieInfo item){

        mAdapter.updateResults(item, getActivity());
    }
}
