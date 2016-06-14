package ravitheja.com.rssfeedreader;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.Image;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

/**
 * List adapter to populate the ListView, with RSS feeds
 */

public class ListAdapter extends BaseAdapter implements ImageDownloadListener {

    private ArrayList<RSSElement> elements;
    private Context context;
    private Typeface titleFont, descriptionFont;

    public ListAdapter(Context context) {
        this.context = context;
        this.elements = new ArrayList<>();
        AssetManager manager = context.getAssets();
        titleFont = Typeface.createFromAsset(manager,"fonts/marlboro.ttf");
        descriptionFont = Typeface.createFromAsset(manager,"fonts/monaco.ttf");
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
    public RSSElement getItem(int position) {
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null ) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list_row, parent, false);
            convertView = row;
        }
        TextView titleView, descriptionView;
        ImageView imageView;

        titleView = (TextView) convertView.findViewById(R.id.titleView);
        descriptionView = (TextView) convertView.findViewById(R.id.descriptionView);
        imageView = (ImageView) convertView.findViewById(R.id.imageView);

        RSSElement element = this.elements.get(position);
        titleView.setText(element.getTitle());
        titleView.setTypeface(titleFont);
        descriptionView.setText(Html.fromHtml(element.getDescription()));
        descriptionView.setTypeface(descriptionFont);

        // starting the image download thread...
        Bitmap bitmap = this.elements.get(position).getImageBitmap();
        if(bitmap == null ) {
            if (element.getImageURL() != null) {
                try {
                    URL imageURL = new URL(element.getImageURL());
                    ImageDownloader downloader = new ImageDownloader(imageURL, this, imageView, position);
                    downloader.start();
                } catch (Exception e) {
                    Log.d("RSS Feed Extractor", "Error in image url: " + e);
                }
            }
        }
        else {
            imageView.setImageBitmap(bitmap);
        }

        return convertView;
    }

    @Override
    public void hasStartedDownload() {

    }

    @Override
    public void hasCompletedDownload(ImageStore imageStore) {
        this.elements.get(imageStore.getPosition()).setImageBitmap(imageStore.getBitmap());
    }

    @Override
    public void hasFailedToDownload() {

    }
}
