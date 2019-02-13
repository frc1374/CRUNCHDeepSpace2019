package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutonomousDriveCommand extends Command {
  double Start, End, Time, Speed, Turn;
  public AutonomousDriveCommand(double speed, double time, double turn) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.DriveSubsystem);
    Time = time;
    Speed = -speed;
    Turn = turn;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Start = System.currentTimeMillis();
    End = System.currentTimeMillis();
    Robot.DriveSubsystem.arcadeDrive(0, 0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.DriveSubsystem.arcadeDrive(Speed, Turn);
    End = System.currentTimeMillis();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (End - Start > Time) {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.DriveSubsystem.arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
