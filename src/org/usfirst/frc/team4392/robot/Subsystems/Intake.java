package org.usfirst.frc.team4392.robot.Subsystems;

import com.ctre.CANTalon;

/**
 * Created by basrobotics on 2/18/2017.
 */
public class Intake {
    CANTalon left, right;

    public Intake(){
        //left =new CANTalon(7);
        right =new CANTalon(8);

        right.setInverted(true);
    }

    public void start(){
        left.set(-1);
        right.set(-1);
    }

    public void stop(){
        left.set(0);
        right.set(0);
    }
}
