package org.usfirst.frc.team4392.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Created by basrobotics on 2/18/2017.
 */
public class OI {
    public XboxController driverController;
    public XboxController operatorController;

    public  OI(){
        driverController = new XboxController(0);
        operatorController = new XboxController(1);
    }

}
