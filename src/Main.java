import java.util.*;

/**
 * Alerts Class.
 * Lets assume the user id = 1 for the sake for library functions.
 *
 * @author swapnil.gupta
 */
public class Main {

    /**
     * Application:
     * TIME COMPLEXITY: O(friends*Trades + Tickers*log(Tickers) + Tickers)
     * SPACE COMPLEXITY: O(friends + trades + tickers)
     *
     * @param args args
     * @throws Exception Exceptions
     */
    public static void main(String[] args) throws Exception {

        int userId = 1;
        List<Alerts> alerts = UserService.getAlertsByUserId(userId);
        for (Alerts alert : alerts) {
            System.out.println(alert);
        }
    }
}
