package tgBot.parser;

import lombok.Getter;

/**
 * Структура данных для хранения информации о статье,
 * включая ссылку, заголовок, текст и дату публикации.
 */
@Getter
public class Article {
  private String link;
  private String title;
  private String date;
  private String text;

  public Article(String link, String title, String text, String date) {
    this.link = link;
    this.title = title;
    this.date = date;
    this.text = text;
  }
}
