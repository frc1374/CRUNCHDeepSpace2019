package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

import frc.robot.Constants;
import frc.robot.RobotMap;

public class DriveSubsystem extends PIDSubsystem {

  public TalonSRX left1 = new TalonSRX(RobotMap.left1);
  public TalonSRX left2 = new TalonSRX(RobotMap.left2);
  public TalonSRX right1 = new TalonSRX(RobotMap.right1);
  public TalonSRX right2 = new TalonSRX(RobotMap.right2);

  public DriveSubsystem() {
    // Insert a subsystem name and PID values here
    super("DriveSubsystem", Constants.kP, Constants.kI, Constants.kD);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  public void tankDrive(double left, double right) {
    left1.set(ControlMode.PercentOutput, left);
    left2.set(ControlMode.PercentOutput, left);
    right1.set(ControlMode.PercentOutput, -right);
    right2.set(ControlMode.PercentOutput, -right);
  }

  public void arcadeDrive(double speed, double turn) {
    tankDrive(speed-turn, speed+turn);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return 0.0;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }
}
