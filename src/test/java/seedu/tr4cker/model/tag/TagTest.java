package seedu.tr4cker.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void testToString() {
        String tagName = "tagName";
        Tag tag = new Tag(tagName);
        assertEquals(tag.toString(), "[tagName]");
    }

}
