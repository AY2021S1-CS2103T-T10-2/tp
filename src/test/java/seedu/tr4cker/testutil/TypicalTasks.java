package seedu.tr4cker.testutil;

import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_AMY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_BOB;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_AMY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ALICE = new TaskBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withCompletionStatus(0)
            .withDeadline("2020-10-20 1800")
            .withTags("friends").build();
    public static final Task BENSON = new TaskBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withCompletionStatus(0).withDeadline("2020-09-30 2359")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new TaskBuilder().withName("Carl Kurz").withDeadline("2020-10-03 1200")
            .withCompletionStatus(0).withAddress("wall street").build();
    public static final Task DANIEL = new TaskBuilder().withName("Daniel Meier").withDeadline("2020-09-21 1500")
            .withCompletionStatus(0).withAddress("10th street").withTags("friends").build();
    public static final Task ELLE = new TaskBuilder().withName("Elle Meyer").withDeadline("2020-10-10 1010")
            .withCompletionStatus(0).withAddress("michegan ave").build();
    public static final Task FIONA = new TaskBuilder().withName("Fiona Kunz").withDeadline("2020-12-25 0000")
            .withCompletionStatus(0).withAddress("little tokyo").build();
    public static final Task GEORGE = new TaskBuilder().withName("George Best").withDeadline("2021-01-01 0000")
            .withCompletionStatus(0).withAddress("4th street").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withName("Hoon Meier").withDeadline("2020-12-07 0600")
            .withCompletionStatus(0).withAddress("little india").build();
    public static final Task IDA = new TaskBuilder().withName("Ida Mueller").withDeadline("2020-09-27 2359")
            .withCompletionStatus(0).withAddress("chicago ave").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withName(VALID_NAME_AMY).withDeadline(VALID_DEADLINE_AMY)
            .withCompletionStatus(VALID_COMPLETION_STATUS_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_BOB).withDeadline(VALID_DEADLINE_BOB)
            .withCompletionStatus(VALID_COMPLETION_STATUS_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code Tr4cker} with all the typical tasks.
     */
    public static Tr4cker getTypicalTr4cker() {
        Tr4cker ab = new Tr4cker();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
