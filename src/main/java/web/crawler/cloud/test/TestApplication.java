package web.crawler.cloud.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import web.crawler.cloud.test.crawler.WebCrawler;


@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }


    @Autowired
    WebCrawler webCrawler;

/*    @Override
    public void run(String... args) throws Exception {
*//*        Url newUrl = new Url("www.augusto.com");
        urlRepository.save(newUrl);*//*

        try {

            webCrawler.getPageUrls("https://colorlib.com/wp/free-css3-html5-search-form-examples/");

            Url fromDbUrl = urlRepository.findByUrl("www.augusto.com");
            System.out.println(fromDbUrl.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
