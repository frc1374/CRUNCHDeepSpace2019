package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

import frc.robot.Constants;
import frc.robot.RobotMap;

public class DriveSubsystem extends PIDSubsystem {
  static int b;
  public boolean isstill = true;
  public static float angle = 0;
	public float minimalvoltage = 0.25f;
	public double post = 0;
  public double negt = 0;
  public double maxGyro = .5;
  public double rotateToAngleRate;
  public AHRS ahrs = new AHRS(SPI.Port.kMXP);

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
    super.getPIDController().setInputRange(-180.0f, 180.0f);
    super.getPIDController().setOutputRange(-1.0, 1.0);
    setAbsoluteTolerance(Constants.kToleranceDegrees);
    super.getPIDController().setContinuous(true);
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

  public double GyroDrive(double turn){
    angle+=turn;
    b = (int)angle/180;
    angle = (float) (angle * Math.pow(-1, b));
    return angle;
  }

  public void ResetGyroAngle(){
    ahrs.reset();
    angle = 0;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    ResetGyroAngle();
    super.getPIDController().enable();
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return ahrs.pidGet();
  }

  @Override
  public void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    if (output >= maxGyro) {
			rotateToAngleRate = maxGyro;
    }
    else if (output<=-maxGyro) {
			rotateToAngleRate = -maxGyro;
    }
    else {
			rotateToAngleRate = output;
		}
		if (isstill) {
			if (this.getPIDController().getError() >= 2 || this.getPIDController().getError() <= -2) {
				if (rotateToAngleRate <= minimalvoltage && rotateToAngleRate > 0) {
					post += 1;
					rotateToAngleRate = minimalvoltage - ((1 -this.getPIDController().getError()) / 65) + post / 100;
        }
        else if (rotateToAngleRate >= -minimalvoltage && rotateToAngleRate < 0) {
					negt += 1;
					rotateToAngleRate = -minimalvoltage + ((1 - this.getPIDController().getError()) / 65) - negt / 100;
				}
      }
      else {
				post = 0;
				negt = 0;
			}
		}
  }
}
