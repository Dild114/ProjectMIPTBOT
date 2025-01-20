package tgBot.parser;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * Парсер сайта: <a href="https://habr.com/ru/news/page1">habr</a>
 */
@Slf4j
public class FirstParser implements SiteParser {
  private static final String site = "https://habr.com";

  @Override
  public List<ArticleParser> parseAllSite() {
    String url = "https://habr.com/ru/news/page1";
    List<ArticleParser> articles = null;
    try {
      Document document = Jsoup.connect(url).get();
      articles = parseAllSite("https://habr.com/ru/news/page1", document);
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return articles;
  }

  @Override
  public List<ArticleParser> parseAllSite(String url, Document document) {
    final List<ArticleParser> data = new ArrayList<>();
    try {
      var posts = document.select("article");
      for (int i = 0; i < posts.size() && i < 10; i++) {
        Element post = posts.get(i);
        String title = post.select("h2.tm-title").text();
        String link = site + post.select("h2.tm-title a.tm-title__link").attr("href");
        String text = post.select("div.tm-article-body").text();
        String date = post.select("div.tm-article-snippet__meta-container span a.tm-article-datetime-published").text();

        data.add(new ArticleParser(title, link, text.substring(0, text.length() - 13), date));
      }
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return data;
  }
}
