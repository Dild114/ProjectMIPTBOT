package tgBot.parser;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * Парсер сайта: <a href="https://3dnews.ru/">3dnews</a>
 */
@Slf4j
public class FourthParser implements SiteParser {
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
      for (var post : posts) {
        String title = post.select("table.nomargins a").text();
        String link = site + post.select("table.nomargins a").attr("href");
        String text = post.select("div.teaser").text();
        String date = "None";

        data.add(new Article(title, link, text, date));
      }
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return data;
  }
}
