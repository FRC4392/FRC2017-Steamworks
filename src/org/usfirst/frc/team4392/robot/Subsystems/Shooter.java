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
        //shootwheel.changeControlMode(CANTalon.TalonControlMode.Speed);
        shootwheel.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        //shootwheel.configNominalOutputVoltage(0.0f, -0.0f);
        //shootwheel.configPeakOutputVoltage(+12.0f, -12.0f);

        //intakewheel.setControlMode(CANTalon.TalonControlMode.PercentVbus);

        shootwheel.set(0);
        intakewheel.set(0);
    }

    public void startShooter(){
        shootwheel.set(-.5);
        intakewheel.set(1);
    }

    public void Log(){
        SmartDashboard.putNumber("Speed", shootwheel.getSpeed());
    }
    public void stopShooter(){
        shootwheel.set(0);
        intakewheel.set(0);
    }

}
