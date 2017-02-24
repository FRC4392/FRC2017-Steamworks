package org.usfirst.frc.team4392.robot.Subsystems;

import com.ctre.CANTalon;

/**
 * Created by basrobotics on 2/18/2017.
 */

public class Drivetrain {
    private CANTalon talonFR, talonFL, talonBR, talonBL;
    private double speed;

    public Drivetrain(){
        talonFR = new CANTalon(13);
        talonBR = new CANTalon(14);
        talonFL = new CANTalon(15);
        talonBL = new CANTalon( 16);

        talonFL.setInverted(true);
        talonBL.setInverted(true);


    }

    public void setLeftRight(double left, double right){
        setLeft(left);
        setRight(right);
    }

    public void setRight(double speed){
        talonFR.set(speed);
        talonBR.set(speed);
    }

    public void setLeft(double speed){
        talonFL.set(speed);
        talonBL.set(speed);
    }
}