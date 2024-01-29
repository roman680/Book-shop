package com.project.bookstore.repository;

import com.project.bookstore.model.ShoppingCart;
import com.project.bookstore.model.User;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class ShoppingCartRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    @DisplayName("Test findByUserId")
    public void testFindByUserId() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setDeleted(false);

        entityManager.persist(user);
        entityManager.flush();

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setDeleted(false);

        entityManager.persist(shoppingCart);
        entityManager.flush();

        Optional<ShoppingCart> foundShoppingCart = shoppingCartRepository
                .findByUserId(user.getId());

        Assertions.assertTrue(foundShoppingCart.isPresent());
        Assertions.assertEquals(user.getId(), foundShoppingCart.get().getUser().getId());
    }
}
