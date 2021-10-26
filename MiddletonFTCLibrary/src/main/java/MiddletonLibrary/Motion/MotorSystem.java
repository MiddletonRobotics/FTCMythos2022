package MiddletonLibrary.Motion;

import androidx.annotation.NonNull;

import MiddletonLibrary.Sensors.TouchSensor;

import static java.util.Locale.US;

/**
 * Created by Keval Kataria on 3/15/2021
 */

public class MotorSystem {
    Motor[] motors;
    String name;

    public MotorSystem(String name, Motor... motors) {
        this.motors = motors;
        this.name = name;
    }

    public void resetEncoders() {for (Motor motor : motors) motor.resetEncoder();}

    public double getInches () {
        double sum = 0;
        for (Motor motor : motors) sum += motor.getInches();
        return sum / motors.length;
    }
    public double getCurrentPosition() {
        double sum = 0;
        for(Motor motor : motors) sum += motor.getCurrentPosition();
        return sum / motors.length;
    }

    public void setPower(double power) {
        for (Motor motor : motors) motor.setPower(power);
    }
    public void setLimits (TouchSensor min, TouchSensor max) {
        for (Motor motor : motors) motor.setLimits(min, max);
    }
    public void setVelocityControl (boolean velocityControl) {
        for (Motor motor : motors) motor.setVelocityControl(velocityControl);
    }

    public double getPower() {
        double sum = 0;
        for (Motor motor : motors) sum += Math.abs(motor.getPower());
        return sum/motors.length;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(US, "%s:\nNum Motors: %d\nInches: %f", name, motors.length, getInches());
    }
}