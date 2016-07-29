package com.healthyhooves.healthyhoovescalculator.calculator;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.healthyhooves.healthyhoovescalculator.R;
import com.healthyhooves.healthyhoovescalculator.mainmenu.MainActivity;
import com.healthyhooves.healthyhoovescalculator.model.QuoteObj;
import com.healthyhooves.healthyhoovescalculator.quotedisplay.QuoteListActivity;

/**
 * Created by Graeme on 07/07/2016.
 */
public class CalculatorDialog {

    private Activity activity;
    private Context context;

    public AlertDialog calcDialog;
    public Button posButton;

    private EditText enterNumCows;
    private EditText enterNumBaths;
    private EditText enterBathCap;

    public CalculatorDialog(Activity activity, Context context) {

        this.activity = activity;
        this.context = context;
        calcDialog = buildCalculatorDialog();
    }

    private AlertDialog buildCalculatorDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context,
                R.style.HealthyHooves_Dialogs);
        AlertDialog dialog;

        View v = activity.getLayoutInflater().inflate(R.layout.calculator_view, null);
        enterNumCows = (EditText) v.findViewById(R.id.edt_numCows);
        enterNumBaths = (EditText) v.findViewById(R.id.edt_numBaths);
        enterBathCap = (EditText) v.findViewById(R.id.edt_bathCap);

        builder.setTitle("Get a Quote")
                .setView(v)
                .setPositiveButton(R.string.str_btn_calculate, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        dialog = builder.create();

        return dialog;
    }

    public void setOnClickListener() {
        posButton = calcDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        posButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditTextEmpty(enterNumCows)) {
                    enterNumCows.setError("Oy, you're a Donkey!");
                }
                if (isEditTextEmpty(enterBathCap)) {
                    enterBathCap.setError("Oy, you're a Donkey!");
                }
                if (isEditTextEmpty(enterNumBaths)) {
                    enterNumBaths.setError("Oy, you're a Donkey!");
                }

                if (!isEditTextEmpty(enterNumCows)&&
                        !isEditTextEmpty(enterBathCap)&&
                        !isEditTextEmpty(enterNumBaths)) {
                    performCalculation();
                    calcDialog.dismiss();
                }
            }
        });
    }

    private void performCalculation() {
        HHCalculator calculator = new HHCalculator(context);
        int numCows = Integer.parseInt(enterNumCows.getText().toString());
        int numBaths = Integer.parseInt(enterNumBaths.getText().toString());
        int bathCap = Integer.parseInt(enterBathCap.getText().toString());
        QuoteObj newQuote = calculator.getQuote(numCows, numBaths, bathCap);

        if (activity instanceof MainActivity) {
            ((MainActivity) activity).goToShowQuoteActivity(newQuote);
        }
        else if (activity instanceof QuoteListActivity) {
            ((QuoteListActivity) activity).goToShowQuoteActivity(newQuote);
        }
    }

    private boolean isEditTextEmpty(EditText editText) {
        String input = editText.getText().toString();
        return input.matches("");
    }
}
