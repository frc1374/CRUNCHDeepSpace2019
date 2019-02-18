package frc.robot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class RobotMap {
    //sensor
    public static final int topSwitch  = 0;
    public static final int bottomSwitch  = 1;
    public static final int climbSwitch = 2;
    public static final int climbSwitchR = 3;
    // drive
    public static final int left1       = 0;
    public static final int left2       = 1;
    public static final int right1      = 2;
    public static final int right2      = 3;

    public static final int compressor  = 0;
    public static final int shift1      = 0;
    public static final int shift2      = 7;

    //intake
    public static final int arm         = 4;
    public static final int wrist       = 5;
    public static final int wheels      = 6;

    //climber
    public static final int climber     = 7;

    //hatch
    public static final int rightIn     = 1;
    public static final int rightOut    = 6;
    public static final int leftIn      = 2;
    public static final int leftOut     = 5;
}
