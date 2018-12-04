package com.example.hemaladani.photogallery;

import android.net.Uri;

/**
 * Created by hemaladani on 5/2/17.
 */

public class GalleryItem {

    private String mCaption;
    private String mId;
    private String mUrl;
    private String mOwner;

    public String toString(){
        return mCaption;
    }

    public String getmCaption() {
        return mCaption;
    }
    public String getmOwner(){
        return mOwner;
    }
    public void setmOwner(String owner){
        mOwner=owner;

    }
    public Uri getPhotographUri(){

        return Uri.parse("https://www.flickr.com/photos/").buildUpon().appendPath(mOwner).appendPath(mId).build();
    }

    public void setmCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
