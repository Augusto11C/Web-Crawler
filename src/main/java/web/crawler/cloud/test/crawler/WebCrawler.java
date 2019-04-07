package web.crawler.cloud.test.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import web.crawler.cloud.test.entity.Url;
import web.crawler.cloud.test.repository.UrlRepository;

import java.net.URL;
import java.util.HashSet;

@EnableMongoRepositories
@Service
public class WebCrawler {

    static final int TIMEOUT = 30000;   // one minute
    private HashSet<String> urls;

    public WebCrawler() {
        urls = new HashSet<String>();
    }

    @Autowired
    UrlRepository urlRepository;

    public void getPageUrls(String url) {

        if (urls.size() > 10) {
            return;
        }

        if (!urls.contains(url) && url != null) {
            try {
                urls.add(url);

                //Saving on BD
                Url newUrl = new Url(url);
                urlRepository.save(newUrl);

                Document document = Jsoup.parse(new URL(url),TIMEOUT);
                Elements urlsFromPage = document.select("a[href]");

                for (Element oneUrlFromPage : urlsFromPage) {

                    getPageUrls(oneUrlFromPage.attr("abs:href"));
                }

            } catch (Exception e) {
                System.err.println("URL:" + url + " " + e.getMessage());
                e.printStackTrace();
            }
        }

    }


    public HashSet<String> getUrls() {
        return urls;
    }

}
