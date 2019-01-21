package frc.robot.sensors;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch extends DigitalInput{
	
	public LimitSwitch(int channel) {
		super(channel);
	}
	
    public boolean get() { 	
    	
    	return !super.get();
    }
}