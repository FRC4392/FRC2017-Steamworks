package org.usfirst.frc.team4392.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Created by basrobotics on 2/18/2017.
 */
public class Gearbox {
    private DoubleSolenoid gearSolenoid;

    public Gearbox(){
        gearSolenoid=new DoubleSolenoid(0,1);
    }

    public void set(DoubleSolenoid.Value value){
        gearSolenoid.set(value);
    }
}
