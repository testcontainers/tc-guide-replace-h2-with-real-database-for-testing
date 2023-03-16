package com.testcontainers.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.test.database.replace=none",
    "spring.datasource.url=jdbc:tc:postgresql:15.2-alpine:///db?TC_INITSCRIPT=sql/init-db.sql"
})
class ProductRepositoryWithJdbcUrlTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Sql("classpath:/sql/seed-data.sql")
    void shouldGetAllProducts() {
        List<Product> products = productRepository.findAll();
        assertEquals(2, products.size());
    }
}
