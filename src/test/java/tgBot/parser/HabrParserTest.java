package tgBot.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class HabrParserTest {
  @Test
  void testParseAllSiteWithCustomHtml() {
    SiteParser parser = new HabrParser();

    String html = """
                <html>
                    <body>
                        <article>
                            <h2 class="tm-title">
                                <a class="tm-title__link" href="/link1">Title 1</a>
                            </h2>
                            <div class="tm-article-body">Text 1</div>
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
                            <div class="tm-article-body">Text 2</div>
                            <div class="tm-article-snippet__meta-container">
                                <span>
                                    <a class="tm-article-datetime-published">2024-10-02</a>
                                </span>
                            </div>
                        </article>
                    </body>
                </html>
                """;
    List<Article> articles = parser.parseAllSite(html);

    assertEquals(2, articles.size());

    Article firstArticle = articles.get(0);
    assertEquals("https://habr.com/link1", firstArticle.getLink());
    assertEquals("Title 1", firstArticle.getTitle());
    assertEquals("Text 1", firstArticle.getText());
    assertEquals("2024-10-01", firstArticle.getDate());

    Article secondArticle = articles.get(1);
    assertEquals("https://habr.com/link2", secondArticle.getLink());
    assertEquals("Title 2", secondArticle.getTitle());
    assertEquals("Text 2", secondArticle.getText());
    assertEquals("2024-10-02", secondArticle.getDate());
  }
}
