package app.repository;

import app.dto.Category;
import java.util.HashMap;
import java.util.Map;

public class MockCategoryRepository implements CategoryRepository {
    private final Map<Integer, Category> categoriesById = new HashMap<>();
    private final Map<String, Category> categoriesByName = new HashMap<>();
    private int idCounter = 1;

    @Override
    public void save(Category category) {
        category.setId(idCounter++);
        categoriesById.put(category.getId(), category);
        categoriesByName.put(category.getName(), category);
    }

    @Override
    public Category findById(int id) {
        return categoriesById.get(id);
    }

    @Override
    public Category findByName(String name) {
        return categoriesByName.get(name);
    }
}
