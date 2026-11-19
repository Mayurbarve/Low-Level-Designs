package org.example.Command;

import org.example.Receviers.Fan;

public class FanCommand implements ICommand{

    private Fan fan;

    public FanCommand(Fan fan){
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.on();
    }

    @Override
    public void undo() {
        fan.off();
    }
}
