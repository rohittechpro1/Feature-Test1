package com.example.hemaladani.photogallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Target;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by hemaladani on 5/4/17.
 */

public class ThumbnailDownloader<T> extends HandlerThread {

    private static final int MESSAGE_DOWNLOAD=0;

    public static final String TAG="ThumbnailDownloader";
    private boolean mHasQuit=false;
    private Handler mRequestHandler;
    private Handler mResponseHandler;
    private ThumbnailDownloadListener<T> mThumnailDownloadListener;
    private ConcurrentMap<T,String> mRequestMap=new ConcurrentHashMap<>();

    public interface ThumbnailDownloadListener<T>{
        void onThumbnailDownloaded(T target,Bitmap thumnai);
    }
    public void setThumbnailDownloadListener(ThumbnailDownloadListener<T> listener){
        mThumnailDownloadListener=listener;
    }

    public ThumbnailDownloader(Handler responseHandler) {
        super(TAG);
        mResponseHandler=responseHandler;
    }

    @Override
    public boolean quit() {
        mHasQuit=true;
        return super.quit();
    }

    public void queThumbnail(T target,String url){
        Log.i(TAG,"Got url:"+url);

        if(url==null){
            mRequestMap.remove(target);

        }else{
            mRequestMap.put(target,url);
            mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD,target).sendToTarget();
        }
    }

    public void clearQueue(){

        mRequestHandler.removeMessages(MESSAGE_DOWNLOAD);
        mRequestMap.clear();
    }

    @Override
    protected void onLooperPrepared() {
        mRequestHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==MESSAGE_DOWNLOAD){
                    T target=(T)msg.obj;
                    Log.i(TAG,"Got a request from url:"+mRequestMap.get(target));
                    handleRequest(target);
                }
            }
        };

    }

    private void handleRequest(final T target){
        try{
            final String url=mRequestMap.get(target);
            if(url==null){return;}
            byte[]bitmapBytes=new FlickrFetchr().getUrlBytes(url);
            final Bitmap bitmap= BitmapFactory.decodeByteArray(bitmapBytes,0,bitmapBytes.length);
            Log.i(TAG,"Bitmap created");
            mResponseHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(mRequestMap.get(target)!=url||mHasQuit){
                        return;
                    }
                    mRequestMap.remove(target);
                    mThumnailDownloadListener.onThumbnailDownloaded(target,bitmap);
                }
            });


        }
        catch (IOException ioe){
            Log.e(TAG,"Error Downloading",ioe);
        }



    }


}
