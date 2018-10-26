package com.example.maruf.e_pharma.ContactNumber;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shanto on 9/21/17.
 */

public class LogToast {

    public static void T(Context context,String info){
        Toast.makeText(context,info,Toast.LENGTH_LONG).show();
    }

    public static void L(String Tag,String info){
        Log.d(Tag,info);
    }
}
