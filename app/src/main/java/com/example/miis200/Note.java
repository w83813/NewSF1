package com.example.miis200;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;


public class Note implements Parcelable {

    private Bitmap Image;
    private String Imagepath;

    public Note(Bitmap mImage, String mImagepath) {
        this.Image = mImage;
        this.Imagepath = mImagepath;
    }



    protected Note(Parcel in) {
        Imagepath = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
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
        return "Note{" +
                "title='" + Imagepath + '\'' +
                '}';
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
