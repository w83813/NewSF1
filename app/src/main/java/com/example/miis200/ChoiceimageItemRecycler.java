package com.example.miis200;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ChoiceimageItemRecycler implements Serializable {

    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private String memo;

    private String imagepath;

    private Bitmap eyeimage;

    public String getMemo() {
        return memo;
    }

    public String getImagepath() {
        return imagepath;
    }

    public Bitmap getEyeimage() {
        return eyeimage;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public void setEyeimage(Bitmap meyeimage) {
        this.eyeimage = meyeimage;
    }
}
