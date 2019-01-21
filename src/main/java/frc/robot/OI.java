package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class OI {
    private static final XboxController Driver = new XboxController(0);
    private static final XboxController Operator = new XboxController(1);

     // driver
     public static double getDriverSpeed() { return Driver.getTriggerAxis(Hand.kLeft) - Driver.getTriggerAxis(Hand.kRight); }
     public static double getSteer() { return Driver.getX(Hand.kLeft); }


     // operator
    public static double getElevator() {return Operator.getY(Hand.kLeft);}
     /*                                 Controls
     * Driver
     * Right trigger = forwards
     * Left trigger = backwards
     * Left stick = steer
     * 
     * Operator
     * 
     */
}
