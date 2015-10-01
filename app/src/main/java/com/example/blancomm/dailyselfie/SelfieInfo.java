package com.example.blancomm.dailyselfie;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BlancoMM on 01-Oct-15.
 */
public class SelfieInfo {

    private String mPath;
    private String mName;
    private Date mDate;
    private boolean mSelected;

    public SelfieInfo(String path, String name, Date date) {
        mPath = path;
        mName = name;
        mDate = date;
        mSelected = false;
    }

    public String getPath() {
        return mPath;
    }

    public String getDisplayName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = format.parse(mName, new ParsePosition(0));
        return new SimpleDateFormat("dd MMM, yyyy HH:mm:ss").format(date);
    }

    public boolean getSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    @Override
    public String toString() {
        return mName;
    }
}
