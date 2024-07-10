import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a rental contract for an item.
 */
class RentalContract {

    private Item item;
    private int rentalDuration;
    private LocalDate startDate;
    private LocalDate endDate;
    private int billableDays;
    private double initialCharge;
    private int discountRate;
    private double discountValue;
    private double totalCharge;

    /**
     * Constructs a new RentalContract object.
     *
     * @param item           The item being rented.
     * @param rentalDuration Number of days the item is rented for.
     * @param startDate      The date the item is checked out.
     * @param endDate        The due date for returning the item.
     * @param billableDays   Number of days the item is charged for.
     * @param initialCharge  Total charge before any discounts.
     * @param discountRate   Percentage discount applied.
     * @param discountValue  Amount of discount applied.
     * @param totalCharge    Final charge after applying discounts.
     */
    public RentalContract(Item item, int rentalDuration, LocalDate startDate, LocalDate endDate, int billableDays,
                          double initialCharge, int discountRate, double discountValue, double totalCharge) {
        this.item = item;
        this.rentalDuration = rentalDuration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.billableDays = billableDays;
        this.initialCharge = initialCharge;
        this.discountRate = discountRate;
        this.discountValue = discountValue;
        this.totalCharge = totalCharge;
    }

    /**
     * Returns a string representation of the RentalContract object.
     *
     * @return String representation of the RentalContract.
     */
    @Override
    public String toString() {
        return String.format(
                "Item code: %s\nItem type: %s\nBrand: %s\nRental duration: %d\nStart date: %s\nEnd date: %s\nDaily rental charge: $%.2f\nBillable days: %d\nInitial charge: $%.2f\nDiscount rate: %d%%\nDiscount value: $%.2f\nTotal charge: $%.2f\n",
                item.code, item.type, item.brand, rentalDuration,
                startDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")),
                endDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")),
                item.getDailyFee(), billableDays, initialCharge, discountRate, discountValue, totalCharge);
    }
}
