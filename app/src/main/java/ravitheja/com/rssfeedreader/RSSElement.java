package ravitheja.com.rssfeedreader;

/**
 * POJO class to store a single RSS item
 */

public class RSSElement {

    private String title, link, description;

    public RSSElement() {

    }

    public RSSElement(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;

    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.title + " | " + this.link + " | " + this.description;
    }
}
