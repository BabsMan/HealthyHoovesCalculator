package com.healthyhooves.healthyhoovescalculator.calculator;

import android.content.Context;
import android.widget.Toast;

import com.healthyhooves.healthyhoovescalculator.databaseutils.QuotesDataSource;
import com.healthyhooves.healthyhoovescalculator.model.QuoteObj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Houses the mathematics to be carried out by the application.
 */
public class HHCalculator {

    private Context context;

    private float numLtrs;
    private float bathsBeforeChange;
    private float ltrsHH;
    private float kgCuSO4;

    public HHCalculator(final Context context) {
        this.context = context;
    }

    private void performCalculations(final int numCows, final int numBaths, final int bathCap) {

        numLtrs = calculateNumLitres(numCows, numBaths);
        bathsBeforeChange = calculateBathsBeforeChange(bathCap);
        ltrsHH = calculateLitresHH(bathCap);
        kgCuSO4 = calculateKGCuSO4(bathCap);
    }

    private float calculateNumLitres(final int numCows, final int numBaths){

        float calc0 = (float)numCows/200;
        float calc1 = calc0*450;
        float calc2 = calc0*4;
        float calc3 = (calc1/numCows);
        float calc4 = (42/calc3);

        return (calc4*numBaths)*calc2;
    }
    private float calculateBathsBeforeChange(final int bathCap) {

        final float calc1 = (float) bathCap/200;
        return (calc1*500)/50;
    }
    private float calculateLitresHH(final int bathCap) {

        return (float)(bathCap*0.02);
    }
    private float calculateKGCuSO4(final int bathCap) {

        return (float)(bathCap*0.02);
    }

    public QuoteObj getQuote(final int numCows, final int numBaths, final int bathCap) {

        performCalculations(numCows, numBaths, bathCap);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d");
        Date now = new Date();
        String strDate = sdf.format(now);
        Toast.makeText(context, strDate, Toast.LENGTH_SHORT).show();

        QuotesDataSource dataSource = new QuotesDataSource(context);
        dataSource.open();

        QuoteObj newQuote = dataSource.createQuote(strDate, numCows, bathCap, numBaths,
                numLtrs, bathsBeforeChange, ltrsHH, kgCuSO4);

        dataSource.close();

        return newQuote;
    }
}
