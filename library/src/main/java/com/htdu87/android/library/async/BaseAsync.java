package com.htdu87.android.library.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;

import com.htdu87.android.library.R;
import com.htdu87.android.library.ui.Loading;

import java.lang.ref.WeakReference;

public abstract class BaseAsync<Params,Progress,Result> extends AsyncTask<Params,Progress,Result> {
    protected String msg;
    private WeakReference<Activity> weak;
    private Loading loading;

    public BaseAsync(Activity context) {
        weak=new WeakReference<>(context);
        loading=new Loading(context);
    }

    public BaseAsync(Activity context, String waitingText) {
        this(context);
        loading.setMessage(waitingText);
    }

    @Override
    protected void onPreExecute() {
        loading.show();
    }

    @Override
    protected void onPostExecute(Result result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (weak.get().isDestroyed())return;
        }else {
            if (weak.get().isFinishing())return;
        }
        loading.dismiss();
    }

    @Override
    protected void onCancelled(Result result) {
        loading.dismiss();
    }

    @Override
    protected void onCancelled() {
        loading.dismiss();
    }

    protected Activity getContext(){
        return weak.get();
    }

    protected String statusCode2Msg(int code){
        switch (code){
            case 400:
                return getContext().getString(R.string.htdu87_lib_msg_400);
            case 401:
                return getContext().getString(R.string.htdu87_lib_msg_401);
            case 403:
                return getContext().getString(R.string.htdu87_lib_msg_403);
            case 404:
                return getContext().getString(R.string.htdu87_lib_msg_404);
            case 500:
                return getContext().getString(R.string.htdu87_lib_msg_500);
            case 502:
                return getContext().getString(R.string.htdu87_lib_msg_502);
            default:
                return getContext().getString(R.string.htdu87_lib_msg_ukn);
        }
    }

    public void dismiss(){
        loading.dismiss();
    }
}
