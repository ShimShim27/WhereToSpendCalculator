package wts.calc.exception;

public class CalculationException extends IllegalArgumentException {
    public String NO_WALLETS = "No wallets provided";
    public static String NO_WALLET_NAME = "No name provided";

    public CalculationException(final String message) {
        super(message);
    }
}
