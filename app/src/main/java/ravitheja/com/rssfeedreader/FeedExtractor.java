package ravitheja.com.rssfeedreader;

import android.app.Activity;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by zen on 14/6/16.
 */

public class FeedExtractor implements Runnable {

    private URL feedURL;
    private volatile ArrayList<RSSElement> items;
    private ParserListener callbackListener;

    public FeedExtractor(URL url, ParserListener listener) {
        this.feedURL = url;
        this.items = new ArrayList<RSSElement>();
        this.callbackListener = listener;
    }

    public void run() {
        try {
            // notifying callback about the start of the process
            didStartParsing();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(feedURL.openStream()));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("item");
            for(int i=0; i<nodeList.getLength(); i++) {

                RSSElement rssElement =  new RSSElement();
                Node node = nodeList.item(i);
                Element element = (Element) node;

                NodeList titleList = element.getElementsByTagName("title");
                NodeList linkList = element.getElementsByTagName("link");
                NodeList descList = element.getElementsByTagName("description");

                rssElement.setTitle(((Node)titleList.item(0)).getNodeValue());
                rssElement.setLink(((Node)linkList.item(0)).getNodeValue());
                rssElement.setDescription(((Node)descList.item(0)).getNodeValue());

                items.add(rssElement);
            }
            didFinishParsing();
        }
        catch (Exception e) {
            Log.d("RSS reader",e.toString());
            didFinishWithException(e);
        }
    }

    private void didStartParsing() {
        if(callbackListener == null) {
            return;
        }
        if(callbackListener instanceof Activity) {
            ((Activity) callbackListener).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callbackListener.didStartParsing();
                }
            });
        }
        else {
            callbackListener.didStartParsing();
        }
    }

    private void didFinishParsing() {
        if(callbackListener == null) {
            return;
        }
        if(callbackListener instanceof Activity) {
            ((Activity) callbackListener).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callbackListener.didFinishParsing();
                }
            });
        }
        else {
            callbackListener.didFinishParsing();
        }
    }

    private void didFinishWithException(Exception e) {
        if(callbackListener == null) {
            return;
        }
        final Exception exception = e;
        if(callbackListener instanceof Activity) {
            ((Activity) callbackListener).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callbackListener.didFinishWithException(exception);
                }
            });
        }
        else {
            callbackListener.didFinishWithException(exception);
        }
    }
}
