package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimberSubsystem extends Subsystem {
  public TalonSRX climber = new TalonSRX(RobotMap.climber);

  public void rotateClimber(double axis) {
    if (axis >= 0.5 || axis <= -0.5) {
      climber.set(ControlMode.PercentOutput, axis);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
