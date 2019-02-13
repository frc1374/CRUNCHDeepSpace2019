package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static final DriveSubsystem DriveSubsystem = new DriveSubsystem();
  public static final IntakeSubsystem IntakeSubsystem = new IntakeSubsystem();
  //public static final GyroCommand GyroCommand = new GyroCommand();
  //public static boolean inAuto;
  Command DriveCommand = new DriveCommand();
  Command IntakeCommand = new IntakeCommand();
  Command autonomousCommand = new GyroAuto();
  SendableChooser<String> chooser;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    chooser = new SendableChooser<String>();
    chooser.setDefaultOption("AutoDefault", "AutoDefault");
    chooser.addOption("GyroAuto", "GyroAuto");
    SmartDashboard.putData("Auto mode", chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }
  
  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    String Selected = chooser.getSelected();
      switch (Selected) {
	      case "GyroAuto":
	        autonomousCommand = new GyroAuto();
	        break;
        }
    //Robot.DriveSubsystem.ResetGyroAngle();
    //inAuto = true;
    autonomousCommand.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    DriveCommand.start();
    IntakeCommand.start();
    //GyroCommand.start();
    //inAuto = false;
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
