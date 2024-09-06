package pl.kkurowski.ecommerce.payu;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PayUTest {

    @Test
    void itRegisterNewPayment() {
        PayU payu = thereIsPayU();
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(request);

        assertNotNull(response.getOrderId());
        assertNotNull(response.redirectUri());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
        var request = new OrderCreateRequest();
        request
                .setNotifyUrl("https://your.eshop.com/notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("Bigos")
                .setCurrencyCode("PLN")
                .setTotalAmount(15500)
                .setExtOrderId(UUID.randomUUID().toString())
                .setBuyer(new Buyer()
                        .setEmail("krzysztofkurowski.main@gmail.com")
                        .setFirstName("krzysztof")
                        .setLastName("kurowski")
                        .setLanguage("pl")
                )
                .setProducts(Arrays.asList(
                        new Product()
                                .setName("Bigos")
                                .setUnitPrice(15500)
                                .setQuantity(1)
                ));

        return request;
    }

    private PayU thereIsPayU() {
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                ));
    }
}
