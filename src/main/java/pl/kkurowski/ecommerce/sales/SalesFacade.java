package pl.kkurowski.ecommerce.sales;

import pl.kkurowski.ecommerce.sales.cart.Cart;
import pl.kkurowski.ecommerce.sales.cart.HashMapCartStorage;

public class SalesFacade {
    private HashMapCartStorage cartStorage;

    public SalesFacade(HashMapCartStorage cartStorage) {
        this.cartStorage = cartStorage;
    }

    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }

    public void addProduct(String customerId, String productId) {
        Cart cart = getCartForCustomer(customerId);
        cart.add(productId);

    }

    private Cart getCartForCustomer(String customerId) {
        return cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        return new ReservationDetails();
    }
}
