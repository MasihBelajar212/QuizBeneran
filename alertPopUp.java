package com.example.quizbeneran;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;

public class alertPopUp extends AppCompatDialogFragment {
    private static String alertType;

    public alertPopUp(String alertType2) {
        alertType = alertType2;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder termAlert = new AlertDialog.Builder(getActivity());
        if (alertType.equals("Term of Service")) {
            termAlert.setTitle("Alert").setMessage("Please Read The Term of Service!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            return termAlert.create();
        } else if (!alertType.equals("Password not match")) {
            return null;
        } else {
            termAlert.setTitle("Alert").setMessage("Password does not match!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            return termAlert.create();
        }
    }
}
