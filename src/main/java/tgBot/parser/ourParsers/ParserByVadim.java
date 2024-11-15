package src.main.java.tgBot.parser.ourParsers;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import src.main.java.tgBot.parser.Article;
import src.main.java.tgBot.parser.SiteParser;

/**
 * Парсер сайта: <a href="https://habr.com/ru/news/page1">habr</a>
 */
public class ParserByVadim implements SiteParser {
  private static final String url = "https://habr.com/ru/news/page1";
  private static final String site = "https://habr.com";
  @Override
  public List<Article> parseAllSite() {
    final List<Article> result = new ArrayList<>();

    try {
      Document document = Jsoup.connect(url).get();
      var posts = document.select("article");
      for (var post : posts) {
        String title = post.select("h2.tm-title").text();
        String link = site + post.select("h2.tm-title a.tm-title__link").attr("href");
        String text = post.select("div.tm-article-body").text();
        String date = post.select("div.tm-article-snippet__meta-container span a.tm-article-datetime-published ").text();
        result.add(new Article(link, title, text, date));
      }
    } catch (Exception e) {
      System.out.println("Ошибка во время парсинга сайта: " + url);
    }
    return result.stream().toList();
  }
}
