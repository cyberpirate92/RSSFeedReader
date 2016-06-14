package ravitheja.com.rssfeedreader;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements ParserListener {

    private ListView listView;
    private ListAdapter adapter;
    private URL feedURL;
    private ProgressDialog progressDialog;
    FeedExtractor extractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            feedURL = new URL("http://www.feedforall.com/sample.xml");
            listView = (ListView) findViewById(R.id.listView);
            adapter = new ListAdapter(this.getApplicationContext(),feedURL);
            listView.setAdapter(adapter);

            // starting the background parsing thread...
            extractor = new FeedExtractor(feedURL,this);
            extractor.start();
        }
        catch(MalformedURLException mue) {
            displayToast("Invalid URL");
        }

    }
    public void displayToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void didStartParsing() {
        displayToast("Please wait... fetching feeds");
        progressDialog = ProgressDialog.show(this, "Loading", "Please wait", true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void didFinishParsing() {
        displayToast("Parsing complete");
        adapter.clear();
        adapter.addItems(extractor.getFeedList());
        progressDialog.dismiss();
    }

    @Override
    public void didFinishWithException(Exception e) {
        displayToast("Parsing could not be completed due to errors");
        progressDialog.dismiss();
    }


}
