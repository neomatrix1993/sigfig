import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test cases.
 *
 * @author swapnil.gupta
 */
public class Tests {
    private List<Trade> trades = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
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
    }

    @Test
    public void emptyAlertTest() throws Exception {
        List<Trade> trades = new ArrayList<>();
        List<Alerts> alerts = UserService.getAlertsByTradeListAndDate(trades, LocalDate.now());
        Assert.assertEquals(0, alerts.size());
    }

    @Test
    public void invalidCallTypeTest() throws Exception {
        try {
            new Trade("2017-10-14,ASD,GOOG");
        } catch (Exception e) {
            Assert.assertEquals("Invalid Call type.", e.getMessage());
        }
    }

    @Test
    public void invalidTransactionLengthTest() throws Exception {
        try {
            new Trade("2017-10-14,CALL,GOOG,124");
        } catch (Exception e) {
            Assert.assertEquals("Invalid trade, length mismatch.", e.getMessage());
        }
    }

    /**
     * This Test will only work from Oct 15 till one of the
     *
     * @throws Exception Exception.
     */
    @Test
    public void validAlertTest() throws Exception {
        List<Alerts> expected = Arrays.asList(
                new Alerts(2, CallType.BUY, "GOOG"),
                new Alerts(1, CallType.BUY, "YOLO"),
                new Alerts(1, CallType.BUY, "AAPL")
        );
        LocalDate now = LocalDate.parse("2017-10-15");
        List<Alerts> alerts = UserService.getAlertsByTradeListAndDate(this.trades, now);
        System.out.println(alerts);
        Assert.assertEquals(expected.toString(), alerts.toString());
    }

    @Test
    public void notPresentInWeekTest() throws Exception {
        List<Alerts> alerts = UserService.getAlertsByTradeListAndDate(Arrays.asList(
                new Trade("2017-10-01,BUY,GOOG"),
                new Trade("2017-10-01,BUY,GOOG")
        ), LocalDate.now());
        Assert.assertEquals(0, alerts.size());
    }


    @Test
    public void zeroBuySellTest() throws Exception {
        List<Alerts> alerts = UserService.getAlertsByTradeListAndDate(Arrays.asList(
                new Trade("2017-10-14,BUY,GOOG"),
                new Trade("2017-10-14,SELL,GOOG")
        ), LocalDate.now());
        Assert.assertEquals(0, alerts.size());
    }
}