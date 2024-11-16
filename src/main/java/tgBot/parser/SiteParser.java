package tgBot.parser;

import java.util.List;

/**
 * Интерфейс работы с парсерами.
 */
public interface SiteParser {

  /**
   * Полностью парсит сайт (страницу).
   * <p>
   * Ключевые элементы: заголовок, URL, краткий пересказ, дата публикации.
   * @return массив, в котором хранятся ключевые элементы
   */
  List<Article> parseAllSite();
}
