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

  public void rotateClimber(boolean axis, boolean back) {
    // press a to climb, and the talon has a breakout board to automatically stop the climber at 90
    // if that does not work go till bumpers pass lvl 2
    // if you go over, use the arm.
    if (axis) {
      climber.set(ControlMode.PercentOutput, .55);
    }  
    else if (!axis && !back){
      climber.set(ControlMode.PercentOutput,0);
    }
    else if(back){
      climber.set(ControlMode.PercentOutput,-.4);
    }
  }
  
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
