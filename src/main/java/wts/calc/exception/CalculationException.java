package wts.calc.exception;

public class CalculationException extends RuntimeException {
    public static String NO_WALLETS = "No wallets provided";
    public static String NO_WALLET_NAME = "No name provided";
    public static String DUPLICATE_WALLET_NAME = "Duplicate wallet namee";

    public CalculationException(final String message) {
        super(message);
    }
}
