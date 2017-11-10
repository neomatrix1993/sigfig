import java.time.LocalDate;

/**
 * Trade Class.
 * Created by swapnil.gupta on 10/14/17.
 */
public class Trade {

    private LocalDate date;
    private CallType call;
    private String ticker;

    public Trade(LocalDate date, CallType call, String ticker) {
        this.date = date;
        this.call = call;
        this.ticker = ticker;
    }

    /**
     * String type trade converted to Trade objects.
     *
     * @param trade String of trade.
     * @throws Exception General exception.
     */
    public Trade(String trade) throws InvalidTradeException {
        String regex = ",";
        String[] details = trade.split(regex);
        if (details.length != 3) {
            throw new InvalidTradeException("Invalid trade, length mismatch.");
        }
        this.date = LocalDate.parse(details[0]);

        if (details[1].equals(CallType.BUY.toString())) {
            this.call = CallType.BUY;
        } else if (details[1].equals(CallType.SELL.toString())) {
            this.call = CallType.SELL;
        } else {
            throw new InvalidTradeException("Invalid Call type.");
        }

        this.ticker = details[2];
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CallType getCall() {
        return call;
    }

    public void setCall(CallType call) {
        this.call = call;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return date + "," + call + "," + ticker;
    }
}
