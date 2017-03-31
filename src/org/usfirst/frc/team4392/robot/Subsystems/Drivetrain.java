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

        talonFL.setInverted(false);
        talonBL.setInverted(false);
        talonFR.setInverted(true);
        talonBR.setInverted(true);

        talonFL.changeControlMode(CANTalon.TalonControlMode.Follower);
        talonFR.changeControlMode(CANTalon.TalonControlMode.Follower);

        talonFL.set(15);
        talonFR.set(13);

        talonBL.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        talonBR.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);

        //Determine if sensors need to be reversed

        talonBL.configNominalOutputVoltage(+0.0f, -0.0f);
        talonBR.configNominalOutputVoltage(+0.0f, -0.0f);
        talonBR.configPeakOutputVoltage(+12.0f, -12.0f);
        talonBL.configPeakOutputVoltage(+12.0f, -12.0f);

        talonBR.setProfile(0);
        talonBL.setProfile(0);

        talonBR.setP(0);
        talonBR.setI(0);
        talonBR.setD(0);
        talonBR.setF(0);

        talonBL.setP(0);
        talonBL.setI(0);
        talonBL.setD(0);
        talonBL.setF(0);

        talonBL.setMotionMagicCruiseVelocity(0);
        talonBR.setMotionMagicCruiseVelocity(0);

        talonBR.setMotionMagicAcceleration(0);
        talonBL.setMotionMagicAcceleration(0);


    }

    public void setLeftRight(double left, double right){
        setLeft(left);
        setRight(right);
    }

    public void setRight(double speed){
        talonBR.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        talonBR.set(speed);
    }

    public void setLeft(double speed){
        talonBL.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        talonBL.set(speed);
    }

    public void driveStraightMagic(double distance){
        talonBL.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
        talonBR.changeControlMode(CANTalon.TalonControlMode.MotionMagic);

        talonBL.set(distance);
        talonBR.set(distance);
    }


}