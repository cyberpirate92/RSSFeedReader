package ravitheja.com.rssfeedreader;

/**
 * Created by zen on 14/6/16.
 */
public interface ImageDownloadListener {
    void hasStartedDownload();
    void hasCompletedDownload();
    void hasFailedToDownload();
}
