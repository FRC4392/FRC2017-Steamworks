package org.usfirst.frc.team4392.robot.Subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by basrobotics on 2/14/2017.
 */
public class Shooter {

    private CANTalon shootwheel = new CANTalon (11);
    private CANTalon intakewheel = new CANTalon(12);

    public void init(){

        shootwheel.setFeedbackDevice(CANTalon.FeedbackDevice.EncRising);
        shootwheel.configNominalOutputVoltage(0.0f, -0.0f);
        shootwheel.configPeakOutputVoltage(+12.0f, -12.0f);
        shootwheel.configEncoderCodesPerRev(1024);
        shootwheel.SetVelocityMeasurementPeriod(CANTalon.VelocityMeasurementPeriod.valueOf(10));
        shootwheel.setF(.053);
        shootwheel.setP(6);
        shootwheel.setD(50);
        shootwheel.changeControlMode(CANTalon.TalonControlMode.Speed);


        //intakewheel.setControlMode(CANTalon.TalonControlMode.PercentVbus);

        shootwheel.set(0);
        intakewheel.set(0);
    }

    public void startShooter(){
        shootwheel.enable();
        shootwheel.set(5800);
        if (shootwheel.getSpeed() > 5700) {
            intakewheel.set(1);
        } else {
            intakewheel.set(0);
        }
    }

    public void Log(){
        SmartDashboard.putNumber("Speed", shootwheel.getSpeed());
    }
    public void stopShooter(){
        shootwheel.disable();
        intakewheel.set(0);
    }

}
