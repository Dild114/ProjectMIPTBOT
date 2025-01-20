package app.api;

import app.api.controller.ArticleController;
import app.api.controller.CategoryController;
import app.api.controller.SiteController;
import app.api.controller.UserController;
import app.api.repository.ArticleRepository;
import app.api.repository.CategoryRepository;
import app.api.repository.SiteRepository;
import app.api.repository.UserRepository;
import app.api.repository.dbArticleRepository;
import app.api.repository.dbCategoryRepository;
import app.api.repository.dbSiteRepository;
import app.api.repository.dbUserRepository;
import app.api.service.ArticleService;
import app.api.service.CategoryService;
import app.api.service.SiteService;
import app.api.service.UserService;
import app.api.stub.MlStubRepository;
import app.ml.FindCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Service;
import java.util.List;

public class Api {
  public static void main(String[] args) {
    Service service = Service.ignite();
    ObjectMapper objectMapper = new ObjectMapper();
    FindCategoryRepository findCategoryRepository = new MlStubRepository();
    // пока что временно это dbStub
    dbStub db = new dbStub(findCategoryRepository);
    final ArticleRepository articleRepository = new dbArticleRepository(db);
    final UserRepository userRepository = new dbUserRepository(db);
    final SiteRepository siteRepository = new dbSiteRepository(db);
    final CategoryRepository categoryRepository = new dbCategoryRepository(db);
    final ArticleService articleService = new ArticleService(articleRepository);
    final UserService userService = new UserService(userRepository);
    final SiteService siteService = new SiteService(siteRepository);
    final CategoryService categoryService = new CategoryService(categoryRepository);

    Application application = new Application(
        List.of(
            new ArticleController(
                service,
                articleService,
                objectMapper
            ),
            new UserController(
                service,
                userService,
                objectMapper
            ),
            new SiteController(
                service,
                siteService,
                objectMapper
            ),
            new CategoryController(
                service,
                categoryService,
                objectMapper
            )
        )
    );
    application.start();
  }
}
