package tgBot.parser;

import java.util.List;

public class SecondParser implements SiteParser{
  @Override
  public List<Article> parseAllSite() {
    return List.of();
  }

  @Override
  public List<Article> parseAllSite(String url) {
    return List.of();
  }
}
