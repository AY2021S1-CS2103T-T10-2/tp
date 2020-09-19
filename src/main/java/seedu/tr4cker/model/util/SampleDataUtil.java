package seedu.tr4cker.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Address;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Email;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;

/**
 * Contains utility methods for populating {@code Tr4cker} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Alex Yeoh"), new Deadline("2020-10-20 1800"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Task(new Name("Bernice Yu"), new Deadline("2020-09-30 2359"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Task(new Name("Charlotte Oliveiro"), new Deadline("2020-10-03 1200"), new Email("charlotte@example"
                    + ".com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Task(new Name("David Li"), new Deadline("2020-09-21 1500"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Task(new Name("Irfan Ibrahim"), new Deadline("2020-10-10 1010"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Task(new Name("Roy Balakrishnan"), new Deadline("2020-12-25 0000"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyTr4cker getSampleTr4cker() {
        Tr4cker sampleAb = new Tr4cker();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
