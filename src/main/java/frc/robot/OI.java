package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class OI {
    private static final XboxController Driver = new XboxController(0);
    private static final XboxController Operator = new XboxController(1);

    // driver
    public static double getDriverSpeed() { return Driver.getTriggerAxis(Hand.kLeft) - Driver.getTriggerAxis(Hand.kRight); }
    public static double getSteer() { return Driver.getX(Hand.kLeft); }
    public static boolean getGyro() { return Driver.getXButton(); }
    public static boolean getAlignment() { return Driver.getYButton(); }

    // operator
    public static double getArm() { return Operator.getTriggerAxis(Hand.kRight) - Operator.getTriggerAxis(Hand.kLeft); }
    public static double getWrist() { return Operator.getY(Hand.kLeft); }
    public static double getWheels() { return Operator.getY(Hand.kRight); }
    public static boolean getClimb() { return Operator.getAButton(); }
    public static boolean getEjectorOut() { return Operator.getBumper(Hand.kRight); }
    public static boolean getEjectorIn() { return Operator.getBumper(Hand.kLeft); }
    public static boolean getClimbReset() {return Operator.getStartButton();}
    public static boolean bigZoop() {return Operator.getYButton();}

    /*                                 Controls
    * Driver
    * Right trigger = forwards
    * Left trigger = backwards
    * Left stick = steer
    * 
    * Operator
    * Right trigger = arm up
    * Left trigger = arm down
    * Left stick = wrist
    * Right stick = wheels
    * A + B = ejector
    */
}
