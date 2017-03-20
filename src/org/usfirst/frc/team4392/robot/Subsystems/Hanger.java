package org.usfirst.frc.team4392.robot.Subsystems;

import edu.wpi.first.wpilibj.Spark;

/**
 * Created by basrobotics on 3/11/2017.
 */
public class Hanger {

    private Spark hangerSpark;

    public Hanger(){
        hangerSpark = new Spark(1);
    }

    public void startHanger(){
        hangerSpark.set(-1);
    }

    public void stopHanger(){
        hangerSpark.set(0);
    }

    public void reverse(){
        hangerSpark.set(1);
    }
}
