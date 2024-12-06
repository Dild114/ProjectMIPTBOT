package tgBot.parser;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * Парсер сайта: <a href="https://habr.com/ru/news/page1">habr</a>
 */
@Slf4j
public class HabrParser implements SiteParser {
  private static final String site = "https://habr.com";

  @Override
  public List<Article> parseAllSite() {
    return parseAllSite("https://habr.com/ru/news/page1");
  }

  @Override
  public List<Article> parseAllSite(String url) {
    final List<Article> result = new ArrayList<>();

    try {
      Document document = Jsoup.parse(url);
      var posts = document.select("article");
      for (var post : posts) {
        String title = post.select("h2.tm-title").text();
        String link = site + post.select("h2.tm-title a.tm-title__link").attr("href");
        String text = post.select("div.tm-article-body").text();
        String date = post.select("div.tm-article-snippet__meta-container span a.tm-article-datetime-published").text();
        result.add(new Article(link, title, text, date));
      }
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return result;
  }
}
