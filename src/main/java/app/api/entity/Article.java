package app.api.entity;

import lombok.Getter;
import lombok.Setter;

public class Article {
  @Getter
  String name;
  @Getter
  ArticleId id;
  @Getter
  String url;
  @Getter
      @Setter
  CategoryId idCategory;

  public Article() {}

  public Article(String name, ArticleId id, String url, CategoryId idCategory) {
    this.name = name;
    this.id = id;
    this.url = url;
    this.idCategory = idCategory;
  }

}
