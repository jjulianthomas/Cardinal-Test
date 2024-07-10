import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * RentalService class handles the checkout process and calculates rental
 * agreements for items.
 */
public class RentalService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yy");

    public static void main(String[] args) {
        // Sample usage
        try {
            RentalContract contract = rentItem("LADW", 3, 10, "07/02/20");
            System.out.println(contract);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Rents an item and calculates the rental contract.
     *
     * @param itemCode       The code of the item to be rented.
     * @param rentalDuration The number of days for which the item is rented.
     * @param discountRate   The discount percentage to be applied.
     * @param startDate      The date when the item is checked out (formatted as "MM/dd/yy").
     * @return The RentalContract object detailing the rental contract.
     * @throws Exception if rentalDuration is less than 1 or discountRate is not between 0 and 100.
     */
    public static RentalContract rentItem(String itemCode, int rentalDuration, int discountRate, String startDate)
            throws Exception {
        if (rentalDuration < 1)
            throw new Exception("Rental duration must be 1 or greater.");
        if (discountRate < 0 || discountRate > 100)
            throw new Exception("Discount rate must be between 0 and 100.");

        Item item = Item.getItemByCode(itemCode);
        LocalDate startLocalDate = LocalDate.parse(startDate, DATE_FORMAT);
        LocalDate endDate = startLocalDate.plusDays(rentalDuration);

        int billableDays = calculateBillableDays(startLocalDate, endDate, item);
        double initialCharge = billableDays * item.getDailyFee();
        double discountValue = (initialCharge * discountRate) / 100;
        double totalCharge = initialCharge - discountValue;

        return new RentalContract(item, rentalDuration, startLocalDate, endDate, billableDays, initialCharge,
                discountRate, discountValue, totalCharge);
    }

    /**
     * Calculates the number of billable days between startDate and endDate for the given item.
     *
     * @param startDate The date when the item is checked out.
     * @param endDate   The due date for returning the item.
     * @param item      The item being rented.
     * @return The number of billable days.
     */
    private static int calculateBillableDays(LocalDate startDate, LocalDate endDate, Item item) {
        int billableDays = 0;
        for (LocalDate date = startDate.plusDays(1); !date.isAfter(endDate); date = date.plusDays(1)) {
            if (isBillableDay(date, item))
                billableDays++;
        }
        return billableDays;
    }

    /**
     * Checks if the given date is a billable day for the given item.
     *
     * @param date The date to check.
     * @param item The item being rented.
     * @return true if the date is a billable day, false otherwise.
     */
    private static boolean isBillableDay(LocalDate date, Item item) {
        if (isHoliday(date))
            return item.isHolidayFee();
        if (isWeekend(date))
            return item.isWeekendFee();
        return item.isWeekdayFee();
    }

    /**
     * Checks if the given date is a weekend day (Saturday or Sunday).
     *
     * @param date The date to check.
     * @return true if the date is a weekend day, false otherwise.
     */
    private static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    /**
     * Checks if the given date is a holiday (Independence Day or Labor Day).
     *
     * @param date The date to check.
     * @return true if the date is a holiday, false otherwise.
     */
    private static boolean isHoliday(LocalDate date) {
        int year = date.getYear();
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        if (independenceDay.getDayOfWeek().getValue() == 6) {
            independenceDay = independenceDay.minusDays(1); // Move to Friday if Saturday
        } else if (independenceDay.getDayOfWeek().getValue() == 7) {
            independenceDay = independenceDay.plusDays(1); // Move to Monday if Sunday
        }

        LocalDate laborDay = LocalDate.of(year, 9, 1);
        // Move to the first Monday of September
        laborDay = laborDay.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

        return date.equals(independenceDay) || date.equals(laborDay);
    }
}
