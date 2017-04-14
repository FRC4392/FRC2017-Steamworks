package org.usfirst.frc.team4392.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4392.robot.Subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private final Shooter shooter = new Shooter();
	private final OI operatorInterface = new OI();
	private final Drivetrain drivetrain = new Drivetrain();
	private final CheesyDriveHelper cheesyDriveHelper = new CheesyDriveHelper();
	private final Gearbox gearbox=new Gearbox();
	//private final Intake intake= new Intake();
	private final Hanger hanger = new Hanger();
	private final Timer autoTimer = new Timer();
	private int AutoMode = 0;
	DriverStation ds = DriverStation.getInstance();
	boolean isFirstAuto = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		shooter.init();
		SmartDashboard.putNumber("Auto", 0);
		AutoMode = (int) SmartDashboard.getNumber("Auto",0.0);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit() {
		autoTimer.reset();
		autoTimer.start();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		AutoMode = (int) SmartDashboard.getNumber("Auto", 0);

		switch (AutoMode) {
			case 0:
				shooter.stopShooter();
				drivetrain.driveStraightMagic(450);
				break;
			case 1:
				if (autoTimer.get() < 12){
					shooter.startShooter();
					drivetrain.setLeftRight(0,0);
				} else {
					if (ds.getAlliance() == DriverStation.Alliance.Blue){
						drivetrain.setLeftRight(-.7, -.5);
					} else {
						drivetrain.setLeftRight(-.5, -.7);
					}

					shooter.stopShooter();
				}
				break;
			case 2:
				if (!isFirstAuto){
					isFirstAuto = true;
					drivetrain.reset();
				}
				drivetrain.driveStraightMagic(-(97.3-35.5+9));
				drivetrain.Log();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */

	public void teleopPeriodic() {
		isFirstAuto = false;
		shooter.Log();
		drivetrain.Log();
		double leftSpeed = operatorInterface.driverController.getY(GenericHID.Hand.kLeft);
		double rightSpeed = operatorInterface.driverController.getX(GenericHID.Hand.kRight);

		if (operatorInterface.driverController.getBumper(GenericHID.Hand.kLeft)){
			leftSpeed *= .4;
			rightSpeed *= .4;
		}

		DriveSignal signal = cheesyDriveHelper.cheesyDrive(leftSpeed, rightSpeed, operatorInterface.driverController.getBumper(GenericHID.Hand.kRight));
		drivetrain.setLeftRight(signal.leftMotor, signal.rightMotor);

		if (operatorInterface.operatorController.getAButton()){
			gearbox.set(DoubleSolenoid.Value.kForward);
		} else if (operatorInterface.operatorController.getBButton()){
			gearbox.set(DoubleSolenoid.Value.kReverse);
		}

		/*if (operatorInterface.operatorController.getXButton()){
			intake.start();
		} else if (operatorInterface.operatorController.getYButton()){
			intake.stop();
		}*/

		if ((operatorInterface.operatorController.getTriggerAxis(GenericHID.Hand.kRight) > .5) || (operatorInterface.driverController.getTriggerAxis(GenericHID.Hand.kRight)> .5)){
			shooter.startShooter();
		} else {
			shooter.stopShooter();
		}

		if (operatorInterface.operatorController.getStartButton()) {
			if(operatorInterface.operatorController.getBackButton()) {
				hanger.reverse();
			}else{
				hanger.startHanger();
				}
		} else {
			hanger.stopHanger();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
	}

	public void disabledPeriodic() {
		super.disabledPeriodic();
		drivetrain.Log();
	}
}

