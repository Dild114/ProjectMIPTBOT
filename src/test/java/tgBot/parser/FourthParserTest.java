package tgBot.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FourthParserTest {

  @Test
  public void testParseAllSiteWithCustomHtml() {
    String html = """
                <html>
                    <body>
                        <div id="news" class="content-block">
                            <div class="content-block-data white">
                                <table class="nomargins">
                                    <tr>
                                        <td>
                                            <a href="link1">Title 1</a>
                                        </td>
                                    </tr>
                                </table>
                                <div class="teaser">Text 1</div>
                            </div>
                            <div class="content-block-data white">
                                <table class="nomargins">
                                    <tr>
                                        <td>
                                            <a href="link2">Title 2</a>
                                        </td>
                                    </tr>
                                </table>
                                <div class="teaser">Text 2</div>
                            </div>
                        </div>
                    </body>
                </html>
                """;

    FourthParser parser = new FourthParser();
    Document document = Jsoup.parse(html);
    List<Article> articles = parser.parseAllSite("https://3dnews.ru/", document);

    assertEquals(2, articles.size());

    Article firstArticle = articles.get(0);
    String currentValue = """
            Title 1
            None
            https://3dnews.ru/link1
            Text 1""";
    assertEquals(currentValue, firstArticle.toString());
    assertEquals("Title 1", firstArticle.getTitle());
    assertEquals("https://3dnews.ru/link1", firstArticle.getLink());
    assertEquals("Text 1", firstArticle.getText());
    assertEquals("None", firstArticle.getDate());

    Article secondArticle = articles.get(1);
    currentValue = """
            Title 2
            None
            https://3dnews.ru/link2
            Text 2""";
    assertEquals(currentValue, secondArticle.toString());
    assertEquals("Title 2", secondArticle.getTitle());
    assertEquals("https://3dnews.ru/link2", secondArticle.getLink());
    assertEquals("Text 2", secondArticle.getText());
    assertEquals("None", secondArticle.getDate());
  }
}