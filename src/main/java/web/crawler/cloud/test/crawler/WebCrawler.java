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
    private final int TIMEOUT = 30000;
    private HashSet<String> urls;

    public WebCrawler() {
        urls = new HashSet<String>();
    }

    @Autowired
    UrlRepository urlRepository;

    public void getPageUrls(String url, int mapMaxSize) {

/*        if (urls.size() > repetition) {
            return;
        }*/

        if (!urls.contains(url) && url != null && mapMaxSize > 0) {
            System.out.println(mapMaxSize);
            try {

                urls.add(url);

                //Saving on BD
                Url newUrl = new Url(url);
                urlRepository.save(newUrl);

                Document document = Jsoup.parse(new URL(url), TIMEOUT);
                Elements urlsFromPage = document.select("a[href]");
                int auxRepetition = mapMaxSize - 1;
                for (Element oneUrlFromPage : urlsFromPage) {
                    if (urls.size() > mapMaxSize) {
                        return;
                    }
                    String href = oneUrlFromPage.attr("href");
                    if (StringUtils.isBlank(href)
                            || href.startsWith("#")) {
                        continue;
                    }
                    auxRepetition = auxRepetition - 1;
                    System.out.println(auxRepetition + "auxRepetition");
                    getPageUrls(oneUrlFromPage.attr("abs:href"), auxRepetition);//abs:href
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
