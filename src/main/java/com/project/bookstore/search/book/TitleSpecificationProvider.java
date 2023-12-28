package com.project.bookstore.search.book;

import com.project.bookstore.model.Book;
import com.project.bookstore.search.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    private static final String SPECIFICATION_KEY = "title";

    @Override
    public String getKey() {
        return SPECIFICATION_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                root.get("title").in(Arrays.stream(params).toArray());
    }
}
