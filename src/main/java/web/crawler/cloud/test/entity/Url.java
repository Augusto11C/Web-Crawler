package web.crawler.cloud.test.entity;




public class Url {

    private String urlToCrawler = "";



    public Url(String urlToCrawler) {
        this.urlToCrawler = urlToCrawler;
    }



    public String getUrl() {
        return urlToCrawler;
    }

    public void setUrl(String urlToCrawler) {
        this.urlToCrawler = urlToCrawler;
    }


}
