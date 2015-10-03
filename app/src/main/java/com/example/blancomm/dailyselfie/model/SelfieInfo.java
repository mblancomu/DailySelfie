package com.example.blancomm.dailyselfie.model;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BlancoMM on 01-Oct-15.
 */
public class SelfieInfo {

    private String mPath;
    private String mName;
    private boolean mSelected;

    public SelfieInfo(String path, String name) {
        mPath = path;
        mName = name;
        mSelected = false;
    }

    public String getPath() {
        return mPath;
    }

    public boolean getSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    public String getmName() {
        return mName;
    }
}
