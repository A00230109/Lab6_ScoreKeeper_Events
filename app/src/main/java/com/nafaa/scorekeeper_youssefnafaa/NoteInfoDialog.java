package com.nafaa.scorekeeper_youssefnafaa;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class NoteInfoDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Developer Information\n\n")

                .setMessage("Name: Youssef Nafaa\n"+
                        "College: Cambrian\n"+
                        "City: Sudbury\n"+
                        "Professor: David Pellegrini\n"+
                        "Course: Mobile Application Development\n\n\n" +
                        "Android App: Score Keeper\n\n"+
                        "Version: 3.2\n"+
                        "Language: Java\n\n"+
                        "Class: JAV-1001 - 91253\nApp Development for Android - 202009-001")

                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setIcon(android.R.drawable.ic_dialog_info);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b7cced")));
        return dialog;
    }
}
