package ravitheja.com.rssfeedreader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by zen on 10/6/16.
 */
public class ListAdapter extends BaseAdapter {

    private ArrayList<RSSElement> elements;
    private URL feedURL;
    private Context context;

    public ListAdapter(Context context, URL feedURL) {
        this.context = context;
        this.feedURL = feedURL;
    }

    public void addItem(ArrayList<RSSElement> list) {
        for(RSSElement element : list) {
            this.elements.add(element);
        }
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
        return null;
    }
}
