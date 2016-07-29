package com.healthyhooves.healthyhoovescalculator.quotedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.healthyhooves.healthyhoovescalculator.R;
import com.healthyhooves.healthyhoovescalculator.model.QuoteObj;
import com.healthyhooves.healthyhoovescalculator.storepage.StorePageActivity;
import com.healthyhooves.healthyhoovescalculator.storepage.StorePageFragment;

public class ShowQuoteActivity extends AppCompatActivity {

    public static final String EXTRA_QUOTE = "quote";
    private QuoteObj display;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quotes);

        display = getIntent().getExtras().getParcelable(EXTRA_QUOTE);

        initUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_show_quote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_open_store_page) {

            startStorePageActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUI() {

        String title = getString(R.string.str_output_quote_for) + " " + display.getDate();
        this.setTitle(title);

        TextView txtNumCows = (TextView) findViewById(R.id.output_num_cow);
        String numCowsOutput = "Number of Cows: " + Integer.toString(display.getNumCows());
        txtNumCows.setText(numCowsOutput);
        TextView txtBathCap = (TextView) findViewById(R.id.output_bath_cap);
        String bathCapOutput = "Bath Capacity (L): " + Integer.toString(display.getBathCap());
        txtBathCap.setText(bathCapOutput);
        TextView txtNumBaths = (TextView) findViewById(R.id.output_num_baths);
        String numBathsOutput = "Number of Baths a day: " + Integer.toString(display.getNumBaths());
        txtNumBaths.setText(numBathsOutput);

        // Currently interested in.
        View cardNumLtrs = findViewById(R.id.card_num_ltrs);
        TextView txtNumLtrs = (TextView) cardNumLtrs.findViewById(R.id.output_value);
        int NumLtrsRounded = Math.round(display.getNumLtrs());
        txtNumLtrs.setText(String.valueOf(NumLtrsRounded));
        TextView txtNumLtrsDesc = (TextView) cardNumLtrs.findViewById(R.id.output_desc);
        txtNumLtrsDesc.setText(R.string.str_output_litres_desc);
        TextView txtNumLtrsCopy = (TextView) cardNumLtrs.findViewById(R.id.output_copy);
        txtNumLtrsCopy.setText(R.string.str_output_litres_copy);

        View cardChangeBaths = findViewById(R.id.card_change_baths);
        TextView txtChangeBaths = (TextView) cardChangeBaths.findViewById(R.id.output_value);
        int ChangeBathsRounded = Math.round(display.getBathsBeforeChange());
        txtChangeBaths.setText(String.valueOf(ChangeBathsRounded));
        TextView txtChangeBathsDesc = (TextView) cardChangeBaths.findViewById(R.id.output_desc);
        txtChangeBathsDesc.setText(R.string.str_output_change_desc);
        TextView txtChangeBathsCopy = (TextView) cardChangeBaths.findViewById(R.id.output_copy);
        txtChangeBathsCopy.setText(R.string.str_ouput_change_copy);

        View cardKGCuSO4 = findViewById(R.id.card_kg_suo4);
        TextView txtKGCuSO4 = (TextView) cardKGCuSO4.findViewById(R.id.output_value);
        int KGCuSO4Rounded = Math.round(display.getKgCuSO4());
        txtKGCuSO4.setText(String.valueOf(KGCuSO4Rounded));
        TextView txtKGCuSO4Desc = (TextView) cardKGCuSO4.findViewById(R.id.output_desc);
        txtKGCuSO4Desc.setText(R.string.str_output_cuso4_desc);
        TextView txtKGCuSO4Copy = (TextView) cardKGCuSO4.findViewById(R.id.output_copy);
        txtKGCuSO4Copy.setText(R.string.str_ouput_cuso4_copy);
    }

    private void startStorePageActivity() {
        Intent i = new Intent(this, StorePageActivity.class);
        startActivity(i);
    }
}
