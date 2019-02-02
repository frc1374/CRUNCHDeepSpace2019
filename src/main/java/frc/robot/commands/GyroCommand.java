package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class GyroCommand extends Command {
  double Angle;
	double Start, End;
  public GyroCommand(double angle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.DriveSubsystem);
    Angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Start = System.currentTimeMillis();
    End = System.currentTimeMillis();
    Robot.DriveSubsystem.setSetpoint(Robot.DriveSubsystem.GyroDrive(Angle));
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if (Robot.inAuto || OI.getGyro()) {
      Robot.DriveSubsystem.usePIDOutput(Robot.DriveSubsystem.getPIDController().get());
      Robot.DriveSubsystem.arcadeDrive(0, Robot.DriveSubsystem.rotateToAngleRate);
      if(Math.abs(Robot.DriveSubsystem.getPIDController().getError()) > 2) {
        Start = System.currentTimeMillis(); 
      }
      else {
        End = System.currentTimeMillis();
      }
      System.out.println(End - Start);
    // }
    // else {
    //   Robot.DriveSubsystem.arcadeDrive(OI.getDriverSpeed(), OI.getSteer());
    // }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (End - Start > 200) {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.DriveSubsystem.left1.set(ControlMode.PercentOutput, 0);
    Robot.DriveSubsystem.right1.set(ControlMode.PercentOutput, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
