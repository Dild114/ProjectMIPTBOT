package tgBot.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class FirstParserTest {

  @Test
  public void testParseAllSiteWithCustomHtml() {
    String html = """
                <html>
                    <body>
                        <article>
                            <h2 class="tm-title">
                                <a class="tm-title__link" href="/link1">Title 1</a>
                            </h2>
                            <div class="tm-article-body">Text 1. Читать далее</div>
                            <div class="tm-article-snippet__meta-container">
                                <span>
                                    <a class="tm-article-datetime-published">2024-10-01</a>
                                </span>
                            </div>
                        </article>
                        <article>
                            <h2 class="tm-title">
                                <a class="tm-title__link" href="/link2">Title 2</a>
                            </h2>
                            <div class="tm-article-body">Text 2. Читать далее</div>
                            <div class="tm-article-snippet__meta-container">
                                <span>
                                    <a class="tm-article-datetime-published">2024-10-02</a>
                                </span>
                            </div>
                        </article>
                    </body>
                </html>
                """;

    FirstParser parser = new FirstParser();
    Document document = Jsoup.parse(html);
    List<Article> articles = parser.parseAllSite(html, document);

    assertEquals(2, articles.size());


    Article firstArticle = articles.get(0);
    String currentValue = """
        Title 1
        2024-10-01
        https://habr.com/link1
        Text 1.""";
    assertEquals(currentValue, firstArticle.toString());
    assertEquals("Title 1", firstArticle.getTitle());
    assertEquals("https://habr.com/link1", firstArticle.getLink());
    assertEquals("Text 1.", firstArticle.getText());
    assertEquals("2024-10-01", firstArticle.getDate());

    Article secondArticle = articles.get(1);
    currentValue = """
        Title 2
        2024-10-02
        https://habr.com/link2
        Text 2.""";
    assertEquals(currentValue, secondArticle.toString());
    assertEquals("Title 2", secondArticle.getTitle());
    assertEquals("https://habr.com/link2", secondArticle.getLink());
    assertEquals("Text 2.", secondArticle.getText());
    assertEquals("2024-10-02", secondArticle.getDate());
  }
}