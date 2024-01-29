package com.project.bookstore.repository;

import static org.junit.Assert.assertEquals;

import com.project.bookstore.model.Category;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void test_Repository_Ok() {
        Category category1 = new Category();
        category1.setDescription("description1");
        category1.setName("name1");
        Category category2 = new Category();
        category2.setName("name2");
        category1.setDescription("description2");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        List<Category> actualList = categoryRepository.findAll();

        assertEquals(2, actualList.size());
    }
}
