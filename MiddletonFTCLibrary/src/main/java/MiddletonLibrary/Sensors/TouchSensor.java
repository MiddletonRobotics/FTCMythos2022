package MiddletonLibrary.Sensors;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.TouchSensor;

import static MiddletonLibrary.Resources.Utils.getHardwareMap;

/**
 * Created by Keval Kataria on 3/15/2021
 */

public class TouchSensor {
    private com.qualcomm.robotcore.hardware.TouchSensor touchSensor;
    private String name;

    public TouchSensor(String name) {
        this.name = name;
        touchSensor = getHardwareMap().get(com.qualcomm.robotcore.hardware.TouchSensor.class, name);
    }

    public boolean isPressed() {return touchSensor.isPressed();}

    @NonNull
    @Override
    public String toString() {return String.format("%s:\nPressed: %b", name, isPressed());}
}