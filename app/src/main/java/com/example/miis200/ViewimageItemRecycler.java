package com.example.miis200;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


public class ViewimageItemRecycler implements Parcelable {

    private Bitmap Image;
    private String Imagepath;

    public ViewimageItemRecycler(Bitmap mImage, String mImagepath) {
        this.Image = mImage;
        this.Imagepath = mImagepath;
    }



    protected ViewimageItemRecycler(Parcel in) {
        Imagepath = in.readString();
    }

    public static final Creator<ViewimageItemRecycler> CREATOR = new Creator<ViewimageItemRecycler>() {
        @Override
        public ViewimageItemRecycler createFromParcel(Parcel in) {
            return new ViewimageItemRecycler(in);
        }

        @Override
        public ViewimageItemRecycler[] newArray(int size) {
            return new ViewimageItemRecycler[size];
        }
    };

    public String getImagepath() {
        return Imagepath;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap mImage) {
        this.Image = mImage;
    }


    @Override
    public String toString() {
        return Imagepath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Imagepath);
    }
}
