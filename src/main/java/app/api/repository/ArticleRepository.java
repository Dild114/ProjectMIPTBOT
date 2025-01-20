package app.api.repository;

import ai.onnxruntime.OrtException;
import app.api.entity.Article;
import app.api.entity.ArticleId;
import app.api.entity.Category;
import app.api.entity.UserId;

import java.io.IOException;
import java.util.Map;

public interface ArticleRepository {
  ArticleId generateId();

  Map<Article, Category> getArticles(UserId userId) throws IOException, OrtException;
}
