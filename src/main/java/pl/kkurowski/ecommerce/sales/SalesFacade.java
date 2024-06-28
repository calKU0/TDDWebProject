package pl.kkurowski.ecommerce.sales;

import pl.kkurowski.ecommerce.sales.cart.Cart;
import pl.kkurowski.ecommerce.sales.cart.HashMapCartStorage;
import pl.kkurowski.ecommerce.sales.offering.Offer;
import pl.kkurowski.ecommerce.sales.offering.OfferCalculator;
import pl.kkurowski.ecommerce.sales.payment.PaymentDetails;
import pl.kkurowski.ecommerce.sales.payment.PaymentGateway;
import pl.kkurowski.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.kkurowski.ecommerce.sales.reservation.AcceptOfferRequest;
import pl.kkurowski.ecommerce.sales.reservation.Reservation;
import pl.kkurowski.ecommerce.sales.reservation.ReservationDetails;
import pl.kkurowski.ecommerce.sales.reservation.ReservationRepository;

import java.util.UUID;

public class SalesFacade {
    private HashMapCartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(HashMapCartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationRepository reservationRespository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRespository;
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
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getTotal())
        );

        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails);

        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId, paymentDetails.getPaymentUrl());
    }
}
