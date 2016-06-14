package ravitheja.com.rssfeedreader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

/**
 * List adapter to populate the ListView, with RSS feeds
 */

public class ListAdapter extends BaseAdapter {

    private ArrayList<RSSElement> elements;
    private URL feedURL;
    private Context context;

    public ListAdapter(Context context, URL feedURL) {
        this.context = context;
        this.feedURL = feedURL;
        this.elements = new ArrayList<>();
    }

    public void addItems(ArrayList<RSSElement> list) {
        for(RSSElement element : list) {
            this.elements.add(element);
            Log.d("RSS Reader",element.toString());
        }
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.elements.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(elements == null) {
            return 0;
        }
        else {
            return elements.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if(elements == null) {
            return null;
        }
        else {
            return elements.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.list_row, parent, false);

        TextView titleView, descriptionView;
        titleView = (TextView) row.findViewById(R.id.titleView);
        descriptionView = (TextView) row.findViewById(R.id.descriptionView);

        RSSElement element = this.elements.get(position);
        titleView.setText(element.getTitle());
        descriptionView.setText(element.getDescription());

        return row;
    }
}
