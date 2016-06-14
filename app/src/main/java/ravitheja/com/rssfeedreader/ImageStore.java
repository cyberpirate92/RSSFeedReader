package ravitheja.com.rssfeedreader;

import android.graphics.Bitmap;

/**
 * Created by zen on 14/6/16.
 */
public class ImageStore {

    private Bitmap bitmap;
    private int position;

    public ImageStore(Bitmap bitmap, int position) {
        this.bitmap = bitmap;
        this.position = position;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
