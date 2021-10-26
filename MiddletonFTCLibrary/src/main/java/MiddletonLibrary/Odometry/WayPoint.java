package MiddletonLibrary.Odometry;

import androidx.annotation.NonNull;

import MiddletonLibrary.Math.Vector;

import static java.util.Locale.US;

/**
 * Created by Keval Kataria on 3/15/2021
 */

public class WayPoint {
    private double x, y, h, targetRadius = 1, modeSwitchRadius = 10, pointSwitchRadius = 10,
            minVelocity = 0.5, maxVelocity = 1, timeout = 2, lookAhead = 10,
            angularCorrectionSpeed = 0.08, driveCorrectionSpeed = 0.12;
    private String name = "WayPoint";
    private PointMode switchMode = PointMode.MECH;
    private Runnable onComplete = () -> {};

    public enum PointMode {MECH, TANK, SWITCH}

    public WayPoint() {}
    public WayPoint(double x, double y, double h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    public WayPoint setSwitchMode(PointMode switchMode) {
        this.switchMode = switchMode;
        return this;
    }

    public WayPoint setPoint(double x, double y, double h) {
        this.x = x;
        this.y = y;
        this.h = h;
        return this;
    }

    public WayPoint setX(double x) {
        this.x = x;
        return this;
    }

    public WayPoint setY(double y) {
        this.y = y;
        return this;
    }

    public WayPoint setH(double h) {
        this.h = h;
        return this;
    }

    public WayPoint setTargetRadius(double targetRadius) {
        modeSwitchRadius += targetRadius - this.targetRadius;
        pointSwitchRadius += targetRadius - this.targetRadius;
        this.targetRadius = targetRadius;
        return this;
    }

    public WayPoint setModeSwitchRadius(double modeSwitchRadius) {
        this.modeSwitchRadius = modeSwitchRadius;
        return this;
    }

    public WayPoint setPointSwitchRadius(double pointSwitchRadius) {
        this.pointSwitchRadius = pointSwitchRadius;
        return this;
    }

    public WayPoint setMinVelocity(double minVelocity) {
        this.minVelocity = minVelocity;
        return this;
    }

    public WayPoint setDriveCorrectionSpeed(double driveCorrectionSpeed) {
        this.driveCorrectionSpeed = driveCorrectionSpeed;
        return this;
    }

    public WayPoint setMaxVelocity(double maxVelocity) {
        this.maxVelocity = maxVelocity;
        return this;
    }

    public WayPoint setTimeout(double timeout) {
        this.timeout = timeout;
        return this;
    }

    public WayPoint setLookAhead(double lookAhead) {
        this.lookAhead = lookAhead;
        return this;
    }

    public WayPoint setAngularCorrectionSpeed(double angularCorrectionSpeed) {
        this.angularCorrectionSpeed = angularCorrectionSpeed;
        return this;
    }

    public WayPoint setOnComplete(Runnable onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public double getH() {return h;}

    public double getTargetRadius() {return targetRadius;}
    public double getModeSwitchRadius() {return modeSwitchRadius;}
    public double getPointSwitchRadius() {return pointSwitchRadius;}

    public double getMinVelocity() {return minVelocity;}
    public double getMaxVelocity() {return maxVelocity;}

    public double getTimeout() {return timeout;}

    public double getLookAhead() {return lookAhead;}

    public double getAngularCorrectionSpeed() {return angularCorrectionSpeed;}
    public double getDriveCorrectionSpeed() {return driveCorrectionSpeed;}

    public PointMode getSwitchMode() {return switchMode;}

    public Runnable getOnComplete() {return onComplete;}

    public Vector getPoint() {return new Vector(name, x, y);}

    public WayPoint setName(String name) {
        this.name = name;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(US, "%s:\nX: %.2f\nY: %.2f\nH: %.1f\n", name, x, y, h);
    }
}