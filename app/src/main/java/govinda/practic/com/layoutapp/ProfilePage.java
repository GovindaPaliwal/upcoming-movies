package govinda.practic.com.layoutapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by admin on 09-01-2016.
 */
public class ProfilePage extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        webView=(WebView)findViewById(R.id.w_view);
        webView.loadUrl("Your_site_name");
    }
}
