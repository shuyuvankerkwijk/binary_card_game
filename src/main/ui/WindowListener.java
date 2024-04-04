package ui;

import model.Event;
import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Creates a WindowListener with mostly empty functions (WindowAdapter) except
 * it listens for the window being closed, and prints the EventLog when the
 * window is closed.
 */
public class WindowListener extends WindowAdapter {

    // EFFECTS: Constructs WindowListener
    public WindowListener() {
        super();
    }

    // EFFECTS: Prints EventLog of all game events
    @Override
    public void windowClosing(WindowEvent e) {
        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            System.out.println(event);
        }
        System.exit(0);
    }
}
