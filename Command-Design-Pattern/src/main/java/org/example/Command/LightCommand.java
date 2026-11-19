package org.example.Command;

import org.example.Receviers.Light;

public class LightCommand implements ICommand{

    private Light light;

    public LightCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute(){
        light.on();
    }

    @Override
    public void undo(){
        light.off();
    }
}
