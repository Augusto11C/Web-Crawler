package web.crawler.cloud.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.crawler.cloud.test.crawler.WebCrawler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    WebCrawler webCrawler;

    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @RequestMapping(value = "crawler_param", method = RequestMethod.POST)
    @ResponseBody
    public List<String> crawlerTest(@RequestParam String urlToCrawler, @RequestParam int mapMaxSize) {

        if (urlToCrawler != null ) {

            System.out.println(mapMaxSize);

            webCrawler.getPageUrls(urlToCrawler,mapMaxSize);
            while (webCrawler.getUrls().size() < 10) ;

            return convertToListOfStrings(webCrawler.getUrls());
        }

        return null;

    }

    private List<String> convertToListOfStrings(HashSet<String> urls) {
        return new ArrayList<String>(urls);
    }
}
