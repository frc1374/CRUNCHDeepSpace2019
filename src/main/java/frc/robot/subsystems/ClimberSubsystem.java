package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.sensors.LimitSwitch;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimberSubsystem extends Subsystem {
  public TalonSRX climber = new TalonSRX(RobotMap.climber);
  public LimitSwitch climbSwitch = new LimitSwitch(RobotMap.climbSwitch);
  public void rotateClimber(double axis) {
    if (axis >= 0.5 || axis <= -0.5) {
      climber.set(ControlMode.PercentOutput, axis);
      if(climbSwitch.get() == false){
        climber.set(ControlMode.PercentOutput,0);
      }
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
