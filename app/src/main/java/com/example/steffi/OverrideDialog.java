package com.example.steffi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class OverrideDialog extends DialogFragment {

    public interface confirmListener{
        void onConfirm(Boolean confirmed);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        final confirmListener confirmListener = (confirmListener) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.title_override)
                .setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        confirmListener.onConfirm(true);
                    }
                })
                .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        confirmListener.onConfirm(false);
                    }
                });


        // Create the AlertDialog object and return it
        return builder.create();
    }
}
