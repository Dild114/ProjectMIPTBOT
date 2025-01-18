package app.api;

import app.api.controller.ArticleController;
import app.api.controller.SiteController;
import app.api.controller.UserController;
import app.api.repository.*;
import app.api.service.ArticleService;
import app.api.service.SiteService;
import app.api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Service;

import java.util.List;

public class Api {
  public static void main(String[] args) {
    Service service = Service.ignite();
    ObjectMapper objectMapper = new ObjectMapper();
    dbStub db = new dbStub();
    final ArticleRepository articleRepository = new dbArticleRepository(db);
    final UserRepository userRepository = new dbUserRepository(db);
    final SiteRepository siteRepository = new dbSiteRepository(db);
    final ArticleService articleService = new ArticleService(articleRepository);
    final UserService userService = new UserService(userRepository);
    final SiteService siteService = new SiteService(siteRepository);

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
            )
        )
    );
    application.start();
  }
}
