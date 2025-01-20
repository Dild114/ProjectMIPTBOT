package tgBot.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class SecondParserTest {
  @Test
  void testParseAllSiteWithCustomHtml() {
    String html = """
                <html>
                    <body>
                        <div class="js-pagination-element cm-article-main pt-16 pb-24 mt-32:md pos-rel zi-5">
                            <h2 class="mb-12">
                                <a class="txt-secondary-9" href="/link1">Title 1</a>
                            </h2>
                            <div class="font-style-1 mb-24 txt-secondary-9">Text 1</div>
                            <time>Сегодня в 16:22</time>
                            </div>
                        </div>
                        <div class="js-pagination-element cm-article-main pt-16 pb-24 mt-32:md pos-rel zi-5">
                            <h2 class="mb-12">
                                <a class="txt-secondary-9" href="/link2">Title 2</a>
                            </h2>
                            <div class="font-style-1 mb-24 txt-secondary-9">Text 2</div>
                            <time>Сегодня в 16:00</time>
                            </div>
                        </div>
                    </body>
                </html>
                """;
    SiteParser parser = new SecondParser();
    Document document = Jsoup.parse(html);
    List<ArticleParser> articles = parser.parseAllSite(html, document);

    assertEquals(2, articles.size());

    ArticleParser firstArticle = articles.get(0);
    String currentValue = """
        Title 1
        Сегодня в 16:22
        https://timeweb.com/link1
        Text 1""";
    assertEquals(currentValue, firstArticle.toString());
    assertEquals("https://timeweb.com/link1", firstArticle.getLink());
    assertEquals("Title 1", firstArticle.getTitle());
    assertEquals("Text 1", firstArticle.getText());
    assertEquals("Сегодня в 16:22", firstArticle.getDate());

    ArticleParser secondArticle = articles.get(1);
    currentValue = """
        Title 2
        Сегодня в 16:00
        https://timeweb.com/link2
        Text 2""";
    assertEquals(currentValue, secondArticle.toString());
    assertEquals("https://timeweb.com/link2", secondArticle.getLink());
    assertEquals("Title 2", secondArticle.getTitle());
    assertEquals("Text 2", secondArticle.getText());
    assertEquals("Сегодня в 16:00", secondArticle.getDate());
  }
}
