package com.example.foodplanner_app.models;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.foodplanner_app.R;

public class Utilities {
    static ProgressDialog progressDoalog;
    public static void showLoading(Context context){
        progressDoalog =new ProgressDialog(context);
        progressDoalog.setMessage("loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.progress_indicator));
        progressDoalog.setIndeterminate(true);
        progressDoalog.setCancelable(false);
        progressDoalog.show();
    }

}
