package ApplicationConsoleCommerce;

public interface PaymentMethod {
    boolean isValid();
    String getPaymentType();
}
