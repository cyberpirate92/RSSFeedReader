package ravitheja.com.rssfeedreader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * A utility class that can be used to download an image in the background
 */
public class ImageDownloader extends Thread implements ImageDownloadListener {

    private URL imageURL;
    private ImageDownloadListener listener;
    private Bitmap downloadedImage;
    private ImageView targetImageView;

    public ImageDownloader(URL imageURL, ImageDownloadListener listener, ImageView imageView) {
        this.imageURL = imageURL;
        this.listener = listener;
        this.targetImageView = imageView;
    }

    public Bitmap getDownloadedImage() {
        return this.downloadedImage;
    }

    @Override
    public void run() {
        try {
            this.hasStartedDownload();
            InputStream inputStream = imageURL.openStream();
            downloadedImage = BitmapFactory.decodeStream(inputStream);
            this.hasCompletedDownload();
            this.targetImageView.setImageBitmap(this.downloadedImage);
        }
        catch(Exception e) {
            Log.d("RSS Image Downloader","Cannot download image : " + e.toString());
            this.hasFailedToDownload();
        }
    }

    @Override
    public void hasStartedDownload() {
        if(listener == null) {
            return;
        }
        if(listener instanceof Activity) {
            ((Activity) listener).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.hasStartedDownload();
                }
            });
        }
        else {
            listener.hasStartedDownload();
        }
    }

    @Override
    public void hasCompletedDownload() {
        if(listener == null) {
            return;
        }
        if(listener instanceof Activity) {
            ((Activity) listener).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.hasCompletedDownload();
                }
            });
        }
        else {
            listener.hasCompletedDownload();
        }
    }

    @Override
    public void hasFailedToDownload() {
        if(listener == null) {
            return;
        }
        if(listener instanceof Activity) {
            ((Activity) listener).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.hasFailedToDownload();
                }
            });
        }
        else {
            listener.hasFailedToDownload();
        }
    }
}
