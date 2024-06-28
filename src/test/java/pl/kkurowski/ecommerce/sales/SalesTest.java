package pl.kkurowski.ecommerce.sales;

import org.junit.jupiter.api.Test;
import pl.kkurowski.ecommerce.sales.cart.HashMapCartStorage;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class SalesTest {

    @Test
    void itShowsCurrentOffer() {
        String customerId = thereIsCustomer("Kuba");
        SalesFacade sales = thereIsSales();

        Offer offer = sales.getCurrentOffer(customerId);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.ZERO);
        assertThat(offer.getItemsCount()).isEqualTo(0);
    }

    @Test
    void itAddsProductToCart() {
        String productId = thereIsExampleProduct("X", BigDecimal.valueOf(10));
        String customerId = thereIsCustomer("Kuba");
        SalesFacade sales = thereIsSales();

        sales.addProduct(customerId, productId);

        Offer offer = sales.getCurrentOffer(customerId);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(offer.getItemsCount()).isEqualTo(1);
    }

    private String thereIsExampleProduct(String name, BigDecimal price) {
        return name;
    }

    @Test
    void itConfirmPayment() {

    }

    private SalesFacade thereIsSales() {
        return new SalesFacade(new HashMapCartStorage());
    }

    private String thereIsCustomer(String name) {
        return name;
    }
}