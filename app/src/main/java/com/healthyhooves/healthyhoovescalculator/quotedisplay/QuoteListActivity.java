package com.healthyhooves.healthyhoovescalculator.quotedisplay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.healthyhooves.healthyhoovescalculator.DeleteQuoteDialog;
import com.healthyhooves.healthyhoovescalculator.R;
import com.healthyhooves.healthyhoovescalculator.calculator.CalculatorDialog;
import com.healthyhooves.healthyhoovescalculator.databaseutils.QuotesDataSource;
import com.healthyhooves.healthyhoovescalculator.model.QuoteObj;

import java.util.List;

public class QuoteListActivity extends AppCompatActivity {

    private Context context;

    private QuotesDataSource dataSource;
    private SimpleArrayAdapter adapter;
    private ListView listView;
    private TextView txtNoQuotesDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_list);

        context = this;

        dataSource = new QuotesDataSource(this);
        dataSource.open();

        txtNoQuotesDisplay = (TextView) findViewById(R.id.txt_no_items);

        // Using the SimpleCursorAdapter to show the elements in a ListView.
        listView = (ListView) findViewById(R.id.quote_list);
        setListAdapter();
        listView.setDivider(this.getResources().getDrawable(R.drawable.transparent_color));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                QuoteObj item = adapter.getItem(position);
                goToShowQuoteActivity(item);
            }
        });

        FloatingActionButton newQuote = (FloatingActionButton) findViewById(R.id.btn_new_quote);
        newQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayCalculatorDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    /**
     * Launches an instance of {@link CalculatorDialog}.  The {@link CalculatorDialog} contains
     * all the elements to run the quote calculation.
     */
    private void displayCalculatorDialog() {

        CalculatorDialog calculator = new CalculatorDialog(this, this);
        calculator.calcDialog.show();
        calculator.setOnClickListener();
    }

    private void displayDeleteConfirmDialog(final QuoteObj quote) {

        DeleteQuoteDialog dialog = new DeleteQuoteDialog(this, this, quote);
        dialog.dialog.show();
    }

    public void deleteQuoteFromDatabase(final QuoteObj quote) {

        dataSource.deleteQuote(quote);
        setListAdapter();
    }

    private void setListAdapter() {

        List<QuoteObj> quotes = dataSource.getAllQuotes();
        adapter = new SimpleArrayAdapter(this, quotes);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        toggleNoQuoteDisplay(quotes.size() != 0);
    }

    private void toggleNoQuoteDisplay(boolean toggle) {

        if (toggle) {
            txtNoQuotesDisplay.setVisibility(View.GONE);
        } else {
            txtNoQuotesDisplay.setVisibility(View.VISIBLE);
        }
    }

    public void goToShowQuoteActivity(final QuoteObj obj) {

        Intent i = new Intent(this, ShowQuoteActivity.class);
        i.putExtra(ShowQuoteActivity.EXTRA_QUOTE, obj);
        startActivity(i);
    }

    public class SimpleArrayAdapter extends ArrayAdapter<QuoteObj> {

        private List<QuoteObj> quotes;

        public SimpleArrayAdapter(Context context, List<QuoteObj> quotes) {
            super(context, 0, quotes);
            this.quotes = quotes;
        }

        @Override
        public int getCount() {
            return quotes.size();
        }

        @Override
        public QuoteObj getItem(final int position) {
            return quotes.get(position);
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, final ViewGroup parent) {

            final QuoteObj quote = getItem(position);

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.list_item_quote, parent, false);
            ImageButton btnDelete = (ImageButton) rowView.findViewById(R.id.btn_close);
            btnDelete.setFocusable(false);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteFileFromDatabase(quote);
                }
            });

            TextView txtDate = (TextView) rowView.findViewById(R.id.txt_quote_date);
            txtDate.setText(String.valueOf(quote.getDate()));

            TextView txtNumCows = (TextView) rowView.findViewById(R.id.txt_quote_num_cows);
            String strNumCows = String.valueOf(quote.getNumCows()) + " Cows";
            txtNumCows.setText(strNumCows);

            TextView txtBathCap = (TextView) rowView.findViewById(R.id.txt_quote_bath_cap);
            String strBathCap = String.valueOf(quote.getBathCap()) + "L Bath";
            txtBathCap.setText(strBathCap);

            TextView txtNumBaths = (TextView) rowView.findViewById(R.id.txt_quote_num_baths);
            String strNumBaths = String.valueOf(quote.getNumBaths()) + " Baths a Day";
            txtNumBaths.setText(strNumBaths);

            return rowView;
        }

        void deleteFileFromDatabase(final QuoteObj quote) {
            displayDeleteConfirmDialog(quote);
        }
    }

}
