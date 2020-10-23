package seedu.tr4cker.ui.planner;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.logic.Logic;
import seedu.tr4cker.logic.commands.CommandResult;
import seedu.tr4cker.model.planner.PlannerDay;
import seedu.tr4cker.ui.TaskListPanel;
import seedu.tr4cker.ui.UiPart;

/**
 * An UI component that displays Planner tab.
 * Displays the calendar and tasks list.
 */
public class PlannerTabWindow extends UiPart<Region> {

    private static final String FXML = "PlannerTabWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(PlannerCalendarPanel.class);
    private final PlannerCalendarPanel plannerCalendarPanel;
    private final TaskListPanel taskListPanel;

    @FXML
    private StackPane plannerCalendarPanelPlaceholder;
    @FXML
    private StackPane plannerTaskListPanelPlaceholder;

    /**
     * Constructor for PlannerTabWindow.
     *
     * @param logic Logic of TR4CKER.
     */
    public PlannerTabWindow(Logic logic) {
        super(FXML);
        logger.fine("Initialising Planner Tab Window");
        this.plannerCalendarPanel = new PlannerCalendarPanel(PlannerDay.getCurrDay());
        this.taskListPanel = new TaskListPanel(logic.getPlannerFilteredTaskList());

        plannerCalendarPanelPlaceholder.getChildren().add(plannerCalendarPanel.getRoot());
        plannerTaskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
        logger.fine("Created Planner Tab Window.");
    }

    /**
     * Updates calendar aspect of Planner tab.
     *
     * @param commandResult CommandResult being passed in.
     */
    public void updateCalendar(CommandResult commandResult) { // from mainwindow
        LocalDate localDate = commandResult.getLocalDate();
        YearMonth yearMonth = commandResult.getYearMonth();
        if (localDate != null && yearMonth == null) { // user wants to go to specified date
            updateToLocalDate(localDate);
        } else if (localDate == null && yearMonth != null) { // user wants to go to specified month
            updateToYearMonth(yearMonth);
        }
    }

    /**
     * Updates calendar view to reflect user's input (specific date).
     *
     * @param localDate User's input.
     */
    private void updateToLocalDate(LocalDate localDate) {
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int date = localDate.getDayOfMonth();
        plannerCalendarPanel.setCurrentYear(year);
        plannerCalendarPanel.setCurrentMonth(month);
        LocalDate newDate = LocalDate.of(year, month, 1);
        LocalDate userInput = LocalDate.of(year, month, date);
        YearMonth newYearMonth = YearMonth.of(year, month);
        PlannerDay newDay = new PlannerDay(newDate);
        plannerCalendarPanel.clearCalendar();
        plannerCalendarPanel.changeCalendarMonthYear(newYearMonth);
        plannerCalendarPanel.fillCalendarTable(newDay, userInput);
        logger.fine("Updated calendar view to " + localDate.toString());
    }

    /**
     * Updates calendar view to reflect user's input (specific month).
     *
     * @param yearMonth User's input.
     */
    private void updateToYearMonth(YearMonth yearMonth) {
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();
        plannerCalendarPanel.setCurrentYear(year);
        plannerCalendarPanel.setCurrentMonth(month);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        PlannerDay newDay = new PlannerDay(firstDayOfMonth);
        plannerCalendarPanel.clearCalendar();
        plannerCalendarPanel.changeCalendarMonthYear(yearMonth);
        plannerCalendarPanel.fillCalendarTable(newDay, null);
        logger.fine("Updated calendar view to " + yearMonth.toString());
    }

}
