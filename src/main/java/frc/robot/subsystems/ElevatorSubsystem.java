package frc.robot.subsystems;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.sensors.LimitSwitch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;


public class ElevatorSubsystem extends PIDSubsystem{
    public TalonSRX liftMotor = new TalonSRX(0);
    public LimitSwitch bottom = new LimitSwitch(RobotMap.bottomSwitch);
    public Encoder ElevatorEncoder = new Encoder(0, 1, true, EncodingType.k4X);
    static double p = 0.01;
	static double i = 0.0;
	static double d = 0.0;
    double height;
    int level = 0;
    int setMotorDirection;
    boolean override = false;
    public double[] setPoints = {
        0,
         (0),
         (0),
    };


    public ElevatorSubsystem() {
        super("Elevator",p,i,d);
        setAbsoluteTolerance(0.005);     
        getPIDController().setContinuous(true);
        ElevatorEncoder.setDistancePerPulse(0);
    }
    
    public void initDefaultCommand() {
        ElevatorEncoder.setPIDSourceType(PIDSourceType.kDisplacement);

    }

    public void switchOverride () {
    	override = !override;
    }

    protected double returnPIDInput() {
        height = ElevatorEncoder.getDistance();
    	return height;
    }

    public void stop() {
    	liftMotor.set(ControlMode.PercentOutput,0);
    }

    public void elevate(double input) {
        double height = ElevatorEncoder.getDistance();
        if (override == true) {		
	    	liftMotor.set(ControlMode.PercentOutput,input);
			return;
	    } else if (bottom.get() == true && input < 0) {
	    	Robot.ElevatorSubsystem.getLiftEncoder().reset();
			stop();
			return;
		} else {
			liftMotor.set(ControlMode.PercentOutput,input);
		}
    }
    

    protected void usePIDOutput(double output) {
        if (bottom.get() == true && output < 0) {
	    	Robot.ElevatorSubsystem.getLiftEncoder().reset();
			stop();
			return;
        } else {
        	liftMotor.set(ControlMode.PercentOutput,output * setMotorDirection);
        	return;
        }
    }
    
    public Encoder getLiftEncoder() {
    	return ElevatorEncoder;
    }
}

    