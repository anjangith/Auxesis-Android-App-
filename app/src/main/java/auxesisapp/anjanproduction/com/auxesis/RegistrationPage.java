package auxesisapp.anjanproduction.com.auxesis;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class RegistrationPage extends AppCompatActivity {
    private WebView webView;
    private ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_registration_page);
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pbar=(ProgressBar)findViewById(R.id.progressBar2);
        pbar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.myColor), android.graphics.PorterDuff.Mode.MULTIPLY);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("REGISTRATION");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     finish();
                                                 }
                                             }
        );

        // Makes Progress bar Visible


        webView = (WebView) findViewById(R.id.webview);
        webView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
       /* webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if(progress == 100)
                    setTitle(R.string.app_name);
            }
        });*/
        webView.setWebViewClient(new HelloWebViewClient(pbar));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.registration.auxesis.org.in");


    }


    private class HelloWebViewClient extends WebViewClient {
        private ProgressBar progressBar;

        public HelloWebViewClient(ProgressBar progressBar){
            this.progressBar=progressBar;
            progressBar.setVisibility(View.VISIBLE);

        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
