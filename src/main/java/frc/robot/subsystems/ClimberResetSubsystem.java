package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.sensors.LimitSwitch;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimberResetSubsystem extends Subsystem {
  public TalonSRX climber = new TalonSRX(RobotMap.climber);
  public LimitSwitch climbSwitch = new LimitSwitch(RobotMap.climbSwitch);
  int counter = 1;
 
  public void climbReset(boolean start,boolean reset){
    if (start == true){
        climber.set(ControlMode.PercentOutput, -.3);
        if (climbSwitch.get() == true){
            counter++;
        }
    }
    else if (counter == 3){
        climber.set(ControlMode.PercentOutput, 0);
    }
    else if (reset == true){
        counter = 4;
    }
  }
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
