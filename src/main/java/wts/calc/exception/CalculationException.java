package wts.calc.exception;

public class CalculationException extends IllegalArgumentException {
    public String NO_WALLETS = "No wallets provided";

    public CalculationException(final String message) {
        super(message);
    }
}
