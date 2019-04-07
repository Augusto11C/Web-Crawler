package web.crawler.cloud.test.entity;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Url {

    @Id
    private ObjectId _id;
    //@Indexed(unique = false)
    private String urlToCrawler = "";



    public Url(String urlToCrawler) {
        this.urlToCrawler = urlToCrawler;
    }

    @PersistenceConstructor
    public Url(ObjectId _id, String urlToCrawler) {
        this._id = _id;
        this.urlToCrawler = urlToCrawler;
    }

    public String getUrl() {
        return urlToCrawler;
    }

    public void setUrl(String urlToCrawler) {
        this.urlToCrawler = urlToCrawler;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
