package com.soho.evgeny.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by evgeny on 18.08.16.
 */
public class MyObject implements Parcelable {
    final static String LOG_TAG = "MyLog";
    public String s;
    public int i;

    public MyObject(String _s, int _i){
        Log.d(LOG_TAG, "MyObject(String _s, int _i)");
        s = _s;
        i= _i;
    }

    private MyObject(Parcel p){
        Log.d(LOG_TAG, "MyObject(Parcel parcel)");
        s = p.readString();
        i = p.readInt();
    }
    public int describeContents(){
        return 0;
    }



    public static final Parcelable.Creator<MyObject> CREATOR = new Parcelable.Creator<MyObject>() {
        @Override
        public MyObject createFromParcel(Parcel in) {
            Log.d(LOG_TAG,"Create from Parcel");

            return new MyObject(in);
        }

        @Override
        public MyObject[] newArray(int size) {
            return new MyObject[size];
        }
    };



    @Override
    public void writeToParcel(Parcel parcel, int flag) {
        Log.d(LOG_TAG,"Write To Parcel");
        parcel.writeString(s);
        parcel.writeInt(i);
    }
}
