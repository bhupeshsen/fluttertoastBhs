package com.example.tt;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Bhupesh Sen on 31/07/20 at 2:16 PM.
 * bhupeshsen11@gmail.com
 * 7974430255
 */
public class ToastBhs {

    private Context context;
    private Activity activity;

    ToastBhs(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    void showToast(String msg){
        Toast.makeText(activity,msg,Toast.LENGTH_LONG).show();
    }

    void setActivity(Activity activity) {
        this.activity = activity;
    }

}
