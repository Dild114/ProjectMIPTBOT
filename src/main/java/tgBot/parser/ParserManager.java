package tgBot.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserManager {
  private static final SiteParser HABRPARSER = new FirstParser();
  private static final SiteParser COMMUNITYPARSER = new SecondParser();
  private static final SiteParser XAKERPARSER = new ThirdParser();

  public static List<Article> Manager(String url) {
    List<Article> data = new ArrayList<>();
    switch (url) {
      case ("https://habr.com/ru/news/"):
        data = HABRPARSER.parseAllSite();
        break;
      case ("https://timeweb.com/ru/community/"):
        data = COMMUNITYPARSER.parseAllSite();
        break;
      case ("https://xakep.ru/"):
        data = XAKERPARSER.parseAllSite();
      }
    return data;
  }
}
