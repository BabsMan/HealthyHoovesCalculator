package com.healthyhooves.healthyhoovescalculator.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.healthyhooves.healthyhoovescalculator.R;
import com.healthyhooves.healthyhoovescalculator.model.QuoteObj;
import com.healthyhooves.healthyhoovescalculator.quotedisplay.QuoteListActivity;
import com.healthyhooves.healthyhoovescalculator.quotedisplay.ShowQuoteActivity;
import com.healthyhooves.healthyhoovescalculator.storepage.StorePageActivity;

/**
 * Created by Graeme Grier on 12/10/15.
 *
 * Main activity of the application.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToStoreActivity() {

        Intent openStore = new Intent(this, StorePageActivity.class);
        startActivity(openStore);
    }

    public void goToShowQuoteActivity(final QuoteObj obj) {

        Intent i = new Intent(this, ShowQuoteActivity.class);
        i.putExtra(ShowQuoteActivity.EXTRA_QUOTE, obj);
        startActivity(i);
    }

    public void goToShowQuoteListActivity() {

        Intent i = new Intent(this, QuoteListActivity.class);
        startActivity(i);
    }
}
