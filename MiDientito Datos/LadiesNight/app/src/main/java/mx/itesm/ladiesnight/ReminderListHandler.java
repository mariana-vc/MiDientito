package mx.itesm.ladiesnight;

import java.util.ArrayList;

/**
 * Created by beeme on 03/04/2016.
 */
public class ReminderListHandler extends ValueListHandler<Reminder> {
    private ReminderDAO dao = null;
    private Reminder reminder = null;

    public ReminderListHandler(Reminder reminder, String id) {
        try {
            this.reminder = reminder;
            this.dao = new ReminderDAO(id);
            executeSearch();
        } catch (Exception e) {
        }
    }

    public void executeSearch() {
        try {
            if (reminder == null) {
                System.out.println("reminder is null");
            }
            ArrayList<Reminder> resultsList =dao.executeSelect(reminder);
            setList(resultsList);
        } catch (Exception e) {
        }
    }
}
