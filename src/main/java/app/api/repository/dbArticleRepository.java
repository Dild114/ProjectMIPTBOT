package app.api.repository;

import app.api.entity.Article;
import app.api.entity.ArticleId;
import app.api.entity.UserId;

import java.util.List;

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
  public List<Article> getArticles(UserId userId) {
    return db.getArticles(userId);
  }


}
