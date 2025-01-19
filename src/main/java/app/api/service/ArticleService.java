package app.api.service;

import app.api.entity.Article;
import app.api.entity.ArticleId;
import app.api.entity.UserId;
import app.api.repository.ArticleRepository;
import app.api.service.exception.*;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;
  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public List<Article> getArticles(UserId userId) {
    return articleRepository.getArticles(userId);
  }
//  public List<Article> findAll() {
//    return articleRepository.findAll();
//  }
//  public Article findById(ArticleId id) throws ArticleFindException {
//    try {
//      return articleRepository.findById(id);
//    }  catch (ArticleNotFoundException e) {
//      throw new ArticleFindException("Article with id " + id + " not found", e);
//    }
//  }
//
//  public void delete(ArticleId id) throws ArticleNotFoundException {
//    try {
//      articleRepository.delete(id);
//    } catch (ArticleNotFoundException e) {
//      throw new ArticleDeleteByIdException("Article with id " + id + " not found", e);
//    }
//  }
//
//  public ArticleId create(String name, String url) {
//    ArticleId articleId = articleRepository.generateId();
//    Article article = new Article(name, articleId, url, null);
//    try {
//      articleRepository.create(article);
//    } catch (ArticleDuplicateException e) {
//      throw new ArticleCreateException("Article with id " + article.getId() + " already exists", e);
//    }
//    return articleId;
//  }


}
