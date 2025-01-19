package app.api.controller;

public enum Sites {
  SITE1("https://xakep.ru/"),
  SITE2("https://habr.com/ru/news/"),
  SITE3("https://timeweb.com/ru/community/");

  private final String url;

  Sites(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}