package com.project.bookstore.repository;

import com.project.bookstore.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query("SELECT b FROM Book b JOIN FETCH b.categories c WHERE c.id = :categoryId")
    List<Book> findAllByCategoryId(Long categoryId);

    @Query("SELECT b FROM Book b JOIN FETCH b.categories WHERE b.id = :id")
    Optional<Book> findById(Long id);
}
