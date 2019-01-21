/*
Made by Chris Rusu Jan 21 2019
This is elevator code that should work on an elevator that has 4 limit switches 2 at the top, one for slow down and one for stop
The elevator is probably not going to happen this year :C
But if you find yourself in the future making an elevator and now knowing how to make it work this should be a good start
This elevator works by the operator controlling it with the joystick and not by making a pid stop.
Btw I also made a pid version that is more likley to not work as I dont know how to use pid. So use this one.
You need the limitswitch sensor code to have this work

The elevator will be slower if the trigger is odd, and it becomes odd when it goes over the limit switch to slow
The limit switch at the top and bottom make it so that it can only go the direction that is opposite to it ie if its at the top it cant go higher
the math.abs gives a absolute value of the remote and it can be negative or possitive by multiplying it by a -1
so it can not break the elevator and be sad
*/
package frc.robot.subsystems;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.sensors.LimitSwitch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {
    public TalonSRX liftMotor = new TalonSRX(0);
    public LimitSwitch bottomS = new LimitSwitch(RobotMap.bottomSwitchS);
    public LimitSwitch bottom = new LimitSwitch(RobotMap.bottomSwitch);
    public LimitSwitch topS = new LimitSwitch(RobotMap.topSwitchS);
    public LimitSwitch top = new LimitSwitch(RobotMap.topSwitch);
    int trigger = 1;
    public void elevate(double input) {
        if (bottomS.get() == true || topS.get() == true ) {
           trigger++;
        }
        else if (trigger %2 == 0) {
            liftMotor.set(ControlMode.PercentOutput,input*.2);
			return;
        }
        else if (trigger %2 == 1) {
            liftMotor.set(ControlMode.PercentOutput,input);
			return;
        }
        else if (bottom.get() == true){
            liftMotor.set(ControlMode.PercentOutput,Math.abs(input));
			return;
        }
        else if (top.get() == true ){
            liftMotor.set(ControlMode.PercentOutput,Math.abs(input)*-1);
			return;
        }
    }
public void initDefaultCommand() {

    }
}