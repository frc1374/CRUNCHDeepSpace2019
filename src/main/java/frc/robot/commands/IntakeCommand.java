package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class IntakeCommand extends Command {
  public IntakeCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.IntakeSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.IntakeSubsystem.ejectHatch(OI.getEjectorOut(), OI.getEjectorIn());
    Robot.IntakeSubsystem.arm(OI.getArm());
    Robot.IntakeSubsystem.wrist(OI.getWrist());
    Robot.IntakeSubsystem.wheels(OI.getWheels());
    Robot.IntakeSubsystem.zoop(OI.bigZoop());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
