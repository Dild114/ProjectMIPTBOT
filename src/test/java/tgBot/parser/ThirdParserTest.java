package tgBot.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThirdParserTest {

  @Test
  public void testParseAllSiteWithCustomHtml() {
    String html = """
                <html>
                    <body>
                        <div class="bd-block-row">
                            <article>
                                <header>
                                    <h3 class="entry-title">
                                        <a href="https://xakep.ru/link1">Title 1</a>
                                    </h3>
                                </header>
                                <p class="block-exb">Text 1.</p>
                            </article>
                            <article>
                                <header>
                                    <h3 class="entry-title">
                                        <a href="https://xakep.ru/link2">Title 2</a>
                                    </h3>
                                </header>
                                <p class="block-exb">Text 2.</p>
                            </article>
                        </div>
                    </body>
                </html>
                """;

    ThirdParser parser = new ThirdParser();
    Document document = Jsoup.parse(html);
    List<ArticleParser> articles = parser.parseAllSite("https://xakep.ru/", document);

    assertEquals(2, articles.size());

    ArticleParser firstArticle = articles.get(0);
    String currentValue = """
            Title 1
            None
            https://xakep.ru/link1
            Text 1.""";
    assertEquals(currentValue, firstArticle.toString());
    assertEquals("Title 1", firstArticle.getTitle());
    assertEquals("https://xakep.ru/link1", firstArticle.getLink());
    assertEquals("Text 1.", firstArticle.getText());
    assertEquals("None", firstArticle.getDate());

    ArticleParser secondArticle = articles.get(1);
    currentValue = """
            Title 2
            None
            https://xakep.ru/link2
            Text 2.""";
    assertEquals(currentValue, secondArticle.toString());
    assertEquals("Title 2", secondArticle.getTitle());
    assertEquals("https://xakep.ru/link2", secondArticle.getLink());
    assertEquals("Text 2.", secondArticle.getText());
    assertEquals("None", secondArticle.getDate());
  }
}