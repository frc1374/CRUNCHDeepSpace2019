package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class IntakeSubsystem extends Subsystem {
  public TalonSRX arm = new TalonSRX(RobotMap.arm);
  public TalonSRX wrist = new TalonSRX(RobotMap.wrist);
  public TalonSRX wheels = new TalonSRX(RobotMap.wheels);
  public static DoubleSolenoid rightPistons = new DoubleSolenoid(RobotMap.rightIn, RobotMap.rightOut);
  public static DoubleSolenoid leftPistons = new DoubleSolenoid(RobotMap.leftIn, RobotMap.leftOut);

  public void ejectHatch(boolean out, boolean in) {
    if (out) {
			rightPistons.set(Value.kForward);
			leftPistons.set(Value.kForward);
	  }
	  else if (in) {
	    rightPistons.set(Value.kReverse);
	    leftPistons.set(Value.kReverse);
    }
  }

  public void arm(double axis) {
		arm.set(ControlMode.PercentOutput, axis);
  }
  
  public void wrist(double axis) {
		wrist.set(ControlMode.PercentOutput, axis);
  }
  
  public void wheels(double axis) {
		wheels.set(ControlMode.PercentOutput, axis);
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
