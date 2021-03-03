package views;
import androidx.appcompat.widget.Toolbar;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.Menu;


import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;

import interfaces.IHelp;
import presenters.HelpPresenter;

public class HelpActivity extends AppCompatActivity implements IHelp.View {

    private String fromActivity;
    private IHelp.Presenter presenter;
    private Context myContext;
    private WebView mWebview ;

    String TAG = "AppAndroidS/HelpActivity";

    public HelpActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "Starting onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        myContext = this;
        presenter = new HelpPresenter(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Ayuda");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Pressing Back Button");
                    onBackPressed();
                }
            });
        } else {
            Log.d(TAG, "Error loading toolbar");
        }

        String outsideActivity = getIntent().getStringExtra("activity");

        if (isNetDisponible()) {
            mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
            mWebview.setWebViewClient(new WebViewClient() {

                @SuppressWarnings("deprecation")
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(HelpActivity.this, description, Toast.LENGTH_SHORT).show();
                }

                @TargetApi(android.os.Build.VERSION_CODES.M)
                @Override
                public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                    // Redirect to deprecated method, so you can use it in all SDK versions
                    onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
                }

            });

            if (outsideActivity != null && isNetDisponible()) {

                switch (outsideActivity) {
                    case "main":
                        mWebview.loadUrl("https://fregoneti.github.io/AppAndroidS/main.html");
                        break;
                    case "formulario":
                        mWebview.loadUrl("https://fregoneti.github.io/AppAndroidS/formulario.html");
                        break;
                    case "search":
                        mWebview.loadUrl("https://fregoneti.github.io/AppAndroidS/search.html");
                        break;
                }

               // setContentView(mWebview);

            }
        } else {
            presenter.onErrorConnection();
        }
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Starting onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Starting onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Starting onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Starting onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "Starting onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Starting onDestroy");
        super.onDestroy();
    }

    private boolean isNetDisponible() {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
        mWebview = findViewById(R.id.webview);

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    @Override
    public void errorConnection() {

    }
}
