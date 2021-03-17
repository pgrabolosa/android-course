package com.exemple.simpledemo;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DemoDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // pour plus de détails : https://developer.android.com/guide/topics/ui/dialogs

        return new AlertDialog.Builder(requireContext())
                .setMessage("Exemple de dialogue")
                .setPositiveButton(getString(android.R.string.ok), (dialog, which) -> {} )
                .create();
    }
}