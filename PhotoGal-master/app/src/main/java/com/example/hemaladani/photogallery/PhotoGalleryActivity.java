package com.example.hemaladani.photogallery;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class PhotoGalleryActivity extends SingleFragmentActivity {
// this is a test VCS

    // this is second example

    //this is third test file. TMO UI is complete.
    public static Intent newIntent(Context context){
        return new Intent(context,PhotoGalleryActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }

}
