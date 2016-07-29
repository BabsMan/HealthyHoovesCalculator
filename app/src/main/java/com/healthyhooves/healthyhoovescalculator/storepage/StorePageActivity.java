package com.healthyhooves.healthyhoovescalculator.storepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.healthyhooves.healthyhoovescalculator.R;

public class StorePageActivity extends AppCompatActivity {

    Menu menu;

    StorePageFragment fragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fragment = new StorePageFragment();
        setContentView(R.layout.activity_store);
        fragment = (StorePageFragment) getSupportFragmentManager().findFragmentById(R.id.page_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_store_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_open_in_browser) {

            fragment.openPageInBrowser();
            return true;
        }
        else if (id == R.id.action_refresh) {

            fragment.refreshPage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if(!fragment.canGoBack()) {
            super.onBackPressed();
        }
    }
}
