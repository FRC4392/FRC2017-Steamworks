package org.usfirst.frc.team4392.robot.Subsystems;

import com.ctre.CANTalon;

/**
 * Created by basrobotics on 2/14/2017. 
 */
public class Shooter {

    private CANTalon shootwheel = new CANTalon (11);
    private CANTalon intakewheel = new CANTalon(12);

    public void init(){
        //shootwheel.setControlMode(CANTalon.TalonControlMode.PercentVbus);
        //intakewheel.setControlMode(CANTalon.TalonControlMode.PercentVbus);

        shootwheel.set(0);
        intakewheel.set(0);
    }

    public void startShooter(){
        shootwheel.set(-.4);
        intakewheel.set(1);
    }
    public void stopShooter(){
        shootwheel.set(0);
        intakewheel.set(0);
    }

}
