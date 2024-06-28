package ApplicationConsoleCommerce;

public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolder, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public boolean isValid() {
        return cardNumber.matches("\\d{16}") && cvv.matches("\\d{3}");
    }

    @Override
    public String getPaymentType() {
        return "Credit Card";
    }
}
