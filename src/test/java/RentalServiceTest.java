import org.junit.Test;

import static org.junit.Assert.*;

public class RentalServiceTest {

    @Test
    public void testRentalContract1() throws Exception {
        try {
            RentalContract contract = RentalService.rentItem("JAKR", 5, 101, "09/03/15");
            fail("Expected exception not thrown");
        } catch (Exception exception) {
        }
    }

    @Test
    public void testRentalContract2() throws Exception {
        RentalContract contract = RentalService.rentItem("LADW", 3, 10, "07/02/20");
        assertNotNull(contract);
    }

    @Test
    public void testRentalContract3() throws Exception {
        RentalContract contract = RentalService.rentItem("CHNS", 5, 25, "07/02/15");
        assertNotNull(contract);
    }

    @Test
    public void testRentalContract4() throws Exception {
        RentalContract contract = RentalService.rentItem("JAKD", 6, 0, "09/03/15");
        assertNotNull(contract);
    }

    @Test
    public void testRentalContract5() throws Exception {
        RentalContract contract = RentalService.rentItem("JAKR", 9, 0, "07/02/15");
        assertNotNull(contract);
    }

    @Test
    public void testRentalContract6() throws Exception {
        RentalContract contract = RentalService.rentItem("JAKR", 4, 50, "07/02/20");
        assertNotNull(contract);
    }

    @Test
    public void testInvalidRentalDuration() {
        Exception exception = assertThrows(Exception.class, () -> {
            RentalService.rentItem("LADW", 0, 10, "07/02/20");
        });
        assertTrue(exception.getMessage().contains("Rental duration must be 1 or greater."));
    }

    @Test
    public void testInvalidDiscountRate() {
        Exception exception = assertThrows(Exception.class, () -> {
            RentalService.rentItem("LADW", 3, 101, "07/02/20");
        });
        assertTrue(exception.getMessage().contains("Discount rate must be between 0 and 100."));
    }
}
