package tgBot.parser;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * Парсер сайта: <a href="https://timeweb.com/ru/community/">community</a>
 */
@Slf4j
public class SecondParser implements SiteParser {
  private static final String site = "https://timeweb.com";

  @Override
  public List<Article> parseAllSite() {
    String url = "https://timeweb.com/ru/community/";
    try {
      Document document = Jsoup.connect(url).get();
      return parseAllSite("url", document);
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return null;
  }

  @Override
  public List<Article> parseAllSite(String url, Document document) {
    final List<Article> data = new ArrayList<>();
    try {
      if (document == null) document = Jsoup.connect(url).get();
      var posts = document.select("div.js-pagination-element.cm-article-main.pt-16.pb-24.mt-32\\:md.pos-rel.zi-5");
      for (var post : posts) {
        String title = post.select("h2.mb-12 a.txt-secondary-9").text();
        String link = site + post.select("h2.mb-12 a.txt-secondary-9").attr("href");
        String text = post.select("div.font-style-1.mb-24.txt-secondary-9").text();
        String date = post.select("time").text();
        data.add(new Article(title, link, text, date));
      }
    } catch (Exception e) {
      log.error("Ошибка во время парсинга сайта: {}", url, e);
    }
    return data;
  }
}
