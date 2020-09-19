package seedu.tr4cker.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DEADLINE_DESC_AMY;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tr4cker.logic.commands.AddCommand;
import seedu.tr4cker.logic.commands.CommandResult;
import seedu.tr4cker.logic.commands.ListCommand;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.storage.JsonTr4cker;
import seedu.tr4cker.storage.JsonUserPrefsStorage;
import seedu.tr4cker.storage.StorageManager;
import seedu.tr4cker.testutil.TaskBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTr4cker tr4ckerStorage =
                new JsonTr4cker(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(tr4ckerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTr4ckerIoExceptionThrowingStub
        JsonTr4cker tr4ckerStorage =
                new JsonTr4ckerIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(tr4ckerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + DEADLINE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Task expectedTask = new TaskBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addTask(expectedTask);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTaskList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getTr4cker(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonTr4ckerIoExceptionThrowingStub extends JsonTr4cker {
        private JsonTr4ckerIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTr4cker(ReadOnlyTr4cker tr4cker, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
