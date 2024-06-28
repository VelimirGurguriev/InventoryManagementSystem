package ApplicationConsoleCommerce;

public class PayPalPayment implements  PaymentMethod {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean isValid() {
        boolean isValidEmail = email.contains("@");
        boolean isValidPasswordLength = password.length() > 6;
        boolean hasDigitInPassword = password.matches(".*\\d.*");

        return isValidEmail && isValidPasswordLength && hasDigitInPassword;
    }

    @Override
    public String getPaymentType() {
        return "PayPal";
    }
}
