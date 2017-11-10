/**
 * Alerts Class.
 *
 * @author swapnil.gupta
 */
public class Alerts {
    private int count;
    private CallType call;
    private String ticker;

    public Alerts(int count, CallType call, String ticker) {
        this.count = count;
        this.call = call;
        this.ticker = ticker;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
        return count + "," + call + "," + ticker;
    }
}
