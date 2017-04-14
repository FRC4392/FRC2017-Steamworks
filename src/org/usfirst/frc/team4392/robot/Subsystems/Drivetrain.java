package org.usfirst.frc.team4392.robot.Subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.opencv.core.Mat;

/**
 * Created by basrobotics on 2/18/2017.
 */

public class Drivetrain {
    private CANTalon talonFR, talonFL, talonBR, talonBL;
    private double speed;

    public Drivetrain(){
        talonFR = new CANTalon(13);
        talonBR = new CANTalon(14);
        talonFL = new CANTalon(7);
        talonBL = new CANTalon( 15);

        talonFL.setInverted(false);
        talonBL.setInverted(false);
        //talonFR.reverseOutput(true);
        talonBR.reverseOutput(true);

        talonFL.changeControlMode(CANTalon.TalonControlMode.Follower);
        talonFR.changeControlMode(CANTalon.TalonControlMode.Follower);

        talonFL.set(talonBL.getDeviceID());
        talonFR.set(talonBR.getDeviceID());

        talonBL.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        talonBR.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);

        //Determine if sensors need to be reversed

        talonBL.configNominalOutputVoltage(+0.0f, -0.0f);
        talonBR.configNominalOutputVoltage(+0.0f, -0.0f);
        talonBR.configPeakOutputVoltage(+12.0f, -12.0f);
        talonBL.configPeakOutputVoltage(+12.0f, -12.0f);

        talonBR.setProfile(0);
        talonBL.setProfile(0);

        talonBR.reverseSensor(false);
        talonBR.setP(.047);//37
        talonBR.setI(0);
        talonBR.setD(0);
        talonBR.setF(.339);


        talonBL.reverseSensor(true);
        talonBL.setP(0.050);//45
        talonBL.setI(0);
        talonBL.setD(0);
        talonBL.setF(.346);

        talonBL.setProfile(0);
        talonBR.setProfile(0);

        talonBL.setMotionMagicCruiseVelocity(-324);
        talonBR.setMotionMagicCruiseVelocity(-324);

        talonBR.setMotionMagicAcceleration(324);
        talonBL.setMotionMagicAcceleration(324);

        reset();


    }

    public void setLeftRight(double left, double right){
        setLeft(left);
        setRight(right);
    }

    public void setRight(double speed){
        talonBR.changeControlMode(CANTalon.TalonControlMode.Speed);
        speed = speed * 433;
        talonBR.set(speed);
    }

    public void setLeft(double speed){
        talonBL.changeControlMode(CANTalon.TalonControlMode.Speed);
        speed = speed * 433;
        talonBL.set(speed);
    }

    public void driveStraightMagic(double distance){
        talonBL.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
        talonBR.changeControlMode(CANTalon.TalonControlMode.MotionMagic);

        double circum = 2* 3.14159*3;
        distance = distance/circum;

        talonBL.set(distance);
        talonBR.set(distance);
    }

    public void Log(){
        SmartDashboard.putNumber("Left Speed", talonBL.getSpeed());
        SmartDashboard.putNumber("Right Speed", talonBR.getSpeed());
        SmartDashboard.putNumber("Left Position", talonBL.getPosition());
        SmartDashboard.putNumber("Right Postition", talonBR.getPosition());
        SmartDashboard.putNumber("Right Error", talonBR.getClosedLoopError());
        SmartDashboard.putNumber("Left Error", talonBL.getClosedLoopError());
    }

    public void reset(){
        talonBL.setPosition(0);
        talonBR.setPosition(0);
    }


}