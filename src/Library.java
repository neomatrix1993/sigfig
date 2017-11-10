import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Library helper functions.
 *
 * @author swapnil.gupta
 */
public class Library {

    /**
     * Library helper function.
     * User id is not used here, but consider it sends different trade list,
     * for different user ids.
     *
     * @return List of Trade.
     */
    public static List<Trade> getTradeTransactionsForUser(int userId) {
        List<Trade> trades = new ArrayList<>();
        try {
            trades.add(new Trade("2017-10-14,BUY,GOOG"));
            trades.add(new Trade("2017-10-13,BUY,GOOG"));
            trades.add(new Trade("2017-10-12,BUY,AAPL"));
            trades.add(new Trade("2017-10-11,SELL,AAPL"));
            trades.add(new Trade("2017-10-10,BUY,YOLO"));
            trades.add(new Trade("2017-10-09,BUY,AAPL"));
            trades.add(new Trade("2017-10-08,BUY,AAPL"));
            trades.add(new Trade("2017-10-07,BUY,AAPL"));
            trades.add(new Trade("2017-10-06,SELL,GOOG"));
            trades.add(new Trade("2017-10-05,SELL,GOOG"));
            trades.add(new Trade("2017-10-04,SELL,GOOG"));
            trades.add(new Trade("2017-10-03,SELL,GOOG"));
            trades.add(new Trade("2017-09-02,SELL,GOOG"));
            trades.add(new Trade("2017-09-01,SELL,GOOG"));
        } catch (InvalidTradeException e) {
            System.out.println(e.getMessage());
        }


        return trades;
    }

    /**
     * Library helper function.
     * Consider this will return 1...n number of user ids.
     *
     * @param userId User id.
     * @return List of user ids, in integer.
     */
    public static List<Integer> getFriendsListForUser(int userId) {
        return Arrays.asList(1);
    }
}
