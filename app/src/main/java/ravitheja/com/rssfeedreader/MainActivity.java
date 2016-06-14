package ravitheja.com.rssfeedreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements ParserListener {

    private ListView listView;
    private ListAdapter adapter;
    private URL feedURL;
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
            extractor = new FeedExtractor(feedURL,this);
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
    }

    @Override
    public void didFinishParsing() {
        displayToast("Parsing complete");

    }

    @Override
    public void didFinishWithException(Exception e) {
        displayToast("Parsing could not be completed due to errors");
    }
}
