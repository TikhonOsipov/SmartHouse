package com.tixon.smarthouse.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.tixon.smarthouse.R;

/**
 * Created by tikhon.osipov on 28.04.2016
 */
public abstract class AlertDialogEnableManual extends AlertDialog {
    AlertDialog.Builder builder;
    private Context context;

    protected AlertDialogEnableManual(Context context) {
        super(context);
        this.context = context;
        builder = new Builder(context);
    }

    public void show() {
        builder.setMessage(context.getString(R.string.enable_manual_message));
        builder.setTitle(context.getString(R.string.enable_manual_title));
        builder.setPositiveButton(context.getString(R.string.enable_manual_yes), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                positiveAction();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(context.getString(R.string.enable_manual_no), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                negativeAction();
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    public abstract void positiveAction();
    public abstract void negativeAction();
}
