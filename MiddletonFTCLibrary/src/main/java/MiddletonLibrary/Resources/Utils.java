package MiddletonLibrary.Resources;

import android.graphics.Point;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.opencv.core.Rect;

import MiddletonLibrary.Math.PIDController;
import MiddletonLibrary.Math.Vector;
import MiddletonLibrary.Odometry.PositionTracker;

import static java.lang.Double.valueOf;
import static java.lang.Math.*;
import static java.util.Locale.US;
import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;


/**
 * Created by Keval Kataria on 3/15/2021
 */

public class Utils {
    private static MiddletonLinearOpMode linearOpMode;
    private static PositionTracker tracker;
    private static DashBoard dash;
    public static final long DEFAULT_SLEEP_TIME = 500;
    public static final double DEFAULT_TIMEOUT = 2;
    public static final double DEFAULT_SPEED_MULTIPLIER = sqrt(2);
    public static final String VUFORIA_KEY = "AfbTJrD/////AAABmUej6YcOSE12mu7/2FcsZfdGXFK+GVVrTUq" +
            "n2Oki4pa+iRwrgeicZ+d2FUjjB8J8ett3omSP/q6P94JsNKWFGHRsg//Y6UMrxo1eX3bKnYhaOpAZ8LQdS0w" +
            "WrUFnGfnde+sgSnexbGpviPD38wvJq44FSsGLpI4Gz05zkEHrJRwxZ1eEt9rWc1Rs8NsIildRuOHjKd4WuF+" +
            "oyeuTNxj9HLCTuVRtvIpVE8SbcqXUoYDkPUWU2glz5RtdGOkVQPGUDGAM1UJ2MeTHsuOhA18K8j6lSlQauMT" +
            "nDm5N85KELfrDl008Q35GRvf4fwcAXpSIg1fGc8/yDpeGkAvTmRx1A+GQHbfnw5LiJWl0fY7BCqd2";

    public static PIDController turnController, driveController;

    public static void sleep(long milliSeconds) {getLinearOpMode().sleep(milliSeconds);}
    public static void sleep() {sleep(DEFAULT_SLEEP_TIME);}

    public static void setLinearOpMode(MiddletonLinearOpMode opMode) {linearOpMode = opMode;}
    public static MiddletonLinearOpMode getLinearOpMode() {return linearOpMode;}
    public static boolean opModeIsActive() {return linearOpMode.opModeIsActive();}
    public static HardwareMap getHardwareMap() {return linearOpMode.hardwareMap;}
    public static void setTracker(PositionTracker positionTracker) {tracker = positionTracker;}
    public static PositionTracker getTracker() {return tracker;}
    public static void setDash(DashBoard dashboard) {dash = dashboard;}
    public static DashBoard getDash() {return dash;}

    public static double adjustAngle(double angle, AngleUnit angleUnit) {
        return angleUnit.normalize(angle);
    }
    public static double adjustAngle(double angle) {return adjustAngle(angle, DEGREES);}

    public static boolean tolerance(double value1, double value2, double tolerance) {
        return abs(value1 - value2) < tolerance;
    }


    public static double max(double... vals) {
        double max = Double.MIN_VALUE;
        for (double d: vals) if (max < d) max = d;
        return max;
    }
    public static double scaleNumber(double m, double currentMin, double currentMax, double newMin, double newMax) {
        return (((m - currentMin) * (newMax - newMin)) / (currentMax - currentMin)) + newMin;
    }
    public static double scaleNumber(double m, double newMin, double newMax) {
        return scaleNumber(m, 0, 1, newMin, newMax);
    }
    public static Double formatAngle(AngleUnit angleUnit, double angle) {
        return valueOf(formatDegrees(DEGREES.fromUnit(angleUnit, angle)));
    }
    private static String formatDegrees(double degrees){
        return String.format(US, "%.2f", adjustAngle(degrees));
    }
    public static Point getCenterPoint(Rect rect) {
        return new Point(rect.x + rect.width/2, rect.y + rect.height/2);
    }
    public static Vector getLookAhead(Vector initial, Vector current, Vector finalPos, double lookAhead) {
        Vector pathDisplacement = initial.displacement(finalPos);
        Vector projection = new Vector("Projection",
                current.projectOnTo(pathDisplacement).getX() - initial.getX(),
                current.projectOnTo(pathDisplacement).getY() - initial.getY()).projectOnTo(pathDisplacement).add(initial);
        double theta = atan2(pathDisplacement.getY(), pathDisplacement.getX());
        return new Vector("Look Ahead", projection.getX() + (lookAhead * cos(theta)),
                projection.getY() + (lookAhead * sin(theta)));
    }

    public static double[] negate(double[] values) {
        double[] result = new double[values.length];
        for(int i = 0; i < values.length; i++) result[i] = -values[i];
        return result;
    }
    public static double clip(double value) {return Range.clip(value, 0, 1);}
    public static void clip(double[] values) {for(int i = 0; i < values.length; i++) values[i] = clip(values[i]);}
}