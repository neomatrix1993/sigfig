import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Util to convert date to specific pattern.
 *
 * @author swapnil.gupta
 */
public class Util {

    public static boolean isDateInPastWeek(LocalDate date) {
        LocalDate now = LocalDate.now();
        return ChronoUnit.WEEKS.between(date, now) <= 0;
    }

    public static boolean isDateInPastWeek(LocalDate from, LocalDate to) {
        return ChronoUnit.WEEKS.between(from, to) <= 0;
    }
}
