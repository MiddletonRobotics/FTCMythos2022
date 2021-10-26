package MiddletonLibrary.Motion;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.ServoConfigurationType;

import MiddletonLibrary.Sensors.TouchSensor;

import static MiddletonLibrary.Resources.Utils.getHardwareMap;
import static java.util.Locale.US;

/**
 * Created by Keval Kataria on 3/15/2021
 */

public class CRServo {
    private CRServoImplEx servo;
    private String name;
    private TouchSensor min, max = null;
    private boolean limitDetection;

    public CRServo(String name){
        this.name = name;
        com.qualcomm.robotcore.hardware.CRServo servo = getHardwareMap().crservo.get(name);
        this.servo = new CRServoImplEx((ServoControllerEx) servo.getController(), servo.getPortNumber(), ServoConfigurationType.getStandardServoType());
        limitDetection = false;
    }
    public CRServo(String name, Direction direction){
        this(name);
        servo.setDirection(direction);
    }
    public void setLimits(TouchSensor min, TouchSensor max){
        this.min = min; this.max = max;
        limitDetection = true;
    }
    public void setLimit(TouchSensor min) {
        this.min = min;
        this.max = null;
        limitDetection = false;
    }
    public void setPower (double power) {
        double motorPower = power;
        if (limitDetection) {
            if (min != null && min.isPressed() && power < 0 ||
                    max != null && max.isPressed() && power > 0)
                motorPower = 0;
        }
        servo.setPower(motorPower);
    }
    public double getPower() {return servo.getPower();}

    @NonNull
    @Override
    public String toString() {
        return String.format(US, "%s:\nPower: %.2f", name, getPower());
    }
}