package tgBot.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FifthParserTest {

  @Test
  public void testParseAllSiteWithCustomHtml() {
    String html = """
                <html>
                    <body>
                        <div class="g-grid_column g-grid_column__big">
                            <li class="item item__border">
                                <a href="/link1">1 Заголовок 1</a>
                                <div class="item__text__top">Текст 1</div>
                                <span class="time_iteration_icon_light">2024-10-01</span>
                            </li>
                            <li class="item item__border">
                                <a href="/link2">2 Заголовок 2</a>
                                <div class="item__text__top">Текст 2</div>
                                <span class="time_iteration_icon_light">2024-10-02</span>
                            </li>
                        </div>
                    </body>
                </html>
                """;

    FifthParser parser = new FifthParser();
    Document document = Jsoup.parse(html);
    List<Article> articles = parser.parseAllSite(html, document);

    assertEquals(2, articles.size());

    Article firstArticle = articles.get(0);
    String currentValue = """
            Заголовок 1
            2024-10-01
            https://www.ixbt.com/news//link1
            Текст 1""";
    assertEquals(currentValue, firstArticle.toString());
    assertEquals("Заголовок 1", firstArticle.getTitle());
    assertEquals("https://www.ixbt.com/news//link1", firstArticle.getLink());
    assertEquals("Текст 1", firstArticle.getText());
    assertEquals("2024-10-01", firstArticle.getDate());

    Article secondArticle = articles.get(1);
    currentValue = """
            Заголовок 2
            2024-10-02
            https://www.ixbt.com/news//link2
            Текст 2""";
    assertEquals(currentValue, secondArticle.toString());
    assertEquals("Заголовок 2", secondArticle.getTitle());
    assertEquals("https://www.ixbt.com/news//link2", secondArticle.getLink());
    assertEquals("Текст 2", secondArticle.getText());
    assertEquals("2024-10-02", secondArticle.getDate());
  }
}