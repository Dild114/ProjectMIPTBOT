package app.api.repository;

import app.api.entity.Article;
import app.api.entity.ArticleId;
import app.api.entity.Category;
import app.api.entity.UserId;

import java.util.Map;

public class dbArticleRepository implements ArticleRepository {
  dbRepository db;
  public dbArticleRepository(dbRepository dbRepository) {
    this.db = dbRepository;
  }

  @Override
  public ArticleId generateId() {
    return db.generateIdArticle();
  }

  @Override
  public Map<Article, Category> getArticles(UserId userId) {
    return db.getArticles(userId);
  }


}
