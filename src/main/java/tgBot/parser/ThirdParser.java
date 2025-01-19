package tgBot.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * Парсер сайта: <a href="https://xakep.ru/">xakep</a>
 */
@Slf4j
public class ThirdParser implements SiteParser {
  @Override
  public List<ArticleParser> parseAllSite() {
    String url = "https://xakep.ru/";
    try {
      Document document = Jsoup.connect(url).get();
      return parseAllSite("url", document);
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return null;
  }

  @Override
  public List<ArticleParser> parseAllSite(String url, Document document) {
    final List<ArticleParser> data = new ArrayList<>();
    try {
      var posts = document.select("div.bd-block-row article");
      for (int i = 0; i < posts.size() && i < 10; i++) {
        Element post = posts.get(i);
        String title = post.select("header h3.entry-title").text();
        String link = post.select("header h3.entry-title a").attr("href");
        String text = post.select("p.block-exb").text();
        String date = DateParser(link);
        data.add(new ArticleParser(title, link, text, date));
      }
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return data;
  }

  private String DateParser(String url) {
    String date = "None";
    try {
      Document document = Jsoup.connect(url).get();
      String[] list = document.select("div.bd-container div.bdaia-post-date span").text().split(" ");
      date = list[0] + " " + list[1] + " " + list[2];
    } catch (IOException e) {
      return date;
    }
    return date;
  }
}

