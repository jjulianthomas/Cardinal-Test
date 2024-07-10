import java.util.HashMap;
import java.util.Map;

/**
 * Represents an item available for rental.
 */
public class Item {

    private static final Map<String, Item> ITEMS = new HashMap<>();

    static {
        // Initialize item instances and populate the ITEMS map
        ITEMS.put("LADW", new Item("LADW", "Ladder", "Werner", 1.99, true, true, false));
        ITEMS.put("CHNS", new Item("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true));
        ITEMS.put("JAKD", new Item("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        ITEMS.put("JAKR", new Item("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));
    }

    public String code;
    public String type;
    public String brand;
    private double dailyFee;
    private boolean weekdayFee;
    private boolean weekendFee;
    private boolean holidayFee;

    /**
     * Private constructor to create an Item object.
     *
     * @param code          The unique code for the item.
     * @param type          The type or category of the item.
     * @param brand         The brand or manufacturer of the item.
     * @param dailyFee      The daily rental charge for the item.
     * @param weekdayFee    Whether the item incurs rental charges on weekdays.
     * @param weekendFee    Whether the item incurs rental charges on weekends.
     * @param holidayFee    Whether the item incurs rental charges on holidays.
     */
    private Item(String code, String type, String brand, double dailyFee, boolean weekdayFee,
                 boolean weekendFee, boolean holidayFee) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.dailyFee = dailyFee;
        this.weekdayFee = weekdayFee;
        this.weekendFee = weekendFee;
        this.holidayFee = holidayFee;
    }

    /**
     * Retrieves an Item object based on its unique code.
     *
     * @param code The code of the item to retrieve.
     * @return The Item object corresponding to the code, or null if not found.
     */
    public static Item getItemByCode(String code) {
        return ITEMS.get(code);
    }

    /**
     * Retrieves the daily rental charge for the item.
     *
     * @return The daily rental charge.
     */
    public double getDailyFee() {
        return dailyFee;
    }

    /**
     * Checks if the item incurs rental charges on weekdays.
     *
     * @return true if the item incurs rental charges on weekdays, false otherwise.
     */
    public boolean isWeekdayFee() {
        return weekdayFee;
    }

    /**
     * Checks if the item incurs rental charges on weekends.
     *
     * @return true if the item incurs rental charges on weekends, false otherwise.
     */
    public boolean isWeekendFee() {
        return weekendFee;
    }

    /**
     * Checks if the item incurs rental charges on holidays.
     *
     * @return true if the item incurs rental charges on holidays, false otherwise.
     */
    public boolean isHolidayFee() {
        return holidayFee;
    }

    /**
     * Returns a string representation of the Item object.
     *
     * @return String representation of the Item object.
     */
    @Override
    public String toString() {
        return String.format("Item code: %s\nItem type: %s\nBrand: %s\n", code, type, brand);
    }
}
