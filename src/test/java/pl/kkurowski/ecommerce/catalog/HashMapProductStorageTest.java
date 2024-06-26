package pl.kkurowski.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class HashMapProductStorageTest {

    public static final String EXAMPLE_PRODUCT_NAME = "example product";

    @Test
    void itStoresAndLoadProduct() {
        var product = thereIsExampleProduct();
        var productStorage = thereIsProductStorage();

        productStorage.add(product);

        List<Product> products = productStorage.allProducts();

        assertThat(products)
                .hasSize(1)
                .extracting(Product::getName)
                .contains(EXAMPLE_PRODUCT_NAME);
    }

    @Test
    void itStoresAndLoadById() {
        var product = thereIsExampleProduct();
        var productStorage = thereIsProductStorage();

        productStorage.add(product);
        var loaded = productStorage.getProductBy(product.getId());

        assertThat(loaded.getId()).isEqualTo(product.getId());
    }

    private HashMapProductStorage thereIsProductStorage() {
        return new HashMapProductStorage();
    }

    private Product thereIsExampleProduct() {
        var product = new Product(UUID.randomUUID(), EXAMPLE_PRODUCT_NAME, "nice one");
        product.changePrice(BigDecimal.valueOf(10.10));

        return product;
    }
}
