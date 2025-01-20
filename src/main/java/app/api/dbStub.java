package app.api;

import ai.onnxruntime.OrtException;
import app.api.entity.Article;
import app.api.entity.ArticleId;
import app.api.entity.Category;
import app.api.entity.CategoryId;
import app.api.entity.Site;
import app.api.entity.SiteId;
import app.api.entity.User;
import app.api.entity.UserId;
import app.api.repository.dbRepository;
import app.ml.FindTwoMostProbableCategories;
import tgBot.parser.ArticleParser;
import tgBot.parser.ParserManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dbStub implements dbRepository {
  // private List<Article> articles = new ArrayList<>();
  // вообще нужно возвращать категорию если ее вероятность > 70%
  private FindTwoMostProbableCategories findTwoMostProbableCategories;
  private List<Category> categories = new ArrayList<>();
  private final List<Site> sites = new ArrayList<>();
  private final List<User> users = new ArrayList<>();
  private int articleIdCounter = 1;
  private int categoryIdCounter = 1;
  private int siteIdCounter = 1;
  private int userIdCounter = 1;

  public dbStub(FindTwoMostProbableCategories findCategoryRepository) {
    this.findTwoMostProbableCategories = findCategoryRepository;
  }

  @Override
  public ArticleId generateIdArticle() {
    return new ArticleId(articleIdCounter++);
  }

  @Override
  // наверное тут буду делать запрос на парсер и мл а потом мы добавляем артиклы
  // по идеи бд не должно хранить дубликаты статей, оставлю это на части бд
  public HashMap<Article, Category> getArticles(UserId userId) throws IOException, OrtException {
    HashMap<Article, Category> answerArticles = new HashMap<>();
    List<String> categoriesUser = new ArrayList<>();
    List<Category> categoriesUserFull = new ArrayList<>();
    for (Category category : categories) {
      if (category.userId().id() == userId.id()) {
        categoriesUser.add(category.name());
        categoriesUserFull.add(category);
      }
    }
    float max = -1;
    Category maxCategory = new Category(new CategoryId(-1), null, null);
    for (Site site : sites) {
      if (site.userId().id() == userId.id()) {
        List<ArticleParser> articleList = ParserManager.Manager(site.url().getUrl());
        for (int i = articleList.size() - 1; i >= articleList.size() - 2; i--) {
          Map<String, Float> categoryMl = findTwoMostProbableCategories.findTwoMostProbableCategories(articleList.get(i).getText(), categoriesUser.toArray(new String[0]));
          for (Category category : categoriesUserFull) {
            if (categoryMl.containsKey(category.name()) && categoryMl.get(category.name()) > max) {
              max = categoryMl.get(category.name());
              maxCategory = category;
            }
          }
          ArticleParser articleParser = articleList.get(i);
          Article newArticle = new Article(articleParser.getTitle(), generateIdArticle(), articleParser.getLink(), maxCategory.id());
          if (max >= 0.088) {
            answerArticles.put(newArticle, maxCategory);
          }
          max = -1;
          maxCategory = new Category(new CategoryId(-1), null, null);
        }
      }
    }
    return answerArticles;
  }

  @Override
  public CategoryId generateIdCategory() {
    return new CategoryId(categoryIdCounter++);
  }

  @Override
  public List<Category> findAllCategory(UserId userId) {
    List<Category> answerCategory = new ArrayList<>();
    for (Category category : categories) {
      if (category.userId().equals(userId)) {
        answerCategory.add(category);
      }
    }
    return answerCategory;
  }

  @Override
  public Category findCategoryById(CategoryId id) {
    return categories.stream()
        .filter(category -> category.id().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public boolean addCategory(Category category) {
    if (!categories.contains(category)) {
      return categories.add(category);
    }
    return true;
  }

  @Override
  public boolean deleteCategory(CategoryId id, UserId userId) {
    for (Category category : categories) {
      if (category.userId().id() == userId.id() && category.id().id() == id.id()) {
        categories.remove(category);
        return true;
      }
    }
    return false;
  }

  @Override
  public SiteId generateIdSite() {
    return new SiteId(siteIdCounter++);
  }

  @Override
  public void deleteSiteById(SiteId id, UserId userId) {
    List<Site> deleteSites = new ArrayList<>();
    for (Site site : sites) {
      if (site.userId().id() == userId.id() && site.id().siteId() == id.siteId()) {
        deleteSites.add(site);
      }
    }
    for (Site site : deleteSites) {
      sites.remove(site);
    }
  }

  @Override
  public void addSite(Site site) {
    if (!sites.contains(site)) {
      sites.add(site);
    }
  }

  @Override
  public List<Site> findAllSite(UserId userId) {
    List<Site> answerSites = new ArrayList<>();
    for (Site site : sites) {
      if (site.userId().equals(userId)) {
        answerSites.add(site);
      }
    }
    return answerSites;
  }

  @Override
  public Site findSiteById(SiteId id) {
    return sites.stream()
        .filter(site -> site.id().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public UserId generateUserId() {
    return new UserId(userIdCounter++);
  }

  @Override
  public boolean createUser (User user) {
    return users.add(user);
  }

  @Override
  public boolean deleteUser (UserId userId) {
    for (Category category : categories) {
      if (category.userId().equals(userId)) {
        categories.remove(category);
      }
    }

    for (Site site : sites) {
      if (site.userId().equals(userId)) {
        sites.remove(site);
      }
    }
    return users.removeIf(user -> user.telegramId().equals(userId));
  }
}