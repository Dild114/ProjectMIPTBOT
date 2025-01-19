package app.api.repository;

import app.api.entity.Article;
import app.api.entity.ArticleId;
import app.api.entity.UserId;
import com.beust.ah.A;

import java.util.List;

public interface ArticleRepository {
  ArticleId generateId();

  List<Article> getArticles(UserId userId);
}
