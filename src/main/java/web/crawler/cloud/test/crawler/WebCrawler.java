package web.crawler.cloud.test.crawler;

import org.apache.commons.lang3.StringUtils;
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

    private int timeout = 60000;   // one minute
    private HashSet<String> urls;

    public WebCrawler() {
        urls = new HashSet<String>();
    }

    @Autowired
    UrlRepository urlRepository;

    public void getPageUrls(String url, int duration) {


        if (!urls.contains(url) && url != null) {
            try {

                timeout = duration;
                urls.add(url);

                //Saving on BD
                Url newUrl = new Url(url);
                urlRepository.save(newUrl);

                Document document = Jsoup.parse(new URL(url),timeout);
                Elements urlsFromPage = document.select("a[href]");

                for (Element oneUrlFromPage : urlsFromPage) {
                    System.out.println("--------text-------");
                    System.out.println(oneUrlFromPage.text());
                    System.out.println("--------html-------");
                    System.out.println(oneUrlFromPage.html());
                    System.out.println("--------toString-------");
                    System.out.println(oneUrlFromPage.toString());

                    String href = oneUrlFromPage.attr("href");
                    if (StringUtils.isBlank(href)
                            ||  href.startsWith("#")) {
                        continue;
                    }

                    getPageUrls(oneUrlFromPage.attr("href"), timeout);//abs:href
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
