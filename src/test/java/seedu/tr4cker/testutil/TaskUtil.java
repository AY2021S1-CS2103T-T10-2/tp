package seedu.tr4cker.testutil;

import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.tr4cker.logic.commands.AddCommand;
import seedu.tr4cker.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code task}.
     */
    public static String getAddCommand(Task task) {
        return AddCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + task.getName().fullName + " ");
        sb.append(PREFIX_DEADLINE+ task.getDeadline().value + " ");
        sb.append(PREFIX_EMAIL + task.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + task.getAddress().value + " ");
        task.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTaskDescriptor}'s details.
     */
    public static String getEditTaskDescriptorDetails(EditTaskDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getDeadline().ifPresent(deadline-> sb.append(PREFIX_DEADLINE).append(deadline.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
