package com.healthyhooves.healthyhoovescalculator.storepage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.healthyhooves.healthyhoovescalculator.R;

public class StorePageFragment extends Fragment {


    private ProgressBar progress;
    private WebView web;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_store, container, false);

        progress = (ProgressBar) v.findViewById(R.id.progress_page);

        web = (WebView) v.findViewById(R.id.webView_content);
        web.setWebViewClient(new CustomWebViewClient());
        web.setWebChromeClient(new CustomWebChromeClient());
        web.loadUrl("http://healthyhooves.eu/products.html");

        return v;
    }


    /**
     * Checks that the web-view can navigate back.
     *
     * @return boolean based on whether a web-view can navigate back.
     */
    public boolean canGoBack() {

        if(web.canGoBack()) {

            web.goBack();
            return true;
        }
        else {

            return false;
        }
    }

    /**
     * Starts an intent that launches the Url as an intent to be opened in a browser.
     */
    public void openPageInBrowser() {

        Intent openInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(web.getOriginalUrl()));
        startActivity(openInBrowser);
    }

    /**
     * Force the page to refresh.
     */
    public void refreshPage() {

        web.reload();
    }

    private static class CustomWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            return false;
        }
    }

    private class CustomWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(final WebView view, final int newProgress) {

            Log.d("StorePageFragment", String.valueOf(newProgress));
            progress.setProgress(newProgress);
            progress.setVisibility(newProgress < 100 ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onReceivedTitle(final WebView view, final String title) {
            super.onReceivedTitle(view, title);

            Log.d("StorePageFragment", title);
        }
    }
}
