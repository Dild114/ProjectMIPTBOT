package tgBot.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ParserManager {
  private static final SiteParser HABRPARSER = new FirstParser();
  private static final SiteParser COMMUNITYPARSER = new SecondParser();
  private static final SiteParser XAKERPARSER = new ThirdParser();
  private static final SiteParser D3NEWSPARSER = new FourthParser();
  private static final SiteParser IXBTPARSER = new FifthParser();
  private static final Logger log = LogManager.getLogger(ParserManager.class);

  public static List<ArticleParser> Manager(String url) {
    List<ArticleParser> data = new ArrayList<>();
    switch (url) {
      case "https://habr.com/ru/news/":
        data = HABRPARSER.parseAllSite();
        break;
      case "https://timeweb.com/ru/community/":
        data = COMMUNITYPARSER.parseAllSite();
        break;
      case "https://xakep.ru/":
        data = XAKERPARSER.parseAllSite();
        break;
      case "https://3dnews.ru/":
        data = D3NEWSPARSER.parseAllSite();
        break;
      case "https://www.ixbt.com/news/":
        data = IXBTPARSER.parseAllSite();
      default:
        log.error("Невозможно найти новости с данного сайта: {}", url);
      }
    return data;
  }
}
