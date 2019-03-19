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
  public static DoubleSolenoid hatchPistons = new DoubleSolenoid(RobotMap.hatchIn, RobotMap.hatchOut);
  public LimitSwitch bottom = new LimitSwitch(RobotMap.bottomSwitch);
  public LimitSwitch top = new LimitSwitch(RobotMap.topSwitch);
  public void ejectHatch(boolean out, boolean in) {
    if (out) {
			hatchPistons.set(Value.kForward);
	  }
	  else if (in) {
	    hatchPistons.set(Value.kReverse);
    }
  }

  public void arm(double axis) {
    //if (bottom.get() == true && top.get() == true){
      	arm.set(ControlMode.PercentOutput, axis*-.65);
    //}
   /* if (bottom.get() == false){
      arm.set(ControlMode.PercentOutput,0);
      if(axis >0.1){
        arm.set(ControlMode.PercentOutput, axis*.3);
      }
    }
   if (!top.get() ){
      arm.set(ControlMode.PercentOutput,0);
      if(axis < -0.1){
        arm.set(ControlMode.PercentOutput, axis*.3);
      }
    }*/
  }
  
  public void wrist(double axis) {
		wrist.set(ControlMode.PercentOutput, axis*-.5);
  }
  
  public void wheels(double axis) {
		wheels.set(ControlMode.PercentOutput, axis*-.5);
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
