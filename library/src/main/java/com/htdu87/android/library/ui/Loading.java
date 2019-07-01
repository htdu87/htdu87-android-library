package com.htdu87.android.library.ui;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.htdu87.android.library.R;


public class Loading extends Dialog {
    private TextView lblMsg;

    public Loading(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading);
        setCancelable(false);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        lblMsg=findViewById(R.id.lbl_msg);
    }

    public Loading(@NonNull Context context, String msg){
        this(context);
        lblMsg.setText(msg);
    }

    public void setMessage(String msg){
        lblMsg.setText(msg);
    }

    @Override
    public void show() {
        super.show();
        if (TextUtils.isEmpty(lblMsg.getText()))
            lblMsg.setVisibility(View.GONE);
        else
            lblMsg.setVisibility(View.VISIBLE);
    }
}
