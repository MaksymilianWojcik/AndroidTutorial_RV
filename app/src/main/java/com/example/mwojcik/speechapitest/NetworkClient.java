package com.example.mwojcik.speechapitest;

import android.content.Context;

public class NetworkClient {

    private static NetworkClient networkClientInstance;
    private static Context mContext;
    //private RequestQueue mRequestQueue;

    private NetworkClient (Context context){
        mContext = context;
        //mRequestQueue = getRequestQueue();
    }

    public static synchronized NetworkClient getNetworkClientInstance(Context context){
        if (networkClientInstance == null){
            networkClientInstance = new NetworkClient(context);
        }
        return networkClientInstance;
    }


//    public RequestQueue getRequestQueue(){
//        if (mRequestQueue == null){
//            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
//        }
//        return mRequestQueue;
//    }

//    public <T> void addToRequestQueue(Request<T> request){
//        getRequestQueue().add(request);
//    }

}
