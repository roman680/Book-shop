package com.project.bookstore.search.book;

import com.project.bookstore.model.Book;
import com.project.bookstore.search.SpecificationProvider;
import com.project.bookstore.search.SpecificationProviderManager;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    public BookSpecificationProviderManager(
            List<SpecificationProvider<Book>> specificationProviders) {
        this.bookSpecificationProviders = specificationProviders;
    }

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(bookSpecificationProvider -> bookSpecificationProvider.getKey().equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Can't find correct specification for key: " + key));
    }
}
