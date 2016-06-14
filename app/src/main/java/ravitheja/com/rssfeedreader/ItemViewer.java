package ravitheja.com.rssfeedreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ItemViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_viewer);

        Intent intent = this.getIntent();
        if(intent.hasExtra("url")) {
            WebView webView = (WebView) findViewById(R.id.webView);
            if(webView != null) {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(intent.getStringExtra("url"));
            }
            else {
                displayToast("Cannot load webpage !");
            }
        }
    }

    public void displayToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
