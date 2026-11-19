package org.example;

import org.example.Command.FanCommand;
import org.example.Command.LightCommand;
import org.example.Receviers.Fan;
import org.example.Receviers.Light;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Light livingRoomLight = new Light();
        Fan ceillingFan = new Fan();

        RemoteController remoteController = new RemoteController();

        remoteController.setCommand(0, new LightCommand(livingRoomLight));
        remoteController.setCommand(1, new FanCommand(ceillingFan));

        remoteController.pressButton(0);
        remoteController.pressButton(1);
        remoteController.pressButton(0);
        remoteController.pressButton(1);

        remoteController.pressButton(2);




    }
}
