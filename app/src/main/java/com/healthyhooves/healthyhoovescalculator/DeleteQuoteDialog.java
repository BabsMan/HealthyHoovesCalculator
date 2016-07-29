package com.healthyhooves.healthyhoovescalculator;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.healthyhooves.healthyhoovescalculator.model.QuoteObj;
import com.healthyhooves.healthyhoovescalculator.quotedisplay.QuoteListActivity;
import com.healthyhooves.healthyhoovescalculator.quotedisplay.ShowQuoteActivity;

public class DeleteQuoteDialog {

    private Activity activity;
    private Context context;
    private QuoteObj quote;

    public AlertDialog dialog;

    public DeleteQuoteDialog(final Activity activity,
                             final Context context,
                             final QuoteObj quote) {

        this.activity = activity;
        this.context = context;
        this.quote = quote;

        dialog = buildDeleteConfDialog();
    }

    private AlertDialog buildDeleteConfDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context,
                R.style.HealthyHooves_Dialogs);
        builder.setTitle("Delete this quote?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface,
                                        final int i) {
                        if (activity instanceof QuoteListActivity) {
                            ((QuoteListActivity) activity).deleteQuoteFromDatabase(quote);
                        }
                        else if (activity instanceof ShowQuoteActivity) {

                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface,
                                        final int i) {

                    }
                });

        return builder.create();
    }
}
