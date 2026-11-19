package org.example;

import org.example.Command.ICommand;

public class RemoteController {
    private static final int numButtons = 4;
    private ICommand[] commands;
    private boolean[] buttons;

    public RemoteController() {
        commands = new ICommand[numButtons];
        buttons = new boolean[numButtons];

        for (int i = 0; i < numButtons; i++) {
            commands[i] = null;
            buttons[i] = false;
        }
    }

    public void setCommand(int idx, ICommand command) {
        if (idx <= numButtons && idx >= 0) {
            commands[idx] = command;
            buttons[idx] = false;
        }
    }

    public void pressButton(int idx) {
        if (idx >= 0 && idx < numButtons && commands[idx] != null) {
            if(!buttons[idx]) {
                commands[idx].execute();
            }
            else{
                commands[idx].undo();
            }

            buttons[idx] = !buttons[idx];
        }
        else{
            System.out.println("No command assigned at button " + idx);
        }
    }
}
