package com.healthyhooves.healthyhoovescalculator.mainmenu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.healthyhooves.healthyhoovescalculator.R;
import com.healthyhooves.healthyhoovescalculator.calculator.CalculatorDialog;

/**
 * Created by Graeme Grier on 12/10/15.
 *
 * <p>
 * {@link Fragment} containing the main functionality of the application.
 * From here users can navigate to store or launch the Calculator to get a "quote".  Calculator is
 * ran via an instance of {@link CalculatorDialog}.
 * </p>
 *
 */
public class MainFragment extends Fragment {

    Button gotoCalculator;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_main, container, false);
        initUI(v);
        return v;
    }

    private void initUI(final View v) {

        /*Button */gotoCalculator = (Button) v.findViewById(R.id.btn_calculator);
        gotoCalculator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalculatorDialog();
            }
        });

        Button gotoStore = (Button) v.findViewById(R.id.btn_store);
        gotoStore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToStore();
            }
        });

        Button goToQuotes = (Button) v.findViewById(R.id.btn_quote_lists);
        goToQuotes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuotesList();
            }
        });
    }

    /**
     * Launches an instance of {@link CalculatorDialog}.  The {@link CalculatorDialog} contains
     * all the elements to run the quote calculation.
     */
    private void displayCalculatorDialog() {

        CalculatorDialog calculator = new CalculatorDialog(getActivity(), getContext());
        calculator.calcDialog.show();
    }

    private void navigateToStore() {

        ((MainActivity)getActivity()).goToStoreActivity();
    }

    private void openQuotesList() {
        ((MainActivity)getActivity()).goToShowQuoteListActivity();
    }
}
