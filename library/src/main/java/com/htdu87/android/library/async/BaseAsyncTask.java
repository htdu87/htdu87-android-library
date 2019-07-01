package com.htdu87.android.library.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.htdu87.android.library.R;
import com.htdu87.android.library.ui.Loading;

import java.lang.ref.WeakReference;

public abstract class BaseAsyncTask<Params,Progress,Result> extends AsyncTask<Params,Progress,Result> {
    protected String msg;
    private WeakReference<Activity> weak;
    private Loading loading;

    public BaseAsyncTask(Activity context) {
        weak=new WeakReference<>(context);
        loading=new Loading(context);
    }

    public BaseAsyncTask(Activity context, String waitingText) {
        this(context);
        loading.setMessage(waitingText);
    }

    @Override
    protected void onPreExecute() {
        loading.show();
    }

    @Override
    protected void onPostExecute(Result result) {
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
                return getContext().getString(R.string.msg_400);
            case 401:
                return getContext().getString(R.string.msg_401);
            case 403:
                return getContext().getString(R.string.msg_403);
            case 404:
                return getContext().getString(R.string.msg_404);
            case 500:
                return getContext().getString(R.string.msg_500);
            default:
                return getContext().getString(R.string.msg_ukn);
        }
    }
}
