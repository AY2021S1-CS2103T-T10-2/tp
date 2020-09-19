package seedu.tr4cker.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline= "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline time
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadline times
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("91")); // less than 3 numbers
        assertFalse(Deadline.isValidDeadline("Deadline")); // non-numeric
        assertFalse(Deadline.isValidDeadline("9011p041")); // alphabets within digits
        assertFalse(Deadline.isValidDeadline("9312 1534")); // spaces within digits

        // valid deadline times
        assertTrue(Deadline.isValidDeadline("911")); // exactly 3 numbers
        assertTrue(Deadline.isValidDeadline("93121534"));
        assertTrue(Deadline.isValidDeadline("124293842033123")); // long deadline times
    }
}
