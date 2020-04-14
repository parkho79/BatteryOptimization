package com.parkho.sample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class PhBatteryDialog extends DialogFragment
{
    private static final String ARGS = "dialog_arg";

    public static PhBatteryDialog newInstance(int a_viewId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS, a_viewId);

        PhBatteryDialog dialog = new PhBatteryDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle a_savedInstanceState) {
        OnClickListener clickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle bundle = getArguments();
                if (bundle == null) {
                    return;
                }

                final Intent intent;
                if (bundle.getInt(ARGS) == R.id.btn_request_self) {
                    intent = getSelfIntent();
                } else {
                    intent = getPermissionIntent();
                }
                startActivity(intent);
            }
        };

        String message = "정상적인 앱 사용을 위해 해당 어플을 \"배터리 사용량 최적화\" 목록에서 \"제외\"해야 합니다.";
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton("네", clickListener);
        return builder.create();
    }

    private Intent getPermissionIntent() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
        return intent;
    }

    private Intent getSelfIntent() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        intent.setData(Uri.parse("package:" + getContext().getPackageName()));
        return intent;
    }
}