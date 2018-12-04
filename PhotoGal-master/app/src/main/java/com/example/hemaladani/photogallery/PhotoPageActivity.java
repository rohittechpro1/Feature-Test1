package com.example.hemaladani.photogallery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by hemaladani on 6/15/17.
 */

public class PhotoPageActivity extends SingleFragmentActivity {

// this is vZW UI changes.
    
    public static Intent newIntent(Context context, Uri photoGraphUri){
        Intent i=new Intent(context,PhotoPageActivity.class);
        i.setData(photoGraphUri);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return PhotoPageFragment.newInstance(getIntent().getData());
    }
}
