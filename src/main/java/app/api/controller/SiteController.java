package app.api.controller;

import app.api.controller.request.SiteSetRequest;
import app.api.entity.Site;
import app.api.entity.SiteId;
import app.api.entity.UserId;
import app.api.service.SiteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.HashMap;
import java.util.List;

public class SiteController implements Controller {
  private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);
  private final Service service;
  private final SiteService siteService;
  private final ObjectMapper objectMapper;

  public SiteController(Service service, SiteService siteService , ObjectMapper objectMapper) {
    this.service = service;
    this.siteService = siteService;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    addSite();
    getSite();
    deleteSite();
    mySites();
  }

  private void getSite() {
    service.get("all/sites",
        (Request request, Response response) -> {
        response.type("application/json");
        String site1Url = Sites.SITE1.getUrl();
        String site2Url = Sites.SITE2.getUrl();
        String site3Url = Sites.SITE3.getUrl();
        HashMap<String, Integer> hashSite = new HashMap<>();
        hashSite.put(site1Url, 1);
        hashSite.put(site2Url, 2);
        hashSite.put(site3Url, 3);
        return objectMapper.writeValueAsString("Выберите сайт:\n" + hashSite);
        });
  }

  private void mySites() {
    service.get("/sites",
      (Request request, Response response) -> {
      response.type("application/json");
        String body = request.body();
        SiteSetRequest sites = objectMapper.readValue(body, SiteSetRequest.class);
      try {
        List<Site> siteList = siteService.getSites(new UserId(sites.userId()));
        response.status(200);
        return objectMapper.writeValueAsString(siteList);
      } catch (Exception e) {
        response.status(400);
        if (LOG.isErrorEnabled()) {
          LOG.error("error find sites userId:{}", sites.userId());
        }
        return objectMapper.writeValueAsString("Error");
      }
      });
  }

  private void addSite() {
    service.post("/site/:id",
      (Request request, Response response) -> {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        SiteId siteId = new SiteId(id);
        try {
          String body = request.body();
          SiteSetRequest siteSetRequest = objectMapper.readValue(body, SiteSetRequest.class);

          siteService.addSite(siteId, new UserId(siteSetRequest.userId()));
          response.status(201);
          return objectMapper.writeValueAsString("OK id: " + siteId);
        } catch (Exception e) {
          response.status(400);
          if (LOG.isErrorEnabled()) {
            LOG.error("error add site id:{}", id);
          }
          return objectMapper.writeValueAsString("Add Error");
        }
      });
  }
  private void deleteSite() {
    service.delete("/site/:id",
        (Request request, Response response) -> {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        try {
          String body = request.body();
          SiteSetRequest siteSetRequest = objectMapper.readValue(body, SiteSetRequest.class);

          siteService.deleteSite(new SiteId(id), new UserId(siteSetRequest.userId()));
          response.status(204);
          return objectMapper.writeValueAsString(siteService.getSites(new UserId(siteSetRequest.userId())));
        } catch (Exception e) {
          response.status(400);
          if (LOG.isErrorEnabled()) {
            LOG.error("error delete site id:{}", id);
          }
          return objectMapper.writeValueAsString("Delete Error");
        }
        });
  }
}
