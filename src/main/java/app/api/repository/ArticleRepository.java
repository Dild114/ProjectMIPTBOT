package app.api.repository;

import app.api.entity.Article;
import app.api.entity.ArticleId;
import app.api.entity.Category;
import app.api.entity.UserId;

import java.util.HashMap;
import java.util.List;

public interface ArticleRepository {
  ArticleId generateId();

  HashMap<Article, Category> getArticles(UserId userId);
}
