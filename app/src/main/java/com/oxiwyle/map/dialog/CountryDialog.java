package com.oxiwyle.map.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.oxiwyle.map.R;

/**
 * Класс диалога для отображения информации о стране.
 */
public class CountryDialog extends DialogFragment {
    /** Название страны. */
    private String name;

    /**
     * Конструктор класса.
     * @param name название страны
     */
    public CountryDialog(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(name)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
