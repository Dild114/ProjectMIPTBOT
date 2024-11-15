package src.main.java.tgBot.parser;

/**
 * Структура данных для хранения информации о статье,
 * включая ссылку, заголовок, текст и дату публикации.
 */
public class Article {
  public String link;
  public String title;
  public String date;
  public String text;

  public Article(String link, String title, String text, String date) {
    this.link = link;
    this.title = title;
    this.date = date;
    this.text = text;
  }
}
