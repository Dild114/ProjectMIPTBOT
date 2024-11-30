package tgBot.parser.ourParsers;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import tgBot.parser.Article;
import tgBot.parser.SiteParser;

/**
 * Парсер сайта: <a href="https://habr.com/ru/news/page1">habr</a>
 */
@Slf4j
public class ParserByVadim implements SiteParser {
  private static final String url = "https://habr.com/ru/news/page1";
  private static final String site = "https://habr.com";

  @Override
  public List<Article> parseAllSite() {
    return parseAllSite(url);
  }

  @Override
  public List<Article> parseAllSite(String html) {
    final List<Article> result = new ArrayList<>();

    try {
      Document document = Jsoup.parse(html);
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
    return result.stream().toList();
  }
}