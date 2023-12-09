package com.project.bookstore.repository;

import com.project.bookstore.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b where b.isDeleted = false")
    List<Book> findByIsDeletedFalse();
}
