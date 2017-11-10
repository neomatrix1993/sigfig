import java.time.LocalDate;
import java.util.*;

/**
 * User Service class.
 *
 * @author swapnil.gupta
 */
public class UserService {

    /**
     * Function to get stock alert based on user id.
     * 1. getTradeTransactionsForUser(userId) will retrieve list of trades,
     * which is then filtered to break when a date which doesn't belong to this week
     * is encountered and the rest trades are added to hashMap
     *
     * Let no. of friends = f
     * Let no. of trades = tr
     * Let no. of tickers = t
     * TIME COMPLEXITY: O(f x tr)
     *
     * SPACE COMPLEXITY: O(f + tr + t)
     *
     * @param userId User id
     * @return List of Alerts
     */
    public static List<Alerts> getAlertsByUserId(int userId) {
        List<Integer> friendList = Library.getFriendsListForUser(userId);
        Map<String, Integer> tickerCountMap = new HashMap<>();

        for (Integer friendId : friendList) {
            List<Trade> tradeList = Library.getTradeTransactionsForUser(friendId);
            for (Trade trade : tradeList) {
                if (Util.isDateInPastWeek(trade.getDate())) {
                    addToMap(trade, tickerCountMap);
                } else {
                    break;
                }
            }
        }

        return getSortedAlerts(tickerCountMap);
    }

    /**
     * For the purpose of testing,
     * This function takes list of trades and returns alerts,
     * with additional date param.
     *
     * @param trades List of Trades
     * @param date   Date for when to check the test
     * @return List of alerts
     */
    public static List<Alerts> getAlertsByTradeListAndDate(List<Trade> trades, LocalDate date) {
        Map<String, Integer> tickerCountMap = new HashMap<>();
        for (Trade trade : trades) {
            if (Util.isDateInPastWeek(trade.getDate(), date)) {
                addToMap(trade, tickerCountMap);
            } else {
                break;
            }
        }

        return getSortedAlerts(tickerCountMap);
    }

    /**
     * Helper function to add trades to HashMap.
     * TIME COMPLEXITY : O(1)
     * SPACE COMPLEXITY: O(tickers)
     *
     * @param trade          Trade
     * @param tickerCountMap Ticker count map
     */
    private static void addToMap(Trade trade, Map<String, Integer> tickerCountMap) {
        if (trade.getCall().equals(CallType.BUY)) {
            if (tickerCountMap.containsKey(trade.getTicker())) {
                tickerCountMap.put(trade.getTicker(),
                        tickerCountMap.get(trade.getTicker()) + 1);
            } else {
                tickerCountMap.put(trade.getTicker(), 1);
            }
        } else {
            if (tickerCountMap.containsKey(trade.getTicker())) {
                tickerCountMap.put(trade.getTicker(),
                        tickerCountMap.get(trade.getTicker()) - 1);
            } else {
                tickerCountMap.put(trade.getTicker(), -1);
            }
        }
    }

    /**
     * Returns a sorted list of alerts based on alerts count(abs value).
     * TIME COMPLEXITY :
     * Let n = tickers
     * 1. Loop - O(n)
     * 2. Sort - O(nlogn)
     *
     * SPACE COMPLEXITY: O(tickers)
     *
     * @param tickerCountMap Ticker count map.
     * @return List of alerts.
     */
    private static List<Alerts> getSortedAlerts(Map<String, Integer> tickerCountMap) {
        List<Alerts> alertsList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : tickerCountMap.entrySet()) {
            if (entry.getValue() > 0) {
                alertsList.add(new Alerts(entry.getValue(), CallType.BUY, entry.getKey()));
            } else if (entry.getValue() < 0) {
                alertsList.add(new Alerts(Math.abs(entry.getValue()), CallType.SELL, entry.getKey()));
            }
        }

        alertsList.sort(Comparator.comparingInt(Alerts::getCount).reversed());
        return alertsList;
    }
}
