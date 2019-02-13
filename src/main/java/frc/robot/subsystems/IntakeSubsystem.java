package frc.robot.subsystems;
import frc.robot.sensors.LimitSwitch;
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
  public LimitSwitch bottom = new LimitSwitch(RobotMap.bottomSwitch);
  public LimitSwitch top = new LimitSwitch(RobotMap.topSwitch);
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
    if (bottom.get() == false && top.get() == false){
      	arm.set(ControlMode.PercentOutput, axis);
    }
    if (bottom.get() == true){
      arm.set(ControlMode.PercentOutput,0);
      if(axis >0.1){
        arm.set(ControlMode.PercentOutput, axis);
      }
    }
    if (top.get() == true){
      arm.set(ControlMode.PercentOutput,0);
      if(axis < -0.1){
        arm.set(ControlMode.PercentOutput, axis);
      }
    }
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
