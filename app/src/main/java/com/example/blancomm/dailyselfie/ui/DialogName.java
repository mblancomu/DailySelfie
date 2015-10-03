package com.example.blancomm.dailyselfie.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.blancomm.dailyselfie.R;

/**
 * Created by manuel on 3/10/15.
 */
public class DialogName extends DialogFragment {

    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }

    private EditText mEditText;
    private ImageButton mOK, mCancel;

    public DialogName() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_name);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.WHITE));
        dialog.setTitle("Insert name of photo");
        dialog.show();

        mEditText = (EditText) dialog.findViewById(R.id.txt_your_name);
        mOK = (ImageButton) dialog.findViewById(R.id.ok);
        mCancel = (ImageButton) dialog.findViewById(R.id.close);

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditNameDialogListener activity = (EditNameDialogListener) getActivity();
                activity.onFinishEditDialog(mEditText.getText().toString());
                dismiss();
            }
        });
        return dialog;
    }
}
