package ApplicationConsoleCommerce;

public class PaymentProcessor {
    public boolean processPayment(PaymentMethod paymentMethod, double amount) {
        if (paymentMethod.isValid()) {
            System.out.println("Payment authorized for amount: "
                    + String.format("%.2f", amount) + "LV. with " + paymentMethod.getPaymentType());
            return true;
        } else {
            System.out.println("Payment validation failed for amount: "
                    + String.format("%.2f", amount) + "LV. with " + paymentMethod.getPaymentType());
        }
        return false;
    }
}
