package app.api;

import app.api.controller.ArticleController;
import app.api.controller.CategoryController;
import app.api.controller.SiteController;
import app.api.controller.UserController;
import app.api.repository.ArticleRepository;
import app.api.repository.CategoryRepository;
import app.api.repository.SiteRepository;
import app.api.repository.UserRepository;
import app.api.repository.dbStub; 
import app.api.service.ArticleService;
import app.api.service.CategoryService;
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
        final ArticleRepository articleRepository = db;
        final UserRepository userRepository = db;
        final SiteRepository siteRepository = db;
        final CategoryRepository categoryRepository = db;
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
