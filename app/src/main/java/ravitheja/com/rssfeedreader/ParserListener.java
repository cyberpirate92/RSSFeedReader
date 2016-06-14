package ravitheja.com.rssfeedreader;

/**
 * Created by zen on 14/6/16.
 */

public interface ParserListener {

    void didStartParsing();
    void didFinishParsing();
    void didFinishWithException(Exception e);
}
