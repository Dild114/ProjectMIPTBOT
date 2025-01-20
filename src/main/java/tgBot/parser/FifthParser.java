package tgBot.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * Парсер сайта: <a href="https://www.ixbt.com/news/">IXBT</a>
 */
@Slf4j
public class FifthParser implements SiteParser {
  private static final String site = "https://www.ixbt.com/news/";

  @Override
  public List<ArticleParser> parseAllSite() {
    List<ArticleParser> articles = null;
    try {
      Document document = Jsoup.connect(site).get();
      articles = parseAllSite(site, document);
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", site, e);
    }
    return articles;
  }

  @Override
  public List<ArticleParser> parseAllSite(String url, Document document) {
    final List<ArticleParser> data = new ArrayList<>();
    try {
      var posts = document.select("div.g-grid_column.g-grid_column__big li.item.item__border");
      for (var post : posts) {
        String[] title = post.select("a").text().split(" ");
        String link = site + post.select("a").attr("href");
        String text = post.select("div.item__text__top").text();
        String date = post.select("span.time_iteration_icon_light").text();

        data.add(new ArticleParser(String.join(" ", Arrays.copyOfRange(title, 1, title.length)), link, text, date));
      }
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return data;
  }
}
