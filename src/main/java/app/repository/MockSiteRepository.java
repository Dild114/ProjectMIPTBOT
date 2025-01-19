package app.repository;

import app.dto.Site;
import java.util.HashMap;
import java.util.Map;

public class MockSiteRepository implements SiteRepository {
    private final Map<String, Site> sites = new HashMap<>();

    @Override
    public void save(Site site) {
        sites.put(site.getUrl(), site);
    }

    @Override
    public Site findByUrl(String url) {
        return sites.get(url);
    }
}
