package seedu.tr4cker.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.task.Task;

/**
 * An Immutable Tr4cker that is serializable to JSON format.
 */
@JsonRootName(value = "tr4cker")
class JsonSerializableTr4cker {

    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate task(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTr4cker} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableTr4cker(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTr4cker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTr4cker}.
     */
    public JsonSerializableTr4cker(ReadOnlyTr4cker source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Tr4cker into the model's {@code Tr4cker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Tr4cker toModelType() throws IllegalValueException {
        Tr4cker tr4cker = new Tr4cker();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (tr4cker.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            tr4cker.addTask(task);
        }
        return tr4cker;
    }

}
