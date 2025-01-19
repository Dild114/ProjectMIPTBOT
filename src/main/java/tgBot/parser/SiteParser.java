package tgBot.parser;

import org.jsoup.nodes.Document;
import java.util.List;

/**
 * Интерфейс работы с парсерами.
 */
public interface SiteParser {

  /**
   * Метод, использующийся для получения информации с сайта.
   * <p>
   * Ключевые элементы: заголовок, URL, краткий пересказ, дата публикации.
   * @return массив, в котором хранятся ключевые элементы
   */
  List<Article> parseAllSite();

  /**
   * Перегруженный метод парсера сайта, необходимый для тестирования.
   * @param url - сайт с которого считываем данные.
   * @return массив, в котором хранятся ключевые элементы
   */
  List<Article> parseAllSite(String url, Document document);
}
