package tgBot.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Парсер сайта: <a href="https://3dnews.ru/">3dnews</a>
 */
@Slf4j
public class NewsParser implements SiteParser {
  private static final String site = "https://3dnews.ru/";

  @Override
  public List<Article> parseAllSite() {
    String url = "https://3dnews.ru/";
    List<Article> articles = null;
    try {
      Document document = Jsoup.connect(url).get();
      articles = parseAllSite(url, document);
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return articles;
  }

  @Override
  public List<Article> parseAllSite(String url, Document document) {
    final List<Article> data = new ArrayList<>();
    try {
      var posts = document.select("div#news.content-block div.content-block-data.white");
      System.out.println(posts.size());
      for (var post : posts) {
        String title = post.select("table.nomargins a").text();
        String link = site + post.select("table.nomargins a").attr("href");
        String text = post.select("div.teaser").text();
        String date = "None";

        data.add(new Article(title, link, text.substring(0, text.length() - 13), date));
      }
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return data;
  }
}
