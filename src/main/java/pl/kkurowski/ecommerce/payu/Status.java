package pl.kkurowski.ecommerce.payu;

public class Status {
    String statusCode;

    public String getStatusCode() {
        return statusCode;
    }

    public Status setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }
}