package app.api.repository;

import app.api.entity.Article;
import app.api.entity.ArticleId;
import app.api.entity.Category;
import app.api.entity.UserId;

import java.util.Map;

public interface ArticleRepository {
  ArticleId generateId();

  Map<Article, Category> getArticles(UserId userId);
}
