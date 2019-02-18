package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.sensors.LimitSwitch;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimberSubsystem extends Subsystem {
  public TalonSRX climber = new TalonSRX(RobotMap.climber);
  public LimitSwitch climbSwitch = new LimitSwitch(RobotMap.climbSwitch);
  public LimitSwitch climbSwitchR = new LimitSwitch(RobotMap.climbSwitchR);
  public static int count;
  public boolean wasactive;
/*else if (climbSwitch.get() == false && axis == false) {
      climber.set(ControlMode.PercentOutput, 0);
    } */

  public void rotateClimber(Boolean axis, boolean back) {
    
    if (climbSwitch.get() == false && axis == true) {
      climber.set(ControlMode.PercentOutput, .5);
    }  
    else if (climbSwitch.get() == true){
      climber.set(ControlMode.PercentOutput,0);
    }
    else if(back == true ){
      climber.set(ControlMode.PercentOutput,-.5);
    }
    else if(back == false ){
      climber.set(ControlMode.PercentOutput,0);
    }
     if (climbSwitchR.get() == true && count == 1){
      count = 2;
      wasactive = true;
    }
    wasactive = false;
     if (climbSwitchR.get() == true && count == 2 && wasactive == false){
      count = 3;
    }
     if (count == 3){
       back = false;
       climber.set(ControlMode.PercentOutput,0);
     }
   
    
    
  }
  
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    count = 1;
  }
}
