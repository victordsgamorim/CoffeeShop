package com.dorset.coffeeshop.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.dorset.coffeeshop.R;

public class NewsletterFragment extends AppCompatDialogFragment{

    private EditText clientName;
    private EditText clientEmail;
    private NewsletterDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_newsletter, null);

        builder.setView(view)
                .setTitle("Register Newsletter")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = clientName.getText().toString();
                        String email = clientEmail.getText().toString();
                        listener.sendMessage(username, email);
                    }
                });

        clientName = view.findViewById(R.id.edit_username);
        clientEmail = view.findViewById(R.id.edit_password);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (NewsletterDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement NewsletterDialogListener");
        }
    }


    public interface NewsletterDialogListener {
        void sendMessage(String name, String email);
    }
}
